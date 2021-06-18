package net.kaaass.bookshop.mapper;

import net.kaaass.bookshop.dao.entity.MediaEntity;
import net.kaaass.bookshop.dao.entity.MetadataEntity;
import net.kaaass.bookshop.dao.entity.PluginEntity;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.dto.MetadataDto;
import net.kaaass.bookshop.dto.PluginDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi", uses = CommonTransform.class)
public interface CommenMapper {

    MetadataDto metadataEntityToDto(MetadataEntity metadataEntity);

    MediaDto mediaEntityToDto(MediaEntity metadataEntity);

    PluginDto pluginEntityToDto(PluginEntity pluginEntity);
}
