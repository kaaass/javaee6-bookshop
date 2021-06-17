package net.kaaass.bookshop.controller.response;

import net.kaaass.bookshop.dto.UserInfoDto;
import net.kaaass.bookshop.vo.UserOrderCountVo;

public class UserProfileResponse {

    private UserInfoDto info;

    private UserOrderCountVo orderCount;

    public UserProfileResponse() {
    }

    public UserInfoDto getInfo() {
        return this.info;
    }

    public UserOrderCountVo getOrderCount() {
        return this.orderCount;
    }

    public void setInfo(UserInfoDto info) {
        this.info = info;
    }

    public void setOrderCount(UserOrderCountVo orderCount) {
        this.orderCount = orderCount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserProfileResponse)) return false;
        final UserProfileResponse other = (UserProfileResponse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$info = this.getInfo();
        final Object other$info = other.getInfo();
        if (this$info == null ? other$info != null : !this$info.equals(other$info)) return false;
        final Object this$orderCount = this.getOrderCount();
        final Object other$orderCount = other.getOrderCount();
        if (this$orderCount == null ? other$orderCount != null : !this$orderCount.equals(other$orderCount))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserProfileResponse;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $info = this.getInfo();
        result = result * PRIME + ($info == null ? 43 : $info.hashCode());
        final Object $orderCount = this.getOrderCount();
        result = result * PRIME + ($orderCount == null ? 43 : $orderCount.hashCode());
        return result;
    }

    public String toString() {
        return "UserProfileResponse(info=" + this.getInfo() + ", orderCount=" + this.getOrderCount() + ")";
    }
}
