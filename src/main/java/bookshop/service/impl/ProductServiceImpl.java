package bookshop.service.impl;

import bookshop.controller.request.ProductAddRequest;
import bookshop.dao.entity.CategoryEntity;
import bookshop.dao.entity.ProductEntity;
import bookshop.dao.entity.ProductStorageEntity;
import bookshop.dao.repository.CategoryRepository;
import bookshop.dao.repository.OrderItemRepository;
import bookshop.dao.repository.ProductRepository;
import bookshop.dto.ProductDto;
import bookshop.exception.BadRequestException;
import bookshop.exception.BaseException;
import bookshop.exception.InternalErrorExeption;
import bookshop.exception.NotFoundException;
import bookshop.mapper.ProductMapper;
import bookshop.mapper.UserMapper;
import bookshop.service.CategoryService;
import bookshop.service.ProductService;
import bookshop.service.UserService;
import bookshop.util.StringUtils;
import bookshop.util.TimeUtils;
import bookshop.vo.ProductExtraVo;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import org.slf4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

@SessionScoped
@Stateful
public class ProductServiceImpl implements ProductService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    @EJB
    private OrderItemRepository orderItemRepository;

    @EJB
    private ProductMapper productMapper;

    @EJB
    private UserMapper userMapper;

    /**
     * 待添加缓存
     */
    private final Map<String, ProductEntity> productCache = new HashMap<>();

    /**
     * 增加商品
     *
     * @return
     */
    @Override
    public ProductDto addProduct(ProductAddRequest productToAdd) throws NotFoundException {
        ProductEntity entity = new ProductEntity();
        requestToEntity(productToAdd, entity);
        return this.productMapper.productEntityToDto(productRepository.save(entity));
    }

    /**
     * 产品请求转换为添加，不包括 rest、略缩图、分类
     */
    private void requestToEntity(ProductAddRequest productToAdd, ProductEntity entity) throws NotFoundException {
        // 一般字段
        entity.setName(productToAdd.getName());
        entity.setPrice(productToAdd.getPrice());
        entity.setAuthor(productToAdd.getAuthor());
        entity.setIsbn(productToAdd.getIsbn());
        entity.setPublishDate(TimeUtils.dateToTimestamp(productToAdd.getPublishDate()));
        // 存储
        if (entity.getStorage() == null) {
            entity.setStorage(new ProductStorageEntity());
        }
        entity.getStorage().setRest(productToAdd.getRest());
        // 图、分类
        entity.setThumbnail(productToAdd.getThumbnailId());
        if (entity.getCategory() == null || !productToAdd.getCategoryId().equals(entity.getCategory().getId())) {
            final CategoryEntity category = categoryRepository.findById(productToAdd.getCategoryId())
                    .orElseThrow(BaseException.supplier(NotFoundException.class, "分类不存在！"));
            entity.setCategory(category);
        }
    }

    @Override
    public ProductDto editProduct(String id, ProductAddRequest productToAdd) throws NotFoundException, InternalErrorExeption {
        final ProductEntity entity = productRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此商品！"));
        try {
            requestToEntity(productToAdd, entity);
            return productMapper.productEntityToDto(productRepository.save(entity));
        } catch (NotFoundException e) {
            throw e;
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
        return productMapper.productEntityToDto(getEntityById(id));
    }

    @Override
    public ProductDto addProductCache(ProductAddRequest request) throws NotFoundException {
        final ProductEntity entity = new ProductEntity();
        final String fakeId = StringUtils.uuid();
        entity.setId(fakeId);
        requestToEntity(request, entity);
        this.productCache.put(fakeId, entity);
        return this.productMapper.productEntityToDto(entity);
    }

    @Override
    public ProductDto editProductCache(String fakeId, ProductAddRequest request) throws NotFoundException {
        // 检查存在
        if (!this.productCache.containsKey(fakeId)) {
            throw new NotFoundException("不存在此商品！");
        }
        // 更新
        final ProductEntity entity = this.productCache.get(fakeId);
        requestToEntity(request, entity);
        this.productCache.put(fakeId, entity);
        return this.productMapper.productEntityToDto(entity);
    }

    @Override
    public void removeProductCache(String fakeId) throws NotFoundException {
        // 检查存在
        if (!this.productCache.containsKey(fakeId)) {
            throw new NotFoundException("不存在此商品！");
        }
        // 删除
        this.productCache.remove(fakeId);
    }

    @Override
    public List<ProductDto> commitProductCache() throws BadRequestException {
        // 是否为空
        if (this.productCache.isEmpty()) {
            throw new BadRequestException("当前缓冲区为空！");
        }
        // 提交
        final List<ProductDto> ret = StreamSupport.stream(this.productCache.values())
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity entity) {
                        // 清空伪 ID
                        entity.setId(null);
                        // 提交数据库
                        entity = productRepository.save(entity);
                        // 映射
                        return productMapper.productEntityToDto(entity);
                    }
                }).collect(Collectors.<ProductDto>toList());
        clearProductCache();
        return ret;
    }

    @Override
    public void clearProductCache() {
        this.productCache.clear();
    }

    @Override
    public List<ProductDto> getProductCache() {
        return StreamSupport.stream(this.productCache.values())
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public ProductEntity getEntityById(String id) throws NotFoundException {
        return productRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到此商品！"));
    }

    @Override
    public ProductExtraVo getExtraById(String id, int count, String uid) throws NotFoundException {
        final ProductExtraVo extra = new ProductExtraVo();
        extra.setDetail("");
        final ProductEntity entity = getEntityById(id);
        extra.setMonthPurchase(getMonthPurchaseById(entity));
        return extra;
    }

    /**
     * 获取全部商品
     */
    @Override
    public List<ProductDto> getAll() {
        return StreamSupport.stream(productRepository.findAll())
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> getIndexItems() {
        return StreamSupport.stream(productRepository.findAllByIndexOrderGreaterThanEqualOrderByIndexOrderAscCreateTimeDesc())
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
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
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    /**
     * 通过分类获得商品
     */
    @Override
    public List<ProductDto> getAllByCategory(String categoryId) throws NotFoundException {
        final CategoryEntity root = categoryService.getEntityById(categoryId);
        final List<CategoryEntity> categories = categoryService.getAllSubs(root);
        log.info("子分类：{}", categories);
        return StreamSupport.stream(productRepository.findAllByCategoryIn(categories))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> search(String keyword) {
        final String searchStr = StreamSupport.stream(Arrays.asList(keyword.split(" ")))
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return "%" + s + "%";
                    }
                })
                .collect(Collectors.joining(" "));
        log.info("字符串查找关键词 {}", searchStr);
        return StreamSupport.stream(productRepository.findAllByNameIsLikeOrderByIndexOrderAscCreateTimeDesc(searchStr))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> searchByIsbn(String isbn) {
        return StreamSupport.stream(productRepository.searchByIsbn(isbn))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> searchByAuthor(String author) {
        return StreamSupport.stream(productRepository.searchByAuthor("%" + author + "%"))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> searchByPublishDate(Date start, Date end) {
        return StreamSupport.stream(productRepository.searchByPublishDate(
                TimeUtils.dateToTimestamp(start),
                TimeUtils.dateToTimestamp(end)
        ))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
                    }
                })
                .collect(Collectors.<ProductDto>toList());
    }

    @Override
    public List<ProductDto> searchByPrice(float low, float high) {
        return StreamSupport.stream(productRepository.searchByPrice(low, high))
                .map(new Function<ProductEntity, ProductDto>() {
                    @Override
                    public ProductDto apply(ProductEntity productEntity) {
                        return productMapper.productEntityToDto(productEntity);
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
                .orElse(0L).intValue();
    }
}
