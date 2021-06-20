package bookshop.mapper.impl;

import bookshop.dao.entity.MediaEntity;
import bookshop.dto.MediaDto;
import bookshop.mapper.CommenMapper;

import javax.annotation.Generated;
import javax.ejb.Singleton;
import javax.enterprise.context.ApplicationScoped;

@Singleton
@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-06-18T00:00:27+0800",
        comments = "version: 1.2.0.Final, compiler: javac, environment: Java 15.0.2 (N/A)"
)
@ApplicationScoped
public class CommenMapperImpl implements CommenMapper {

    @Override
    public MediaDto mediaEntityToDto(MediaEntity metadataEntity) {
        if (metadataEntity == null) {
            return null;
        }

        MediaDto mediaDto = new MediaDto();

        mediaDto.setId(metadataEntity.getId());
        mediaDto.setType(metadataEntity.getType());
        mediaDto.setUrl(metadataEntity.getUrl());
        mediaDto.setUploaderUid(metadataEntity.getUploaderUid());
        mediaDto.setUploadTime(metadataEntity.getUploadTime());

        return mediaDto;
    }
}
