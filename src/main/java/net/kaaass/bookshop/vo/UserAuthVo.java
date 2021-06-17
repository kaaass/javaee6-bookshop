package net.kaaass.bookshop.vo;

public class UserAuthVo {
    private String id;

    private String phone;

    private String role;

    public UserAuthVo() {
    }

    public String getId() {
        return this.id;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAuthVo)) return false;
        final UserAuthVo other = (UserAuthVo) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        return this$role == null ? other$role == null : this$role.equals(other$role);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAuthVo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        return result;
    }

    public String toString() {
        return "UserAuthVo(id=" + this.getId() + ", phone=" + this.getPhone() + ", role=" + this.getRole() + ")";
    }
}
