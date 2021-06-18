package bookshop.controller.page;

import bookshop.dao.Pageable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named("pageInfo")
public class PageInfo implements Serializable {

    private Pageable pageable;

    public PageInfo() {
    }

    public Pageable getPageable() {
        return this.pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PageInfo)) return false;
        final PageInfo other = (PageInfo) o;
        if (!other.canEqual(this)) return false;
        final Object this$pageable = this.getPageable();
        final Object other$pageable = other.getPageable();
        return this$pageable == null ? other$pageable == null : this$pageable.equals(other$pageable);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PageInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $pageable = this.getPageable();
        result = result * PRIME + ($pageable == null ? 43 : $pageable.hashCode());
        return result;
    }

    public String toString() {
        return "PageInfo(pageable=" + this.getPageable() + ")";
    }
}
