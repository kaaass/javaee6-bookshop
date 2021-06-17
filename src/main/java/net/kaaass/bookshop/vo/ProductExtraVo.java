package net.kaaass.bookshop.vo;

import net.kaaass.bookshop.dto.MediaDto;

import java.util.List;

/**
 * 订单额外信息 VO
 *
 * @author kaaass
 */
public class ProductExtraVo {

    private int monthPurchase;

    private String detail;

    private List<MediaDto> images;

    public ProductExtraVo() {
    }

    public int getMonthPurchase() {
        return this.monthPurchase;
    }

    public String getDetail() {
        return this.detail;
    }

    public List<MediaDto> getImages() {
        return this.images;
    }

    public void setMonthPurchase(int monthPurchase) {
        this.monthPurchase = monthPurchase;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setImages(List<MediaDto> images) {
        this.images = images;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductExtraVo)) return false;
        final ProductExtraVo other = (ProductExtraVo) o;
        if (!other.canEqual(this)) return false;
        if (this.getMonthPurchase() != other.getMonthPurchase()) return false;
        final Object this$detail = this.getDetail();
        final Object other$detail = other.getDetail();
        if (this$detail == null ? other$detail != null : !this$detail.equals(other$detail)) return false;
        final Object this$images = this.getImages();
        final Object other$images = other.getImages();
        return this$images == null ? other$images == null : this$images.equals(other$images);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductExtraVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getMonthPurchase();
        final Object $detail = this.getDetail();
        result = result * PRIME + ($detail == null ? 43 : $detail.hashCode());
        final Object $images = this.getImages();
        result = result * PRIME + ($images == null ? 43 : $images.hashCode());
        return result;
    }

    public String toString() {
        return "ProductExtraVo(monthPurchase=" + this.getMonthPurchase() + ", detail=" + this.getDetail() + ", images=" + this.getImages() + ")";
    }
}
