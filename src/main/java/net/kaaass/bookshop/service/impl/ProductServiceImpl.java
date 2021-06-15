package net.kaaass.bookshop.service.impl;

import java8.util.Optional;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.Stream;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.controller.request.ProductAddRequest;
import net.kaaass.bookshop.controller.response.ProductCommentResponse;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CommentEntity;
import net.kaaass.bookshop.dao.entity.ProductEntity;
import net.kaaass.bookshop.dao.entity.ProductStorageEntity;
import net.kaaass.bookshop.dao.repository.CategoryRepository;
import net.kaaass.bookshop.dao.repository.CommentRepository;
import net.kaaass.bookshop.dao.repository.OrderItemRepository;
import net.kaaass.bookshop.dao.repository.ProductRepository;
import net.kaaass.bookshop.dto.MediaDto;
import net.kaaass.bookshop.dto.ProductDto;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.exception.InternalErrorExeption;
import net.kaaass.bookshop.exception.NotFoundException;
import net.kaaass.bookshop.mapper.ProductMapper;
import net.kaaass.bookshop.mapper.UserMapper;
import net.kaaass.bookshop.service.CategoryService;
import net.kaaass.bookshop.service.ProductService;
import net.kaaass.bookshop.service.PromoteService;
import net.kaaass.bookshop.service.UserService;
import net.kaaass.bookshop.service.metadata.MetadataManager;
import net.kaaass.bookshop.service.metadata.ResourceManager;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.NumericUtils;
import net.kaaass.bookshop.util.TimeUtils;
import net.kaaass.bookshop.vo.CommentVo;
import net.kaaass.bookshop.vo.ProductExtraVo;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Stateful
@Slf4j
public class ProductServiceImpl implements ProductService, Serializable {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ResourceManager resourceManager;

    @Inject
    private CategoryRepository categoryRepository;

    @Inject
    private CategoryService categoryService;

    @Inject
    private MetadataManager metadataManager;

    @EJB
    private PromoteService promoteService;

    @Inject
    private UserService userService;

    @Inject
    private OrderItemRepository orderItemRepository;

    @Inject
    private CommentRepository commentRepository;

    /**
     * 增加商品
     */
    @Override
    public Optional<ProductDto> addProduct(ProductAddRequest productToAdd) {
        val entity = new ProductEntity();
        entity.setName(productToAdd.getName());
        entity.setPrice(productToAdd.getPrice());
        entity.setMailPrice(productToAdd.getMailPrice());
        entity.setBuyLimit(productToAdd.getBuyLimit());
        entity.setStartSellTime(TimeUtils.dateToTimestamp(productToAdd.getStartSellTime()));
        val storage = new ProductStorageEntity();
        storage.setRest(productToAdd.getRest());
        entity.setStorage(storage);
        try {
            val thumbnail = resourceManager.getEntity(productToAdd.getThumbnailId()).orElseThrow();
            entity.setThumbnail(thumbnail);
            val category = categoryRepository.findById(productToAdd.getCategoryId()).orElseThrow();
            entity.setCategory(category);
            return Optional.of(productRepository.save(entity))
                    .map(new Function<ProductEntity, ProductDto>() {
                        @Override
                        public ProductDto apply(ProductEntity productEntity) {
                            return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                        }
                    });
        } catch (Exception e) {
            log.info("插入时发生错误", e);
            return Optional.empty();
        }
    }

    @Override
    public ProductDto editProduct(String id, ProductAddRequest productToAdd) throws NotFoundException, InternalErrorExeption {
        val entity = productRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此商品！"));
        entity.setName(productToAdd.getName());
        entity.setPrice(productToAdd.getPrice());
        entity.setMailPrice(productToAdd.getMailPrice());
        entity.setBuyLimit(productToAdd.getBuyLimit());
        entity.setStartSellTime(TimeUtils.dateToTimestamp(productToAdd.getStartSellTime()));
        entity.getStorage().setRest(productToAdd.getRest());
        try {
            if (!productToAdd.getThumbnailId().equals(entity.getThumbnail().getId())) {
                val thumbnail = resourceManager.getEntity(productToAdd.getThumbnailId()).orElseThrow();
                entity.setThumbnail(thumbnail);
            }
            if (!productToAdd.getCategoryId().equals(entity.getCategory().getId())) {
                val category = categoryRepository.findById(productToAdd.getCategoryId()).orElseThrow();
                entity.setCategory(category);
            }
            return ProductMapper.INSTANCE.productEntityToDto(productRepository.save(entity));
        } catch (Exception e) {
            log.info("插入时发生错误", e);
            throw new InternalErrorExeption("发生未知错误！", e);
        }
    }

    @Override
    public void removeProduct(String id) {
        productRepository.deleteById(id);
    }

    /**
     * 从id获取商品
     */
    @Override
    public ProductDto getById(String id) throws NotFoundException {
        return ProductMapper.INSTANCE.productEntityToDto(getEntityById(id));
    }

    @Override
    public ProductEntity getEntityById(String id) throws NotFoundException {
        return productRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此商品！"));
    }

    @Override
    public ProductExtraVo getExtraById(String id, int count, String uid) throws NotFoundException {
        val extra = new ProductExtraVo();
        extra.setDetail(metadataManager.getForProduct(id, Constants.METAKEY_DETAIL));
        String defaultAddress = null;
        try {
            defaultAddress = userService.getDefaultAddressEntityById(uid).getId();
        } catch (NotFoundException ignored) {
        }
        val entity = getEntityById(id);
        extra.setPromotes(promoteService.getForSingleProduct(entity, count, uid, defaultAddress));
        extra.setMonthPurchase(getMonthPurchaseById(entity));
        // 获得商品图片
        extra.setImages(getProductImagesById(id));
        return extra;
    }

    /**
     * 获取全部商品
     */
    @Override
    public List<ProductDto> getAll(Pageable pageable) {
        return StreamSupport.stream(productRepository.findAll(pageable))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> getIndexItems() {
        return StreamSupport.stream(productRepository.findAllByIndexOrderGreaterThanEqualOrderByIndexOrderAscCreateTimeDesc(0))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    /**
     * 获得秒杀物品
     */
    @Override
    public List<ProductDto> getQuickBuyItems() {
        return StreamSupport.stream(productRepository
                .findAllByStartSellTimeGreaterThanOrderByIndexOrderAscCreateTimeDesc(TimeUtils.nowTimestamp()))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    /**
     * 通过分类获得商品
     */
    @Override
    public List<ProductDto> getAllByCategory(String categoryId, Pageable pageable) throws NotFoundException {
        val root = categoryService.getEntityById(categoryId);
        val categories = categoryService.getAllSubs(root);
        log.info("子分类：{}", categories);
        return StreamSupport.stream(productRepository.findAllByCategoryIn(categories, pageable))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public ProductCommentResponse getComments(String id, Pageable pageable) {
        val result = new ProductCommentResponse();
        val comments = StreamSupport.stream(commentRepository.findAllByProductIdOrderByRateDescCommentTimeDesc(id, pageable))
                .map(new Function<CommentEntity, CommentVo>() {
                    @Override
                    public CommentVo apply(CommentEntity commentEntity) {
                        return UserMapper.INSTANCE.commentEntityToVo(commentEntity);
                    }
                })
                .collect(Collectors.<CommentVo>toList());
        val rate = commentRepository.averageRateByProductId(id)
                .map(new Function<Float, Float>() {
                    @Override
                    public Float apply(Float aFloat) {
                        return NumericUtils.rateRound(aFloat);
                    }
                })
                .orElse(null);
        result.setComments(comments);
        result.setAverageRate(rate);
        return result;
    }

    @Override
    public List<ProductDto> search(String keyword, Pageable pageable) {
        val searchStr = StreamSupport.stream(Arrays.asList(keyword.split(" ")))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return "%" + s + "%";
                    }
                })
                .collect(Collectors.joining(" "));
        log.info("字符串查找关键词 {}", searchStr);
        return StreamSupport.stream(productRepository.findAllByNameIsLikeOrderByIndexOrderAscCreateTimeDesc(searchStr, pageable))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return ProductMapper.INSTANCE.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    /**
     * 获取商品单月销售
     */
    private int getMonthPurchaseById(ProductEntity productEntity) {
        Timestamp start = TimeUtils.dateToTimestamp(TimeUtils.dayShift(new Date(), -30));
        Timestamp end = TimeUtils.nowTimestamp();
        log.info("查询与日期 {} 与 {} 之间", start, end);
        return orderItemRepository.sumCountByIdBetween(productEntity, start, end)
                .orElse(0);
    }

    /**
     * 获得商品图片
     */
    private List<MediaDto> getProductImagesById(String id) {
        val imgStr = metadataManager.getForProduct(id, Constants.METAKEY_IMAGES);
        return StreamSupport.stream(Arrays.asList(imgStr.split(",")))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.trim();
                    }
                })
                .map(new Function<String, Optional<MediaDto>>() {
                    @Override
                    public Optional<MediaDto> apply(String s) {
                        return resourceManager.getResource(s);
                    }
                })
                .flatMap(new Function<Optional<MediaDto>, Stream<MediaDto>>() {
                    @Override
                    public Stream<MediaDto> apply(Optional<MediaDto> mediaDtoOptional) {
                        return mediaDtoOptional.stream();
                    }
                })
                .collect(Collectors.<MediaDto>toList());
    }
}
