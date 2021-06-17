package net.kaaass.bookshop.controller.response;

/**
 * 订单请求响应
 *
 * @author kaaass
 */
public class OrderRequestResponse {

    private String requestId;

    public OrderRequestResponse() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderRequestResponse)) return false;
        final OrderRequestResponse other = (OrderRequestResponse) o;
        if (!other.canEqual(this)) return false;
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        return this$requestId == null ? other$requestId == null : this$requestId.equals(other$requestId);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderRequestResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        return result;
    }

    public String toString() {
        return "OrderRequestResponse(requestId=" + this.getRequestId() + ")";
    }
}
