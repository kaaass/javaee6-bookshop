package bookshop.mapper;

import bookshop.dao.entity.MediaEntity;
import bookshop.dto.MediaDto;

public interface CommenMapper {

    MediaDto mediaEntityToDto(MediaEntity metadataEntity);
}
