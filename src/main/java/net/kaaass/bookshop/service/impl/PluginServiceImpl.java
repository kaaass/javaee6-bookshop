package net.kaaass.bookshop.service.impl;

import java8.util.function.Function;
import java8.util.function.Supplier;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.dao.entity.PluginEntity;
import net.kaaass.bookshop.dao.repository.PluginRepository;
import net.kaaass.bookshop.dto.PluginDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.CommenMapper;
import net.kaaass.bookshop.plugin.PluginManager;
import net.kaaass.bookshop.service.PluginService;
import net.kaaass.bookshop.util.TimeUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Stateless
@Slf4j
public class PluginServiceImpl implements PluginService, Serializable {

    @Inject
    private PluginRepository pluginRepository;

    @Inject
    private PluginManager pluginManager;

    @Inject
    private CommenMapper commenMapper;

    @Override
    public List<PluginDto> getAll() {
        return StreamSupport.stream(pluginRepository.findAll())
                .map(new Function<PluginEntity, PluginDto>() {
                    @Override
                    public PluginDto apply(PluginEntity pluginEntity) {
                        return commenMapper.pluginEntityToDto(pluginEntity);
                    }
                })
                .collect(Collectors.<PluginDto>toList());
    }

    @Override
    public void mountAll() {
        log.info("开始加载所有插件");
        val entities = pluginRepository.findAllByEnableTrue();
        for (val entity : entities) {
            log.info("开始加载插件 entity = {}", entity);
            pluginManager.loadPlugin(entity.getId(), entity.getFilename());
        }
    }

    @Override
    public PluginDto enable(final String path) throws BadRequestException {
        PluginEntity entity = pluginRepository.findFirstByFilename(path)
                .orElseGet(new Supplier<PluginEntity>() {
                    @Override
                    public PluginEntity get() {
                        val newEntity = new PluginEntity();
                        newEntity.setFilename(path);
                        return newEntity;
                    }
                });
        if (entity.isEnable()) {
            throw new BadRequestException("该插件已经启用！");
        }
        entity.setEnable(true);
        entity.setEnableTime(TimeUtils.nowTimestamp());
        entity = pluginRepository.save(entity);
        log.info("启用插件 entity = {}", entity);
        pluginManager.loadPlugin(entity.getId(), entity.getFilename());
        return commenMapper.pluginEntityToDto(pluginRepository.save(entity));
    }

    @Override
    public void disable(String id) throws NotFoundException, BadRequestException {
        val entity = pluginRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此模组"));
        if (!entity.isEnable()) {
            throw new BadRequestException("该插件已经禁用！");
        }
        entity.setEnable(false);
        pluginRepository.save(entity);
        pluginManager.unloadPlugin(entity.getId());
    }

    @Override
    public void remove(String id) throws NotFoundException {
        val entity = pluginRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此模组"));
        if (entity.isEnable()) {
            pluginManager.unloadPlugin(entity.getId());
        }
        pluginRepository.delete(entity);
    }
}
