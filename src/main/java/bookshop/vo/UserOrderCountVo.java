package bookshop.vo;

/**
 * 用户订单统计 VO
 *
 * @author kaaass
 */
public class UserOrderCountVo {

    private int toPay;

    private int toDeliver;

    private int toComment;

    public UserOrderCountVo() {
    }

    public int getToPay() {
        return this.toPay;
    }

    public int getToDeliver() {
        return this.toDeliver;
    }

    public int getToComment() {
        return this.toComment;
    }

    public void setToPay(int toPay) {
        this.toPay = toPay;
    }

    public void setToDeliver(int toDeliver) {
        this.toDeliver = toDeliver;
    }

    public void setToComment(int toComment) {
        this.toComment = toComment;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserOrderCountVo)) return false;
        final UserOrderCountVo other = (UserOrderCountVo) o;
        if (!other.canEqual(this)) return false;
        if (this.getToPay() != other.getToPay()) return false;
        if (this.getToDeliver() != other.getToDeliver()) return false;
        return this.getToComment() == other.getToComment();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserOrderCountVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getToPay();
        result = result * PRIME + this.getToDeliver();
        result = result * PRIME + this.getToComment();
        return result;
    }

    public String toString() {
        return "UserOrderCountVo(toPay=" + this.getToPay() + ", toDeliver=" + this.getToDeliver() + ", toComment=" + this.getToComment() + ")";
    }
}
