package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;

/**
 * 创建单商品订单请求
 * @author kaaass
 */
public class OrderCreateSingleRequest extends OrderCreateRequest {

    public final static String TYPE = "SINGLE";

    @Uuid(message = "productId格式不正确")
    private String productId;

    public OrderCreateSingleRequest() {
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderCreateSingleRequest)) return false;
        final OrderCreateSingleRequest other = (OrderCreateSingleRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderCreateSingleRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        return result;
    }

    public String toString() {
        return "OrderCreateSingleRequest(productId=" + this.getProductId() + ")";
    }
}
