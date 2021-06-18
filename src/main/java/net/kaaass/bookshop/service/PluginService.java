package net.kaaass.bookshop.service;

import net.kaaass.bookshop.dto.PluginDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 插件服务
 *
 * @author kaaass
 */
@Local
public interface PluginService {

    List<PluginDto> getAll();

    void mountAll();

    PluginDto enable(String path) throws BadRequestException;

    void disable(String id) throws NotFoundException, BadRequestException;

    void remove(String id) throws NotFoundException;
}
