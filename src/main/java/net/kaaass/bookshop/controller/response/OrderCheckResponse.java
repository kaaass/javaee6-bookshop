package net.kaaass.bookshop.controller.response;

/**
 * 订单检查响应
 *
 * @author kaaass
 */
public class OrderCheckResponse {

    private String orderId;

    public OrderCheckResponse() {
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderCheckResponse)) return false;
        final OrderCheckResponse other = (OrderCheckResponse) o;
        if (!other.canEqual(this)) return false;
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        return this$orderId == null ? other$orderId == null : this$orderId.equals(other$orderId);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderCheckResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $orderId = this.getOrderId();
        result = result * PRIME + ($orderId == null ? 43 : $orderId.hashCode());
        return result;
    }

    public String toString() {
        return "OrderCheckResponse(orderId=" + this.getOrderId() + ")";
    }
}
