package bookshop.dto;

import bookshop.util.DateToLongSerializer;
import bookshop.util.LongToDateDeserializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 商品 DTO
 *
 * @author kaaass
 */
public class ProductDto {
    private String id;

    private String name;

    private MediaDto thumbnail;

    private float price;

    private float mailPrice;

    private int buyLimit;

    private CategoryDto category;

    private String isbn;

    private String author;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date publishDate;

    private int indexOrder;

    private ProductStorageDto storage;

    @JsonDeserialize(using = LongToDateDeserializer.class)
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date startSellTime;

    public ProductDto() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MediaDto getThumbnail() {
        return this.thumbnail;
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

    public CategoryDto getCategory() {
        return this.category;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public Date getPublishDate() {
        return this.publishDate;
    }

    public int getIndexOrder() {
        return this.indexOrder;
    }

    public ProductStorageDto getStorage() {
        return this.storage;
    }

    public Date getStartSellTime() {
        return this.startSellTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(MediaDto thumbnail) {
        this.thumbnail = thumbnail;
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

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public void setIndexOrder(int indexOrder) {
        this.indexOrder = indexOrder;
    }

    public void setStorage(ProductStorageDto storage) {
        this.storage = storage;
    }

    public void setStartSellTime(Date startSellTime) {
        this.startSellTime = startSellTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductDto)) return false;
        final ProductDto other = (ProductDto) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$thumbnail = this.getThumbnail();
        final Object other$thumbnail = other.getThumbnail();
        if (this$thumbnail == null ? other$thumbnail != null : !this$thumbnail.equals(other$thumbnail)) return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        if (Float.compare(this.getMailPrice(), other.getMailPrice()) != 0) return false;
        if (this.getBuyLimit() != other.getBuyLimit()) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final Object this$isbn = this.getIsbn();
        final Object other$isbn = other.getIsbn();
        if (this$isbn == null ? other$isbn != null : !this$isbn.equals(other$isbn)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        final Object this$publishDate = this.getPublishDate();
        final Object other$publishDate = other.getPublishDate();
        if (this$publishDate == null ? other$publishDate != null : !this$publishDate.equals(other$publishDate))
            return false;
        if (this.getIndexOrder() != other.getIndexOrder()) return false;
        final Object this$storage = this.getStorage();
        final Object other$storage = other.getStorage();
        if (this$storage == null ? other$storage != null : !this$storage.equals(other$storage)) return false;
        final Object this$startSellTime = this.getStartSellTime();
        final Object other$startSellTime = other.getStartSellTime();
        return this$startSellTime == null ? other$startSellTime == null : this$startSellTime.equals(other$startSellTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $thumbnail = this.getThumbnail();
        result = result * PRIME + ($thumbnail == null ? 43 : $thumbnail.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        result = result * PRIME + Float.floatToIntBits(this.getMailPrice());
        result = result * PRIME + this.getBuyLimit();
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $publishDate = this.getPublishDate();
        result = result * PRIME + ($publishDate == null ? 43 : $publishDate.hashCode());
        result = result * PRIME + this.getIndexOrder();
        final Object $storage = this.getStorage();
        result = result * PRIME + ($storage == null ? 43 : $storage.hashCode());
        final Object $startSellTime = this.getStartSellTime();
        result = result * PRIME + ($startSellTime == null ? 43 : $startSellTime.hashCode());
        return result;
    }

    public String toString() {
        return "ProductDto(id=" + this.getId() + ", name=" + this.getName() + ", thumbnail=" + this.getThumbnail() + ", price=" + this.getPrice() + ", mailPrice=" + this.getMailPrice() + ", buyLimit=" + this.getBuyLimit() + ", category=" + this.getCategory() + ", isbn=" + this.getIsbn() + ", author=" + this.getAuthor() + ", publishDate=" + this.getPublishDate() + ", indexOrder=" + this.getIndexOrder() + ", storage=" + this.getStorage() + ", startSellTime=" + this.getStartSellTime() + ")";
    }
}