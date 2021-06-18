package bookshop.dao;

/**
 * 分页
 *
 * @author kaaass
 */
public class Pageable {

    private final int page;
    private final int size;

    public Pageable(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("Page index must not be less than zero!");
        } else if (size < 1) {
            throw new IllegalArgumentException("Page size must not be less than one!");
        } else {
            this.page = page;
            this.size = size;
        }
    }

    public int getPageSize() {
        return this.size;
    }

    public int getPageNumber() {
        return this.page;
    }

    public int getOffset() {
        return this.page * this.size;
    }

    public boolean hasPrevious() {
        return this.page > 0;
    }

    public Pageable previousOrFirst() {
        return this.hasPrevious() ? this.previous() : this.first();
    }

    public Pageable next() {
        return new Pageable(this.getPageNumber() + 1, this.getPageSize());
    }

    public Pageable previous() {
        return this.getPageNumber() == 0 ? this : new Pageable(this.getPageNumber() - 1, this.getPageSize());
    }

    public Pageable first() {
        return new Pageable(0, this.getPageSize());
    }

    public static Pageable of(int page, int size) {
        return new Pageable(page, size);
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Pageable)) return false;
        final Pageable other = (Pageable) o;
        if (!other.canEqual(this)) return false;
        if (this.page != other.page) return false;
        return this.size == other.size;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Pageable;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.page;
        result = result * PRIME + this.size;
        return result;
    }

    public String toString() {
        return "Pageable(page=" + this.page + ", size=" + this.size + ")";
    }
}
