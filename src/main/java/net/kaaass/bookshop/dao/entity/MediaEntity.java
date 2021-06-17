package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "media")
public class MediaEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "url")
    private String url;

    @Column(name = "uploader_uid")
    private String uploaderUid = "00000000000000000000000000000000";

    @OneToMany(mappedBy = "thumbnail", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<ProductEntity> products = new ArrayList<>();

    @Column(name = "upload_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp uploadTime;

    public MediaEntity() {
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUploaderUid() {
        return this.uploaderUid;
    }

    public List<ProductEntity> getProducts() {
        return this.products;
    }

    public Timestamp getUploadTime() {
        return this.uploadTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUploaderUid(String uploaderUid) {
        this.uploaderUid = uploaderUid;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof MediaEntity)) return false;
        final MediaEntity other = (MediaEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$url = this.getUrl();
        final Object other$url = other.getUrl();
        if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
        final Object this$uploaderUid = this.getUploaderUid();
        final Object other$uploaderUid = other.getUploaderUid();
        if (this$uploaderUid == null ? other$uploaderUid != null : !this$uploaderUid.equals(other$uploaderUid))
            return false;
        final Object this$products = this.getProducts();
        final Object other$products = other.getProducts();
        if (this$products == null ? other$products != null : !this$products.equals(other$products)) return false;
        final Object this$uploadTime = this.getUploadTime();
        final Object other$uploadTime = other.getUploadTime();
        return this$uploadTime == null ? other$uploadTime == null : this$uploadTime.equals(other$uploadTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof MediaEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $url = this.getUrl();
        result = result * PRIME + ($url == null ? 43 : $url.hashCode());
        final Object $uploaderUid = this.getUploaderUid();
        result = result * PRIME + ($uploaderUid == null ? 43 : $uploaderUid.hashCode());
        final Object $products = this.getProducts();
        result = result * PRIME + ($products == null ? 43 : $products.hashCode());
        final Object $uploadTime = this.getUploadTime();
        result = result * PRIME + ($uploadTime == null ? 43 : $uploadTime.hashCode());
        return result;
    }

    public String toString() {
        return "MediaEntity(id=" + this.getId() + ", type=" + this.getType() + ", url=" + this.getUrl() + ", uploaderUid=" + this.getUploaderUid() + ", uploadTime=" + this.getUploadTime() + ")";
    }
}
