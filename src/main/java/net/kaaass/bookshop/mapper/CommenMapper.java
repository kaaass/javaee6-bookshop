package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dto.MediaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface CommenMapper {
    CommenMapper INSTANCE = Mappers.getMapper(CommenMapper.class);

    MediaDto mediaEntityToDto(MediaEntity metadataEntity);
}
