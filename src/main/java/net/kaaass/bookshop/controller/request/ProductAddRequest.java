package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Isbn;
import net.kaaass.bookshop.constraints.Uuid;
import net.kaaass.bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Date;

public class ProductAddRequest {

    @Size(
            message = "商品名称长度应该5~50之间",
            min = 5,
            max = 50
    )
    private String name;

    @Uuid(message = "缩略图格式错误")
    private String thumbnailId;

    @Min(value = 0, message = "价格不能为负")
    private float price;

    @Min(value = 0, message = "价格不能为负")
    private float mailPrice;

    @Min(value = -1, message = "购买限制最小为-1")
    private int buyLimit;

    @Isbn(message = "ISBN格式错误")
    private String isbn;

    @Size(
            message = "作者名称长度应该2~100之间",
            min = 2,
            max = 100
    )
    private String author;

    @Min(value = -1, message = "首页展示顺序最小为-1")
    private int indexOrder;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date publishDate;

    @Uuid(message = "分类格式错误")
    private String categoryId;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    private Date startSellTime;

    @Min(value = 0, message = "库存不能为负")
    private int rest;

    public ProductAddRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getThumbnailId() {
        return this.thumbnailId;
    }

    public float getPrice() {
        return this.price;
    }

    public float getMailPrice() {
        return this.mailPrice;
    }

    public int getBuyLimit() {
        return this.buyLimit;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getIndexOrder() {
        return this.indexOrder;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public Date getStartSellTime() {
        return this.startSellTime;
    }

    public int getRest() {
        return this.rest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMailPrice(float mailPrice) {
        this.mailPrice = mailPrice;
    }

    public void setBuyLimit(int buyLimit) {
        this.buyLimit = buyLimit;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIndexOrder(int indexOrder) {
        this.indexOrder = indexOrder;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setStartSellTime(Date startSellTime) {
        this.startSellTime = startSellTime;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductAddRequest)) return false;
        final ProductAddRequest other = (ProductAddRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$thumbnailId = this.getThumbnailId();
        final Object other$thumbnailId = other.getThumbnailId();
        if (this$thumbnailId == null ? other$thumbnailId != null : !this$thumbnailId.equals(other$thumbnailId))
            return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        if (Float.compare(this.getMailPrice(), other.getMailPrice()) != 0) return false;
        if (this.getBuyLimit() != other.getBuyLimit()) return false;
        final Object this$isbn = this.getIsbn();
        final Object other$isbn = other.getIsbn();
        if (this$isbn == null ? other$isbn != null : !this$isbn.equals(other$isbn)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        if (this.getIndexOrder() != other.getIndexOrder()) return false;
        final Object this$publishDate = this.getPublishDate();
        final Object other$publishDate = other.getPublishDate();
        if (this$publishDate == null ? other$publishDate != null : !this$publishDate.equals(other$publishDate))
            return false;
        final Object this$categoryId = this.getCategoryId();
        final Object other$categoryId = other.getCategoryId();
        if (this$categoryId == null ? other$categoryId != null : !this$categoryId.equals(other$categoryId))
            return false;
        final Object this$startSellTime = this.getStartSellTime();
        final Object other$startSellTime = other.getStartSellTime();
        if (this$startSellTime == null ? other$startSellTime != null : !this$startSellTime.equals(other$startSellTime))
            return false;
        if (this.getRest() != other.getRest()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductAddRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $thumbnailId = this.getThumbnailId();
        result = result * PRIME + ($thumbnailId == null ? 43 : $thumbnailId.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        result = result * PRIME + Float.floatToIntBits(this.getMailPrice());
        result = result * PRIME + this.getBuyLimit();
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        result = result * PRIME + this.getIndexOrder();
        final Object $publishDate = this.getPublishDate();
        result = result * PRIME + ($publishDate == null ? 43 : $publishDate.hashCode());
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        final Object $startSellTime = this.getStartSellTime();
        result = result * PRIME + ($startSellTime == null ? 43 : $startSellTime.hashCode());
        result = result * PRIME + this.getRest();
        return result;
    }

    public String toString() {
        return "ProductAddRequest(name=" + this.getName() + ", thumbnailId=" + this.getThumbnailId() + ", price=" + this.getPrice() + ", mailPrice=" + this.getMailPrice() + ", buyLimit=" + this.getBuyLimit() + ", isbn=" + this.getIsbn() + ", author=" + this.getAuthor() + ", indexOrder=" + this.getIndexOrder() + ", publishDate=" + this.getPublishDate() + ", categoryId=" + this.getCategoryId() + ", startSellTime=" + this.getStartSellTime() + ", rest=" + this.getRest() + ")";
    }
}
