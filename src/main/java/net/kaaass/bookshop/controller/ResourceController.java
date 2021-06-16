package net.kaaass.bookshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.val;
import lombok.var;
import net.kaaass.bookshop.controller.page.PageInfo;
import net.kaaass.bookshop.controller.request.AddUrlResourceRequest;
import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dao.repository.MediaRepository;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.mapper.CommenMapper;
import net.kaaass.bookshop.security.Secured;
import net.kaaass.bookshop.security.SecurityIdentity;
import net.kaaass.bookshop.security.SecurityRole;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.FileUtils;
import net.kaaass.bookshop.util.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourceController extends BaseController {

    @Inject
    private MediaRepository mediaRepository;

    @Inject
    private PageInfo pageInfo;

    @Inject
    private SecurityIdentity identity;

    @Inject
    private CommenMapper commenMapper;

    @GET
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public List<MediaDto> getAllResource() {
        return StreamSupport.stream(mediaRepository.findAllByOrderByUploadTimeDesc(pageInfo.getPageable()))
                .map(new Function<MediaEntity, MediaDto>() {
                    @Override
                    public MediaDto apply(MediaEntity mediaEntity) {
                        return commenMapper.mediaEntityToDto(mediaEntity);
                    }
                })
                .collect(Collectors.<MediaDto>toList());
    }

    @POST
    @Path("/")
    @Secured(SecurityRole.ADMIN)
    public MediaDto addNetworkResource(AddUrlResourceRequest request) {
        var entity = new MediaEntity();
        entity.setType(request.getType());
        entity.setUrl(request.getUrl());
        entity.setUploaderUid(getUid(identity));
        var result = mediaRepository.save(entity);
        return commenMapper.mediaEntityToDto(result);
    }

    @DELETE
    @Path("/{id}/")
    @Secured(SecurityRole.ADMIN)
    public boolean getAllResource(@PathParam("id") String id) {
        mediaRepository.deleteById(id);
        return true;
    }

    @PUT
    @Path("/image/")
    @Secured(SecurityRole.LOGGED)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public MediaDto uploadImage(MultipartFormDataInput input) throws BadRequestException, IOException {
        val uploadForm = input.getFormDataMap();
        val originalFilename = uploadForm.get("fileName").get(0).getBodyAsString();
        val inputParts = uploadForm.get("file");
        var suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        var newFileName = StringUtils.uuid() + "." + suffix;
        // TODO 检查suffix

        // 保存文件
        if (inputParts.isEmpty()) {
            throw new BadRequestException("无文件传送！");
        }

        val inputStream = inputParts.get(0).getBody(InputStream.class, null);

        File destFile = new File(Constants.UPLOAD_FOLDER + newFileName);
        FileUtils.saveToFile(inputStream, destFile);

        var entity = new MediaEntity();
        entity.setType(Constants.MEDIA_TYPE_IMAGE);
        entity.setUrl(Constants.UPLOAD_URL_PREFIX + newFileName);
        entity.setUploaderUid(getUid(identity));

        var result = mediaRepository.save(entity);
        return commenMapper.mediaEntityToDto(result);
    }
}
