package net.kaaass.bookshop.service;

import java8.util.Optional;
import net.kaaass.bookshop.controller.request.CategoryAddRequest;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CategoryEntity;
import net.kaaass.bookshop.dto.CategoryDto;
import net.kaaass.bookshop.exception.NotFoundException;

import javax.ejb.Local;
import java.util.List;

/**
 * 分类服务
 * @author kaaass
 */
@Local
public interface CategoryService {

    Optional<CategoryDto> add(CategoryAddRequest userToAdd);

    CategoryDto getById(String id) throws NotFoundException;

    /**
     * @deprecated
     */
    CategoryEntity getEntityById(String id) throws NotFoundException;

    List<CategoryDto> getAll(Pageable pageable);

    /**
     * 寻找某个分类的所有子结点
     */
    List<CategoryEntity> getAllSubs(CategoryEntity root);

    void deleteById(String id) throws NotFoundException;
}
