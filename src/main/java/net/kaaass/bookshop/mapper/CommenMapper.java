package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dto.MediaDto;

public interface CommenMapper {

    MediaDto mediaEntityToDto(MediaEntity metadataEntity);
}
