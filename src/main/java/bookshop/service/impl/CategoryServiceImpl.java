package bookshop.service.impl;

import java8.util.Optional;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import bookshop.controller.request.CategoryAddRequest;
import bookshop.dao.Pageable;
import bookshop.dao.entity.CategoryEntity;
import bookshop.dao.repository.CategoryRepository;
import bookshop.dto.CategoryDto;
import bookshop.exception.BaseException;
import bookshop.exception.NotFoundException;
import bookshop.mapper.ProductMapper;
import bookshop.service.CategoryService;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CategoryServiceImpl implements CategoryService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(CategoryServiceImpl.class);

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private ProductMapper productMapper;

    @Override
    public Optional<CategoryDto> add(CategoryAddRequest categoryDto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setName(categoryDto.getName());
        CategoryEntity parent = Optional.ofNullable(categoryDto.getParentId())
                .flatMap(new Function<String, Optional<CategoryEntity>>() {
                    @Override
                    public Optional<CategoryEntity> apply(String s) {
                        return categoryRepository.findById(s);
                    }
                })
                .orElse(null);
        entity.setParent(parent);
        try {
            return Optional.of(categoryRepository.save(entity))
                    .map(new Function<CategoryEntity, CategoryDto>() {
                        @Override
                        public CategoryDto apply(CategoryEntity categoryEntity) {
                            return productMapper.categoryEntityToDto(categoryEntity);
                        }
                    });
        } catch (Exception e) {
            log.info("插入时发生错误", e);
            return Optional.empty();
        }
    }

    @Override
    public CategoryDto getById(String id) throws NotFoundException {
        return categoryRepository.findById(id)
                .map(new Function<CategoryEntity, CategoryDto>() {
                    @Override
                    public CategoryDto apply(CategoryEntity categoryEntity) {
                        return productMapper.categoryEntityToDto(categoryEntity);
                    }
                })
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此分类！"));
    }

    @Override
    public CategoryEntity getEntityById(String id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此分类！"));
    }

    @Override
    public List<CategoryDto> getAll(Pageable pageable) {
        return StreamSupport.stream(categoryRepository.findAll(pageable))
                .map(new Function<CategoryEntity, CategoryDto>() {
                    @Override
                    public CategoryDto apply(CategoryEntity categoryEntity) {
                        return productMapper.categoryEntityToDto(categoryEntity);
                    }
                })
                .collect(Collectors.<CategoryDto>toList());
    }

    @Override
    public List<CategoryEntity> getAllSubs(final CategoryEntity root) {
        List<CategoryEntity> result = new ArrayList<CategoryEntity>() {{
            add(root);
        }};
        // 找到一层子节点
        final List<CategoryEntity> sons = categoryRepository.findAllByParent(root);
        for (final CategoryEntity son : sons) {
            result.addAll(getAllSubs(son));
        }
        return result;
    }

    @Override
    public void deleteById(String id) throws NotFoundException {
        CategoryEntity entity = getEntityById(id);
        categoryRepository.delete(entity);
    }
}
