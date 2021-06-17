package net.kaaass.bookshop.service;

import net.kaaass.bookshop.controller.request.OrderCreateRequest;

/**
 * 订单请求 DTO
 * @author kaaass
 */
public class OrderRequestContext {

    private String requestId;

    private String uid;

    private OrderCreateRequest request;

    public OrderRequestContext() {
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getUid() {
        return this.uid;
    }

    public OrderCreateRequest getRequest() {
        return this.request;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setRequest(OrderCreateRequest request) {
        this.request = request;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderRequestContext)) return false;
        final OrderRequestContext other = (OrderRequestContext) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        if (this$requestId == null ? other$requestId != null : !this$requestId.equals(other$requestId)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$request = this.getRequest();
        final Object other$request = other.getRequest();
        if (this$request == null ? other$request != null : !this$request.equals(other$request)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderRequestContext;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $request = this.getRequest();
        result = result * PRIME + ($request == null ? 43 : $request.hashCode());
        return result;
    }

    public String toString() {
        return "OrderRequestContext(requestId=" + this.getRequestId() + ", uid=" + this.getUid() + ", request=" + this.getRequest() + ")";
    }
}
