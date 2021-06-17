package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "product_storage")
public class ProductStorageEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @OneToOne(mappedBy = "storage")
    private ProductEntity product;

    @Column(name = "rest")
    private int rest;

    public ProductStorageEntity() {
    }

    public String getId() {
        return this.id;
    }

    public ProductEntity getProduct() {
        return this.product;
    }

    public int getRest() {
        return this.rest;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductStorageEntity)) return false;
        final ProductStorageEntity other = (ProductStorageEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$product = this.getProduct();
        final Object other$product = other.getProduct();
        if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
        if (this.getRest() != other.getRest()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductStorageEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $product = this.getProduct();
        result = result * PRIME + ($product == null ? 43 : $product.hashCode());
        result = result * PRIME + this.getRest();
        return result;
    }

    public String toString() {
        return "ProductStorageEntity(id=" + this.getId() + ", product=" + this.getProduct() + ", rest=" + this.getRest() + ")";
    }
}
