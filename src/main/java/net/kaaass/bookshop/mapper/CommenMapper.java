package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dao.entity.MetadataEntity;
import net.kaaass.bookshop.dao.entity.PluginEntity;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.dto.MetadataDto;
import net.kaaass.bookshop.dto.PluginDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommenMapper {
    CommenMapper INSTANCE = Mappers.getMapper(CommenMapper.class);

    MetadataDto metadataEntityToDto(MetadataEntity metadataEntity);

    MediaDto mediaEntityToDto(MediaEntity metadataEntity);

    PluginDto pluginEntityToDto(PluginEntity pluginEntity);
}