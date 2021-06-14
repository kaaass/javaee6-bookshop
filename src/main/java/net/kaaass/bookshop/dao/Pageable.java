package net.kaaass.bookshop.dao;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 分页
 *
 * @author kaaass
 */
@ToString
@EqualsAndHashCode
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
}
