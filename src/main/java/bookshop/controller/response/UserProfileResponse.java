package bookshop.controller.response;

import bookshop.vo.UserOrderCountVo;

public class UserProfileResponse {

    private UserOrderCountVo orderCount;

    public UserProfileResponse() {
    }

    public UserOrderCountVo getOrderCount() {
        return this.orderCount;
    }

    public void setOrderCount(UserOrderCountVo orderCount) {
        this.orderCount = orderCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof UserProfileResponse)) {
            return false;
        }
        final UserProfileResponse other = (UserProfileResponse) o;
        if (!other.canEqual(this)) {
            return false;
        }
        final Object this$orderCount = this.getOrderCount();
        final Object other$orderCount = other.getOrderCount();
        return this$orderCount == null ? other$orderCount == null : this$orderCount.equals(other$orderCount);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserProfileResponse;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $orderCount = this.getOrderCount();
        result = result * PRIME + ($orderCount == null ? 43 : $orderCount.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "UserProfileResponse(orderCount=" + this.getOrderCount() + ")";
    }
}
