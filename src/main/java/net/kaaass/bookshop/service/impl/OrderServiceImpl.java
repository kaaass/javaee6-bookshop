package net.kaaass.bookshop.service.impl;

import java8.util.function.Consumer;
import java8.util.function.Function;
import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import lombok.var;
import net.kaaass.bookshop.controller.request.CommentRequest;
import net.kaaass.bookshop.controller.request.OrderCreateMultiRequest;
import net.kaaass.bookshop.controller.request.OrderCreateRequest;
import net.kaaass.bookshop.controller.response.OrderCheckResponse;
import net.kaaass.bookshop.controller.response.OrderRequestResponse;
import net.kaaass.bookshop.dao.Pageable;
import net.kaaass.bookshop.dao.entity.CommentEntity;
import net.kaaass.bookshop.dao.entity.OrderEntity;
import net.kaaass.bookshop.dao.entity.OrderItemEntity;
import net.kaaass.bookshop.dao.repository.CartRepository;
import net.kaaass.bookshop.dao.repository.CommentRepository;
import net.kaaass.bookshop.dao.repository.OrderRepository;
import net.kaaass.bookshop.dao.repository.ProductRepository;
import net.kaaass.bookshop.dto.OrderDto;
import net.kaaass.bookshop.dto.OrderItemDto;
import net.kaaass.bookshop.dto.OrderType;
import net.kaaass.bookshop.event.AfterOrderPromoteEvent;
import net.kaaass.bookshop.event.GotOrderContextEvent;
import net.kaaass.bookshop.event.PostOrderContextEvent;
import net.kaaass.bookshop.eventhandle.EventManager;
import net.kaaass.bookshop.exception.*;
import net.kaaass.bookshop.mapper.OrderMapper;
import net.kaaass.bookshop.promote.OrderPromoteContextFactory;
import net.kaaass.bookshop.promote.PromoteManager;
import net.kaaass.bookshop.service.OrderRequestContext;
import net.kaaass.bookshop.service.OrderService;
import net.kaaass.bookshop.service.ProductService;
import net.kaaass.bookshop.service.UserService;
import net.kaaass.bookshop.service.mq.OrderMessageProducer;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.FileUtils;
import net.kaaass.bookshop.util.StringUtils;
import net.kaaass.bookshop.util.TimeUtils;
import net.kaaass.bookshop.vo.UserOrderCountVo;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Stateless
@Slf4j
public class OrderServiceImpl implements OrderService, Serializable {

    @Inject
    private PromoteManager promoteManager;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private UserService userService;

    @Inject
    private OrderPromoteContextFactory orderPromoteContextFactory;

    @Inject
    private CommentRepository commentRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private CartRepository cartRepository;

    @Inject
    private OrderMessageProducer orderMessageProducer;

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private ProductService productService;

    @Override
    public OrderEntity getEntityById(String id) throws NotFoundException {
        return orderRepository.findById(id)
                .orElseThrow(BaseException.supplier(NotFoundException.class, "未找到该订单！"));
    }

    @Override
    public OrderEntity getEntityByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException {
        val result = this.getEntityById(id);
        if (!result.getUid().equals(uid)) {
            throw new ForbiddenException("未找到该订单！");
        }
        return result;
    }

    @Override
    public OrderCheckResponse checkRequest(String requestId) throws BadRequestException {
        val result = new OrderCheckResponse();
        val order = orderRepository.findByRequestId(requestId);
        if (order.isPresent()) {
            val entity = order.get();
            if (entity.getType() == OrderType.ERROR) {
                throw new BadRequestException(entity.getReason());
            }
            result.setOrderId(entity.getId());
        }
        return result;
    }

    @Override
    public OrderDto getById(String id) throws NotFoundException {
        return OrderMapper.INSTANCE.orderEntityToDto(this.getEntityById(id));
    }

    @Override
    public OrderDto getByIdAndCheck(String id, String uid) throws NotFoundException, ForbiddenException {
        return OrderMapper.INSTANCE.orderEntityToDto(this.getEntityByIdAndCheck(id, uid));
    }

    @Override
    public List<OrderDto> getAll(Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByTypeIsNotOrderByCreateTimeDesc(OrderType.ERROR, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return OrderMapper.INSTANCE.orderEntityToDto(orderEntity);
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
                        return OrderMapper.INSTANCE.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public UserOrderCountVo getUserOrderCount(String uid) {
        val result = new UserOrderCountVo();
        val toPay = orderRepository.countAllByUidAndType(uid, OrderType.CREATED).orElse(0);
        result.setToPay(toPay);
        val toDeliver = orderRepository.countAllByUidAndType(uid, OrderType.PAID).orElse(0);
        result.setToDeliver(toDeliver);
        val toComment = orderRepository.countAllByUidAndType(uid, OrderType.DELIVERED).orElse(0);
        result.setToComment(toComment);
        return result;
    }

    @Override
    public List<OrderDto> getAllByUidAndType(String uid, OrderType type, Pageable pageable) {
        return StreamSupport.stream(orderRepository.findAllByUidAndTypeOrderByCreateTimeDesc(uid, type, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return OrderMapper.INSTANCE.orderEntityToDto(orderEntity);
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
                        return OrderMapper.INSTANCE.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public List<OrderDto> getAllByProduct(String pid, Pageable pageable) throws NotFoundException {
        val product = productService.getEntityById(pid);
        return StreamSupport.stream(orderRepository.findAllByProduct(product, pageable))
                .map(new Function<OrderEntity, OrderDto>() {
                    @Override
                    public OrderDto apply(OrderEntity orderEntity) {
                        return OrderMapper.INSTANCE.orderEntityToDto(orderEntity);
                    }
                })
                .collect(Collectors.<OrderDto>toList());
    }

    @Override
    public OrderRequestResponse createToQueue(String uid, OrderCreateRequest request) throws InternalErrorExeption, NotFoundException {
        val requestId = StringUtils.uuid();
        var context = new OrderRequestContext();
        context.setRequest(request);
        context.setUid(uid);
        context.setRequestId(requestId);
        val result = new OrderRequestResponse();
        result.setRequestId(requestId);
        // 触发事件
        val event = new PostOrderContextEvent(uid, context);
        EventManager.EVENT_BUS.post(event);
        context = event.getContext();
        // 准备上下文
        String message = null;
        try {
            message = this.objectMapper.writeValueAsString(context);
        } catch (IOException e) {
            log.warn("序列化错误", e);
            throw new InternalErrorExeption(null, e);
        }
        log.info("序列化后的订单请求：{}", message);
        this.orderMessageProducer.sendMessage(requestId, message);
        return result;
    }

    @Override
    public void doCreate(OrderRequestContext context) throws NotFoundException {
        final val entity = new OrderEntity();
        entity.setId(StringUtils.orderId(getLastOrderId()));
        entity.setUid(context.getUid());
        entity.setRequestId(context.getRequestId());
        val request = context.getRequest();
        val address = userService.getAddressEntityByIdAndCheck(request.getAddressId(), context.getUid());
        val user = userService.getAuthEntityById(context.getUid());
        entity.setAddress(address);
        /*
         打折逻辑
         */
        try {
            // 触发事件
            val event = new GotOrderContextEvent(context);
            var cancel = EventManager.EVENT_BUS.post(event);
            context = event.getContext();
            if (cancel) {
                throw new BadRequestException("订单处理被取消！");
            }
            // 拼接上下文
            val promoteContext = orderPromoteContextFactory.buildFromRequestContext(context);
            log.info("请求上下文：{}", promoteContext);
            // 检查购买限制
            for (val promoteItem : promoteContext.getProducts()) {
                val buyLimit = promoteItem.getProduct().getBuyLimit();
                if (buyLimit > 0 && promoteItem.getCount() > buyLimit) {
                    throw new BadRequestException(String.format("本商品限购%d件！", buyLimit));
                }
                if (new Date().before(promoteItem.getProduct().getStartSellTime())) {
                    throw new BadRequestException("商品还未开卖！");
                }
            }
            // 打折处理
            var promoteResult = promoteManager.doOnOrder(promoteContext);
            log.info("打折结果：{}", promoteResult);
            // 触发事件
            val promoteEvent = new AfterOrderPromoteEvent(promoteResult);
            cancel = EventManager.EVENT_BUS.post(event);
            promoteResult = promoteEvent.getPromoteResult();
            if (cancel) {
                throw new BadRequestException("订单处理被取消！");
            }
            // 处理返回
            entity.setPrice(promoteResult.getPrice());
            entity.setMailPrice(promoteResult.getMailPrice());
            final OrderRequestContext finalContext = context;
            entity.setProducts(StreamSupport.stream(promoteResult.getProducts())
                    .map(new Function<OrderItemDto, OrderItemEntity>() {
                        @Override
                        public OrderItemEntity apply(OrderItemDto orderItemDto) {
                            return OrderMapper.INSTANCE.orderItemDtoToEntity(orderItemDto);
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
            for (val orderItemEntity : entity.getProducts()) {
                val product = orderItemEntity.getProduct();
                val dest = product.getStorage().getRest() - orderItemEntity.getCount();
                if (dest >= 0) {
                    product.getStorage().setRest(dest);
                } else {
                    throw new BadRequestException("本商品库存不足！");
                }
            }
            // 更新库存
            for (val orderItemEntity : entity.getProducts()) {
                productRepository.save(orderItemEntity.getProduct());
            }
        } catch (BadRequestException e) {
            entity.setType(OrderType.ERROR);
            entity.setReason(e.getMessage());
        }
        orderRepository.save(entity);
        /*
          删除购物车中已有的商品
        */
        if (request instanceof OrderCreateMultiRequest) {
            for (val cartItem : ((OrderCreateMultiRequest) request).getCartItems()) {
                cartRepository.deleteById(cartItem.getId());
            }
        }
        /*
          写订单文件
         */
        printOrderFile(entity);
    }

    @Override
    public OrderDto setPaid(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException {
        val entity = uid == null ? getEntityById(id) :
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
        return OrderMapper.INSTANCE.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setDelivered(String id, String deliverCode) throws NotFoundException, BadRequestException {
        val entity = getEntityById(id);
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
        return OrderMapper.INSTANCE.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setCanceled(String id, String uid) throws NotFoundException, ForbiddenException, BadRequestException {
        val entity = getEntityByIdAndCheck(id, uid);
        if (!entity.getType().less(OrderType.PAID)) {
            throw new BadRequestException("该订单已付款或已取消！");
        }
        entity.setType(OrderType.CANCELED);
        entity.setFinishTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return OrderMapper.INSTANCE.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setRefunded(String id) throws NotFoundException, BadRequestException {
        val entity = getEntityById(id);
        if (entity.getType().less(OrderType.PAID) || entity.getType().great(OrderType.COMMENTED)) {
            throw new BadRequestException("只有已付款、未退款的订单可以退款！");
        }
        entity.setType(OrderType.REFUNDED);
        entity.setRefundTime(TimeUtils.nowTimestamp());
        /*
          写订单文件
         */
        printOrderFile(entity);
        return OrderMapper.INSTANCE.orderEntityToDto(orderRepository.save(entity));
    }

    @Override
    public OrderDto setCommented(String id, String uid, CommentRequest commentRequest) throws NotFoundException, ForbiddenException, BadRequestException {
        val entity = getEntityByIdAndCheck(id, uid);
        if (entity.getType() != OrderType.DELIVERED) {
            throw new BadRequestException("该订单当前不能评价！");
        }
        entity.setType(OrderType.COMMENTED);
        entity.setFinishTime(TimeUtils.nowTimestamp());

        for (val comment : commentRequest.getComments()) {
            val commentEntity = new CommentEntity();
            commentEntity.setUid(uid);
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
        return OrderMapper.INSTANCE.orderEntityToDto(orderRepository.save(entity));
    }

    private String getLastOrderId() {
        val dayStart = TimeUtils.dayStart(new Date());
        Timestamp start = TimeUtils.dateToTimestamp(dayStart);
        Timestamp end = TimeUtils.dateToTimestamp(TimeUtils.dayShift(dayStart, 1));
        log.info("查询与日期 {} 与 {} 之间", start, end);
        val result = orderRepository.findFirstByCreateTimeBetweenOrderByCreateTimeDesc(start, end);
        return result.map(new Function<OrderEntity, String>() {
            @Override
            public String apply(OrderEntity orderEntity) {
                return orderEntity.getId();
            }
        }).orElse(Constants.INIT_ORDER_ID);
    }

    private void printOrderFile(OrderEntity entity) {
        val dto = OrderMapper.INSTANCE.orderEntityToDto(entity);
        String filename = String.format("%s.txt", entity.getId());
        File file = new File(Constants.ORDER_FOLDER, filename);
        if (file.exists()) {
            file.delete();
        }
        // 订单文件内容
        val addr = dto.getAddress();
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
        for (val item : dto.getProducts()) {
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
