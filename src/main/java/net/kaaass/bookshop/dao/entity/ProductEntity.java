package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
public class ProductEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @Column(name = "product_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "thumbnail",
            unique = true)
    private MediaEntity thumbnail;

    @Column(name = "price")
    private float price;

    @Column(name = "mail_price")
    private float mailPrice;

    @Column(name = "buy_limit",
            columnDefinition = "INT DEFAULT -1")
    private int buyLimit = -1;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "index_order",
            columnDefinition = "INT DEFAULT -1")
    private int indexOrder = -1;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "author")
    private String author;

    @Column(name = "publish_date")
    private Timestamp publishDate;

    // TODO 添加belongTo字段，以实现商品选择不同颜色种类

    @OneToOne(targetEntity = ProductStorageEntity.class,
            cascade = {CascadeType.ALL})
    @JoinColumn(name = "storage_id")
    private ProductStorageEntity storage;

    @Column(name = "start_sell_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp startSellTime;

    @Column(name = "create_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp createTime;

    @Column(name = "last_update_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp lastUpdateTime;

    public ProductEntity() {
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public MediaEntity getThumbnail() {
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

    public CategoryEntity getCategory() {
        return this.category;
    }

    public int getIndexOrder() {
        return this.indexOrder;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuthor() {
        return this.author;
    }

    public Timestamp getPublishDate() {
        return this.publishDate;
    }

    public ProductStorageEntity getStorage() {
        return this.storage;
    }

    public Timestamp getStartSellTime() {
        return this.startSellTime;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public Timestamp getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setThumbnail(MediaEntity thumbnail) {
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

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public void setIndexOrder(int indexOrder) {
        this.indexOrder = indexOrder;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public void setStorage(ProductStorageEntity storage) {
        this.storage = storage;
    }

    public void setStartSellTime(Timestamp startSellTime) {
        this.startSellTime = startSellTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductEntity)) return false;
        final ProductEntity other = (ProductEntity) o;
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
        if (this.getIndexOrder() != other.getIndexOrder()) return false;
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
        final Object this$storage = this.getStorage();
        final Object other$storage = other.getStorage();
        if (this$storage == null ? other$storage != null : !this$storage.equals(other$storage)) return false;
        final Object this$startSellTime = this.getStartSellTime();
        final Object other$startSellTime = other.getStartSellTime();
        if (this$startSellTime == null ? other$startSellTime != null : !this$startSellTime.equals(other$startSellTime))
            return false;
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        final Object this$lastUpdateTime = this.getLastUpdateTime();
        final Object other$lastUpdateTime = other.getLastUpdateTime();
        return this$lastUpdateTime == null ? other$lastUpdateTime == null : this$lastUpdateTime.equals(other$lastUpdateTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductEntity;
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
        result = result * PRIME + this.getIndexOrder();
        final Object $isbn = this.getIsbn();
        result = result * PRIME + ($isbn == null ? 43 : $isbn.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $publishDate = this.getPublishDate();
        result = result * PRIME + ($publishDate == null ? 43 : $publishDate.hashCode());
        final Object $storage = this.getStorage();
        result = result * PRIME + ($storage == null ? 43 : $storage.hashCode());
        final Object $startSellTime = this.getStartSellTime();
        result = result * PRIME + ($startSellTime == null ? 43 : $startSellTime.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * PRIME + ($createTime == null ? 43 : $createTime.hashCode());
        final Object $lastUpdateTime = this.getLastUpdateTime();
        result = result * PRIME + ($lastUpdateTime == null ? 43 : $lastUpdateTime.hashCode());
        return result;
    }

    public String toString() {
        return "ProductEntity(id=" + this.getId() + ", name=" + this.getName() + ", thumbnail=" + this.getThumbnail() + ", price=" + this.getPrice() + ", mailPrice=" + this.getMailPrice() + ", buyLimit=" + this.getBuyLimit() + ", category=" + this.getCategory() + ", indexOrder=" + this.getIndexOrder() + ", isbn=" + this.getIsbn() + ", author=" + this.getAuthor() + ", publishDate=" + this.getPublishDate() + ", storage=" + this.getStorage() + ", startSellTime=" + this.getStartSellTime() + ", createTime=" + this.getCreateTime() + ", lastUpdateTime=" + this.getLastUpdateTime() + ")";
    }
}
