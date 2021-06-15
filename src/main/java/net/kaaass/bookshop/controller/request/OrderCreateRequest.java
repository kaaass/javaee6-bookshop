package net.kaaass.bookshop.controller.request;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * 基础订单创建请求
 * @author kaaass
 */
@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(
                value = OrderCreateMultiRequest.class,
                name = OrderCreateMultiRequest.TYPE),
        @JsonSubTypes.Type(
                value = OrderCreateSingleRequest.class,
                name = OrderCreateSingleRequest.TYPE)
})
public abstract class OrderCreateRequest {

    private String addressId;
}
