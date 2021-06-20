package bookshop.service;

import bookshop.dao.entity.MediaEntity;
import bookshop.dao.repository.MediaRepository;
import bookshop.dto.MediaDto;
import bookshop.mapper.CommenMapper;
import java8.util.Optional;
import java8.util.function.Function;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.Serializable;

@Stateless
public class ResourceService implements Serializable {

    @EJB
    private MediaRepository mediaRepository;

    @EJB
    private CommenMapper commenMapper;

    public Optional<MediaEntity> getEntity(String id) {
        return mediaRepository.findById(id);
    }

    public Optional<MediaDto> getResource(String id) {
        return getEntity(id).map(new Function<MediaEntity, MediaDto>() {
            @Override
            public MediaDto apply(MediaEntity mediaEntity) {
                return commenMapper.mediaEntityToDto(mediaEntity);
            }
        });
    }
}
