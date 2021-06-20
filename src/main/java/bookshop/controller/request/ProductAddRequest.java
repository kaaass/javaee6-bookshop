package bookshop.controller.request;

import bookshop.constraints.Isbn;
import bookshop.constraints.Uuid;
import bookshop.util.LongToDateDeserializer;
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

    private String thumbnailId;

    @Min(value = 0, message = "价格不能为负")
    private float price;

    @Min(value = 0, message = "价格不能为负")
    private float mailPrice;

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

    public void setRest(int rest) {
        this.rest = rest;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductAddRequest)) return false;
        final ProductAddRequest other = (ProductAddRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$thumbnailId = this.getThumbnailId();
        final Object other$thumbnailId = other.getThumbnailId();
        if (this$thumbnailId == null ? other$thumbnailId != null : !this$thumbnailId.equals(other$thumbnailId))
            return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        if (Float.compare(this.getMailPrice(), other.getMailPrice()) != 0) return false;
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
        return this.getRest() == other.getRest();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductAddRequest;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $thumbnailId = this.getThumbnailId();
        result = result * PRIME + ($thumbnailId == null ? 43 : $thumbnailId.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        result = result * PRIME + Float.floatToIntBits(this.getMailPrice());
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        result = result * PRIME + this.getIndexOrder();
        final Object $publishDate = this.getPublishDate();
        result = result * PRIME + ($publishDate == null ? 43 : $publishDate.hashCode());
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        result = result * PRIME + this.getRest();
        return result;
    }

    @Override
    public String toString() {
        return "ProductAddRequest(name=" + this.getName() + ", thumbnailId=" + this.getThumbnailId() + ", price=" + this.getPrice() + ", mailPrice=" + this.getMailPrice() + ", isbn=" + this.getIsbn() + ", author=" + this.getAuthor() + ", indexOrder=" + this.getIndexOrder() + ", publishDate=" + this.getPublishDate() + ", categoryId=" + this.getCategoryId() + ", rest=" + this.getRest() + ")";
    }
}
