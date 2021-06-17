package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "product_metadata")
public class ProductMetadataEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @Column(name = "product_id")
    private String productId; // TODO manytoone

    @Column(name = "meta_key")
    private String key;

    @Column(name = "meta_value",
            columnDefinition = "TEXT DEFAULT NULL")
    private String value;

    @Column(name = "last_update_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp lastUpdateTime;

    public ProductMetadataEntity() {
    }

    public String getId() {
        return this.id;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    public Timestamp getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductMetadataEntity)) return false;
        final ProductMetadataEntity other = (ProductMetadataEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$productId = this.getProductId();
        final Object other$productId = other.getProductId();
        if (this$productId == null ? other$productId != null : !this$productId.equals(other$productId)) return false;
        final Object this$key = this.getKey();
        final Object other$key = other.getKey();
        if (this$key == null ? other$key != null : !this$key.equals(other$key)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$lastUpdateTime = this.getLastUpdateTime();
        final Object other$lastUpdateTime = other.getLastUpdateTime();
        return this$lastUpdateTime == null ? other$lastUpdateTime == null : this$lastUpdateTime.equals(other$lastUpdateTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductMetadataEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $productId = this.getProductId();
        result = result * PRIME + ($productId == null ? 43 : $productId.hashCode());
        final Object $key = this.getKey();
        result = result * PRIME + ($key == null ? 43 : $key.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $lastUpdateTime = this.getLastUpdateTime();
        result = result * PRIME + ($lastUpdateTime == null ? 43 : $lastUpdateTime.hashCode());
        return result;
    }

    public String toString() {
        return "ProductMetadataEntity(id=" + this.getId() + ", productId=" + this.getProductId() + ", key=" + this.getKey() + ", value=" + this.getValue() + ", lastUpdateTime=" + this.getLastUpdateTime() + ")";
    }
}
