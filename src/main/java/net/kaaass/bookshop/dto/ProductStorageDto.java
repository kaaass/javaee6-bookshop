package net.kaaass.bookshop.dto;

public class ProductStorageDto {

    private String id;

    private int rest;

    public ProductStorageDto() {
    }

    public String getId() {
        return this.id;
    }

    public int getRest() {
        return this.rest;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ProductStorageDto)) return false;
        final ProductStorageDto other = (ProductStorageDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (this.getRest() != other.getRest()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ProductStorageDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        result = result * PRIME + this.getRest();
        return result;
    }

    public String toString() {
        return "ProductStorageDto(id=" + this.getId() + ", rest=" + this.getRest() + ")";
    }
}
