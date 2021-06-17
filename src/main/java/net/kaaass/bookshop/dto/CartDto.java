package net.kaaass.bookshop.dto;

import net.kaaass.bookshop.util.DateToLongSerializer;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 购物车项目 DTO
 * @author kaaass
 */
public class CartDto {
    private String id;

    private String uid;

    private ProductDto product;

    private int count;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    public CartDto() {
    }

    public String getId() {
        return this.id;
    }

    public String getUid() {
        return this.uid;
    }

    public ProductDto getProduct() {
        return this.product;
    }

    public int getCount() {
        return this.count;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CartDto)) return false;
        final CartDto other = (CartDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$product = this.getProduct();
        final Object other$product = other.getProduct();
        if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
        if (this.getCount() != other.getCount()) return false;
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CartDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $product = this.getProduct();
        result = result * PRIME + ($product == null ? 43 : $product.hashCode());
        result = result * PRIME + this.getCount();
        final Object $createTime = this.getCreateTime();
        result = result * PRIME + ($createTime == null ? 43 : $createTime.hashCode());
        return result;
    }

    public String toString() {
        return "CartDto(id=" + this.getId() + ", uid=" + this.getUid() + ", product=" + this.getProduct() + ", count=" + this.getCount() + ", createTime=" + this.getCreateTime() + ")";
    }
}
