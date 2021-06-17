package net.kaaass.bookshop.controller.request;

/**
 * 购物车添加请求
 * @author kaaass
 */
public class CartAddRequest {

    String productId;

    int count;

    public CartAddRequest() {
    }

    public String getProductId() {
        return this.productId;
    }

    public int getCount() {
        return this.count;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CartAddRequest)) return false;
        final CartAddRequest other = (CartAddRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        if (this.getCount() != other.getCount()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CartAddRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        result = result * PRIME + this.getCount();
        return result;
    }

    public String toString() {
        return "CartAddRequest(productId=" + this.getProductId() + ", count=" + this.getCount() + ")";
    }
}
