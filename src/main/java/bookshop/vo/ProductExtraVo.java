package bookshop.vo;

/**
 * 订单额外信息 VO
 *
 * @author kaaass
 */
public class ProductExtraVo {

    private int monthPurchase;

    private String detail;

    public ProductExtraVo() {
    }

    public int getMonthPurchase() {
        return this.monthPurchase;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setMonthPurchase(int monthPurchase) {
        this.monthPurchase = monthPurchase;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductExtraVo)) return false;
        final ProductExtraVo other = (ProductExtraVo) o;
        if (!other.canEqual(this)) return false;
        if (this.getMonthPurchase() != other.getMonthPurchase()) return false;
        final Object this$detail = this.getDetail();
        final Object other$detail = other.getDetail();
        return this$detail == null ? other$detail == null : this$detail.equals(other$detail);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductExtraVo;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getMonthPurchase();
        final Object $detail = this.getDetail();
        result = result * PRIME + ($detail == null ? 43 : $detail.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "ProductExtraVo(monthPurchase=" + this.getMonthPurchase() + ", detail=" + this.getDetail() + ")";
    }
}
