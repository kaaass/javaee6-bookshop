package net.kaaass.bookshop.dto;

import lombok.Data;
import net.kaaass.bookshop.util.DateToLongSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单 DTO
 * @author kaaass
 */
@Data
public class OrderDto {

    private String id;

    private String uid;

    private UserAddressDto address;

    private String requestId;

    private OrderType type;

    private float price;

    private float mailPrice;

    private String deliverCode;

    private String reason;

    private List<OrderItemDto> products = new ArrayList<>();

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date payTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date deliverTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date finishTime;

    @JsonSerialize(using = DateToLongSerializer.class)
    private Date refundTime;
}
