package net.kaaass.bookshop.dto;

/**
 * 订单内容 DTO
 * @author kaaass
 */
public class OrderItemDto {

    private String id;

    private ProductDto product;

    private float price;

    private int count;

    public OrderItemDto() {
    }

    public String getId() {
        return this.id;
    }

    public ProductDto getProduct() {
        return this.product;
    }

    public float getPrice() {
        return this.price;
    }

    public int getCount() {
        return this.count;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderItemDto)) return false;
        final OrderItemDto other = (OrderItemDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$product = this.getProduct();
        final Object other$product = other.getProduct();
        if (this$product == null ? other$product != null : !this$product.equals(other$product)) return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        if (this.getCount() != other.getCount()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderItemDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $product = this.getProduct();
        result = result * PRIME + ($product == null ? 43 : $product.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        result = result * PRIME + this.getCount();
        return result;
    }

    public String toString() {
        return "OrderItemDto(id=" + this.getId() + ", product=" + this.getProduct() + ", price=" + this.getPrice() + ", count=" + this.getCount() + ")";
    }
}
