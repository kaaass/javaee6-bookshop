package bookshop.service;

import java8.util.Optional;
import java8.util.function.Function;
import bookshop.dao.entity.MediaEntity;
import bookshop.dao.repository.MediaRepository;
import bookshop.dto.MediaDto;
import bookshop.mapper.CommenMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class ResourceService implements Serializable {

    @Inject
    private MediaRepository mediaRepository;

    @Inject
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
