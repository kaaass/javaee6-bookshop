package net.kaaass.bookshop.controller.request;

import net.kaaass.bookshop.constraints.Uuid;

import javax.validation.constraints.Size;

/**
 * 分类添加请求
 *
 * @author kaaass
 */
public class CategoryAddRequest {

    @Size(
            message = "分类名字长度必须大于3小于20",
            min = 3,
            max = 20
    )
    private String name;

    @Uuid(message = "父类格式错误", nullable = true)
    private String parentId;

    public CategoryAddRequest() {
    }

    public String getName() {
        return this.name;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CategoryAddRequest)) return false;
        final CategoryAddRequest other = (CategoryAddRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$parentId = this.getParentId();
        final Object other$parentId = other.getParentId();
        return this$parentId == null ? other$parentId == null : this$parentId.equals(other$parentId);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CategoryAddRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $parentId = this.getParentId();
        result = result * PRIME + ($parentId == null ? 43 : $parentId.hashCode());
        return result;
    }

    public String toString() {
        return "CategoryAddRequest(name=" + this.getName() + ", parentId=" + this.getParentId() + ")";
    }
}
