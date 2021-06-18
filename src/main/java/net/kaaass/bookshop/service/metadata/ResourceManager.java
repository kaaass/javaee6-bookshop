package net.kaaass.bookshop.service.metadata;

import java8.util.Optional;
import java8.util.function.Function;
import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dao.repository.MediaRepository;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.mapper.PojoMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;

@Stateless
public class ResourceManager implements Serializable {

    @Inject
    private MediaRepository mediaRepository;

    @Inject
    private PojoMapper pojoMapper;

    public Optional<MediaEntity> getEntity(String id) {
        return mediaRepository.findById(id);
    }

    public Optional<MediaDto> getResource(String id) {
        return getEntity(id).map(new Function<MediaEntity, MediaDto>() {
            @Override
            public MediaDto apply(MediaEntity mediaEntity) {
                return pojoMapper.entityToDto(mediaEntity);
            }
        });
    }
}
