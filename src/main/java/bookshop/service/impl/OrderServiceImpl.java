package bookshop.service.impl;

import java8.util.Optional;
import java8.util.function.Consumer;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import bookshop.controller.request.CommentRequest;
import bookshop.controller.request.OrderCreateMultiRequest;
import bookshop.controller.request.OrderCreateRequest;
import bookshop.controller.request.OrderCreateSingleRequest;
import bookshop.controller.response.OrderCheckResponse;
import bookshop.controller.response.OrderRequestResponse;
import bookshop.dao.Pageable;
import bookshop.dao.entity.*;
import bookshop.dao.repository.CommentRepository;
import bookshop.dao.repository.OrderRepository;
import bookshop.dao.repository.ProductRepository;
import bookshop.dto.*;
import bookshop.exception.*;
import bookshop.mapper.OrderMapper;
import bookshop.mapper.ProductMapper;
import bookshop.service.*;
import bookshop.service.mq.OrderMessageProducer;
import bookshop.util.Constants;
import bookshop.util.FileUtils;
import bookshop.util.StringUtils;
import bookshop.util.TimeUtils;
import bookshop.vo.UserOrderCountVo;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class OrderServiceImpl implements OrderService, Serializable {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(OrderServiceImpl.class);
    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserService userService;

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CartService cartService;

    @Inject
    private OrderMessageProducer orderMessageProducer;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ProductService productService;

    @Inject
    private OrderMapper orderMapper;

    @Inject
    private ProductMapper productMapper;

    @Override
    public OrderEntity getEntityById(String id) throws NotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该订单！"));
    }

    @Override
    public OrderEntity getEntityByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException {
        final OrderEntity result = this.getEntityById(id);
        if (!result.getUid().equals(uid)) {
            throw new ForbiddenException("未找到该订单！");
        }
        return result;
    }

    @Override
    public OrderCheckResponse checkRequest(String requestId) throws BadRequestException {
        final OrderCheckResponse result = new OrderCheckResponse();
        final Optional<OrderEntity> order = orderRepository.findByRequestId(requestId);
        if (order.isPresent()) {
            final OrderEntity entity = order.get();
            if (entity.getType() == OrderType.ERROR) {
                throw new BadRequestException(entity.getReason());
            }
            result.setOrderId(entity.getId());
        }
        return result;
    }

    @Override
    public OrderDto getById(String id) throws NotFoundException {
        return orderMapper.orderEntityToDto(this.getEntityById(id));
    }

    @Override
    public OrderDto getByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException {
        return orderMapper.orderEntityToDto(this.getEntityByIdAndCheck(id, uid));
    }

    @Override
    public List<OrderDto> getAll(Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByTypeIsNotOrderByCreateTimeDesc(OrderType.ERROR, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return orderMapper.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public List<OrderDto> getAllByUid(String uid, Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByUidAndTypeIsNotOrderByCreateTimeDesc(uid, OrderType.ERROR, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return orderMapper.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public UserOrderCountVo getUserOrderCount(String uid) {
        final UserOrderCountVo result = new UserOrderCountVo();
        final Integer toPay = orderRepository.countAllByUidAndType(uid, OrderType.CREATED).orElse(0);
        result.setToPay(toPay);
        final Integer toDeliver = orderRepository.countAllByUidAndType(uid, OrderType.PAID).orElse(0);
        result.setToDeliver(toDeliver);
        final Integer toComment = orderRepository.countAllByUidAndType(uid, OrderType.DELIVERED).orElse(0);
        result.setToComment(toComment);
        return result;
    }

    @Override
    public List<OrderDto> getAllByUidAndType(String uid, OrderType type, Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByUidAndTypeOrderByCreateTimeDesc(uid, type, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return orderMapper.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public List<OrderDto> getAllByType(OrderType type, Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByTypeOrderByCreateTimeDesc(type, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return orderMapper.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public List<OrderDto> getAllByProduct(String pid, Pageable pageable) throws NotFoundException {
        final ProductEntity product = productService.getEntityById(pid);
        return StreamSupport.stream(orderRepository.findAllByProduct(product, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return orderMapper.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public OrderRequestResponse createToQueue(String uid, OrderCreateRequest request) throws InternalErrorExeption, NotFoundException {
        final String requestId = StringUtils.uuid();
        // 购物车处理
        if (request instanceof OrderCreateMultiRequest) {
            final OrderCreateMultiRequest multi = (OrderCreateMultiRequest) request;
            multi.setCachedCartItems(new ArrayList<CartDto>());
            for (final OrderCreateMultiRequest.CartItem cartItem : multi.getCartItems()) {
                final String cid = cartItem.getId();
                // 实体缓存获取
                multi.getCachedCartItems().add(cartService.getById(cid));
                // 删除购物车中已有的商品
                cartService.deleteById(cid);
            }
        }
        // 订单处理
        OrderRequestContext context = new OrderRequestContext();
        context.setRequest(request);
        context.setUid(uid);
        context.setRequestId(requestId);
        OrderRequestResponse result = new OrderRequestResponse();
        result.setRequestId(requestId);
        // 准备上下文
        String message;
        try {
            message = this.objectMapper.writeValueAsString(context);
        } catch (IOException e) {
            log.warn("序列化错误", e);
            throw new InternalErrorExeption(null, e);
        }
        log.info("序列化后的订单请求：{}", message);
        // 发送消息
        this.orderMessageProducer.sendMessage(requestId, message);
        return result;
    }

    @Override
    public void doCreate(OrderRequestContext context) throws NotFoundException {
        final OrderEntity entity = new OrderEntity();
        entity.setId(StringUtils.orderId(getLastOrderId()));
        entity.setUid(context.getUid());
        entity.setRequestId(context.getRequestId());
        final OrderCreateRequest request = context.getRequest();
        final UserAddressEntity address = userService.getAddressEntityByIdAndCheck(request.getAddressId(), context.getUid());
        final UserAuthEntity user = userService.getAuthEntityById(context.getUid());
        entity.setAddress(address);
        /*
         订单逻辑
         */
        try {
            // 生成商品信息
            float price = 0;
            float mailPrice = 0;
            final ArrayList<OrderItemDto> products = new ArrayList<>();
            if (request instanceof OrderCreateSingleRequest) {
                final OrderCreateSingleRequest single = (OrderCreateSingleRequest) request;
                final ProductEntity product = productRepository.getOne(single.getProductId());
                final OrderItemDto item = new OrderItemDto();
                price = product.getPrice();
                mailPrice = product.getMailPrice();
                item.setCount(1);
                item.setPrice(price);
                item.setProduct(productMapper.productEntityToDto(product));
                products.add(item);
            } else if (request instanceof OrderCreateMultiRequest) {
                final OrderCreateMultiRequest multi = (OrderCreateMultiRequest) request;
                for (final CartDto cartItem : multi.getCachedCartItems()) {
                    final OrderItemDto item = new OrderItemDto();
                    final float curPrice = cartItem.getCount() * cartItem.getProduct().getPrice();
                    final float curMailPrice = cartItem.getProduct().getMailPrice();
                    item.setProduct(cartItem.getProduct());
                    item.setPrice(curPrice);
                    item.setCount(cartItem.getCount());
                    products.add(item);
                    price += curPrice;
                    if (curMailPrice > mailPrice) {
                        mailPrice = curMailPrice;
                    }
                }
            }
            // 处理返回
            entity.setPrice(price + mailPrice);
            entity.setMailPrice(mailPrice);
            entity.setProducts(StreamSupport.stream(products)
                    .map(new Function<OrderItemDto, OrderItemEntity>() {
                        @Override
                        public OrderItemEntity apply(OrderItemDto orderItemDto) {
                            return orderMapper.orderItemDtoToEntity(orderItemDto);
                        }
                    })
                    .peek(new Consumer<OrderItemEntity>() {
                        @Override
                        public void accept(OrderItemEntity orderItemEntity) {
                            orderItemEntity.setUser(user);
                        }
                    })
                    .peek(new Consumer<OrderItemEntity>() {
                        @Override
                        public void accept(OrderItemEntity orderItemEntity) {
                            orderItemEntity.setOrder(entity);
                        }
                    })
                    .collect(Collectors.<OrderItemEntity>toList()));
            // 检查库存数量
            for (final OrderItemEntity orderItemEntity : entity.getProducts()) {
                final ProductEntity product = orderItemEntity.getProduct();
                final int dest = product.getStorage().getRest() - orderItemEntity.getCount();
                if (dest >= 0) {
                    product.getStorage().setRest(dest);
                } else {
                    throw new BadRequestException("本商品库存不足！");
                }
            }
            // 更新库存
            for (final OrderItemEntity orderItemEntity : entity.getProducts()) {
                productRepository.save(orderItemEntity.getProduct());
            }
        } catch (BaseException e) {
            entity.setType(OrderType.ERROR);
            entity.setReason(e.getMessage());
        }
        orderRepository.save(entity);
        if (OrderType.ERROR.equals(entity.getType())) {
            return;
        }
        /*
          写订单文件
         */
        printOrderFile(entity);
    }

    @Override
    public OrderDto setPaid(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException {
        final OrderEntity entity = uid == null ? getEntityById(id) :
                getEntityByIdAndCheck(id, uid);
        if (!entity.getType().less(OrderType.PAID)) {
            throw new BadRequestException("该订单已付款或已取消！");
        }
        entity.setType(OrderType.PAID);
        entity.setPayTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return orderMapper.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setDelivered(String id, String deliverCode) throws NotFoundException, BadRequestException {
        final OrderEntity entity = getEntityById(id);
        if (entity.getType() != OrderType.PAID) {
            throw new BadRequestException("只有已付款的订单可以发货！");
        }
        entity.setType(OrderType.DELIVERED);
        entity.setDeliverCode(deliverCode);
        entity.setDeliverTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return orderMapper.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setCanceled(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException {
        final OrderEntity entity = getEntityByIdAndCheck(id, uid);
        if (!entity.getType().less(OrderType.PAID)) {
            throw new BadRequestException("该订单已付款或已取消！");
        }
        entity.setType(OrderType.CANCELED);
        entity.setFinishTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return orderMapper.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setRefunded(String id) throws NotFoundException, BadRequestException {
        final OrderEntity entity = getEntityById(id);
        if (entity.getType().less(OrderType.PAID) || entity.getType().great(OrderType.COMMENTED)) {
            throw new BadRequestException("只有已付款、未退款的订单可以退款！");
        }
        entity.setType(OrderType.REFUNDED);
        entity.setRefundTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return orderMapper.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setCommented(String id, String uid, CommentRequest commentRequest) throws NotFoundException, ForbiddenException, BadRequestException {
        final OrderEntity entity = getEntityByIdAndCheck(id, uid);
        final UserAuthEntity auth = userService.getAuthEntityById(uid);
        if (entity.getType() != OrderType.DELIVERED) {
            throw new BadRequestException("该订单当前不能评价！");
        }
        entity.setType(OrderType.COMMENTED);
        entity.setFinishTime(TimeUtils.nowTimestamp());

        for (final CommentRequest.Content comment : commentRequest.getComments()) {
            final CommentEntity commentEntity = new CommentEntity();
            commentEntity.setUser(auth);
            commentEntity.setOrderId(id);
            commentEntity.setProductId(comment.getProductId());
            commentEntity.setRate(comment.getRate());
            commentEntity.setContent(comment.getContent());
            commentEntity.setCommentTime(TimeUtils.nowTimestamp());
            commentRepository.save(commentEntity);
        }

        /*
          写订单文件
         */
        printOrderFile(entity);
        return orderMapper.orderEntityToDto(orderRepository.save(entity));
    }

    private String getLastOrderId() {
        final Date dayStart = TimeUtils.dayStart(new Date());
        Timestamp start = TimeUtils.dateToTimestamp(dayStart);
        Timestamp end = TimeUtils.dateToTimestamp(TimeUtils.dayShift(dayStart, 1));
        log.info("查询与日期 {} 与 {} 之间", start, end);
        final Optional<OrderEntity> result = orderRepository.findFirstByCreateTimeBetweenOrderByCreateTimeDesc(start, end);
        return result.map(new Function<OrderEntity, String>() {
            @Override
            public String apply(OrderEntity orderEntity) {
                return orderEntity.getId();
            }
        }).orElse(Constants.INIT_ORDER_ID);
    }

    private void printOrderFile(OrderEntity entity) {
        final OrderDto dto = orderMapper.orderEntityToDto(entity);
        String filename = String.format("%s.txt", entity.getId());
        File file = new File(Constants.ORDER_FOLDER, filename);
        if (file.exists()) {
            file.delete();
        }
        // 订单文件内容
        final UserAddressDto addr = dto.getAddress();
        StringBuilder sb = new StringBuilder();
        sb.append("订单号：").append(dto.getId()).append('\n');
        sb.append("订单状态：").append(dto.getType()).append('\n');
        sb.append("运单号：").append(dto.getDeliverCode()).append('\n');
        sb.append("创建时间：").append(formatDate(dto.getCreateTime())).append('\n');
        sb.append("付款时间：").append(formatDate(dto.getPayTime())).append('\n');
        sb.append("发货时间：").append(formatDate(dto.getDeliverTime())).append('\n');
        sb.append("评价时间：").append(formatDate(dto.getFinishTime())).append('\n');
        sb.append("退款时间：").append(formatDate(dto.getRefundTime())).append('\n');
        sb.append("收货地址：\n")
                .append("  ").append(addr.getName()).append(" ").append(addr.getPhone()).append('\n')
                .append("  ").append(addr.getArea()).append('\n')
                .append("  ").append(addr.getDetailAddress()).append('\n');
        sb.append("订单信息：\n");
        sb.append("书名\t\t\t\t\t\t单价\t\t数量\t\t总价\n");
        for (final OrderItemDto item : dto.getProducts()) {
            sb.append(String.format("%s\t\t\t\t\t\t%s\t\t%s\t\t%s\n",
                    item.getProduct().getName(),
                    item.getProduct().getPrice(),
                    item.getCount(),
                    item.getPrice()));
        }
        // 输出
        log.info("输出订单 {} 详细信息至 {}", dto.getId(), file.getAbsolutePath());
        FileUtils.saveToFile(new ByteArrayInputStream(sb.toString().getBytes()), file);
    }

    private static String formatDate(Date date) {
        if (date == null) {
            return "暂无";
        }
        return date.toLocaleString();
    }
}
