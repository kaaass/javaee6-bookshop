package bookshop.controller;

import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import bookshop.controller.page.PageInfo;
import bookshop.controller.request.AddUrlResourceRequest;
import bookshop.dao.entity.MediaEntity;
import bookshop.dao.repository.MediaRepository;
import bookshop.dto.MediaDto;
import bookshop.exception.BadRequestException;
import bookshop.mapper.CommenMapper;
import bookshop.security.Secured;
import bookshop.security.SecurityIdentity;
import bookshop.security.SecurityRole;
import bookshop.util.Constants;
import bookshop.util.FileUtils;
import bookshop.util.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Stateless
@Path("/resource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResourceController extends BaseController {

    @EJB
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
        MediaEntity entity = new MediaEntity();
        entity.setType(request.getType());
        entity.setUrl(request.getUrl());
        entity.setUploaderUid(getUid(identity));
        MediaEntity result = mediaRepository.save(entity);
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
        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        String originalFilename = uploadForm.get("fileName").get(0).getBodyAsString();
        List<InputPart> inputParts = uploadForm.get("file");
        String suffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String newFileName = StringUtils.uuid() + "." + suffix;
        // TODO 检查suffix

        // 保存文件
        if (inputParts.isEmpty()) {
            throw new BadRequestException("无文件传送！");
        }

        InputStream inputStream = inputParts.get(0).getBody(InputStream.class, null);

        File destFile = new File(Constants.UPLOAD_FOLDER + newFileName);
        FileUtils.saveToFile(inputStream, destFile);

        MediaEntity entity = new MediaEntity();
        entity.setType(Constants.MEDIA_TYPE_IMAGE);
        entity.setUrl(Constants.UPLOAD_URL_PREFIX + newFileName);
        entity.setUploaderUid(getUid(identity));

        MediaEntity result = mediaRepository.save(entity);
        return commenMapper.mediaEntityToDto(result);
    }
}
