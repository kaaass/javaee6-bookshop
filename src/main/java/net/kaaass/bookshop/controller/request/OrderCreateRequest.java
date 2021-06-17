package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

/**
 * 基础订单创建请求
 * @author kaaass
 */
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

    @Uuid(message = "addressId格式不正确")
    private String addressId;

    public OrderCreateRequest() {
    }

    public String getAddressId() {
        return this.addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderCreateRequest)) return false;
        final OrderCreateRequest other = (OrderCreateRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$addressId = this.getAddressId();
        final Object other$addressId = other.getAddressId();
        if (this$addressId == null ? other$addressId != null : !this$addressId.equals(other$addressId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderCreateRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $addressId = this.getAddressId();
        result = result * PRIME + ($addressId == null ? 43 : $addressId.hashCode());
        return result;
    }

    public String toString() {
        return "OrderCreateRequest(addressId=" + this.getAddressId() + ")";
    }
}
