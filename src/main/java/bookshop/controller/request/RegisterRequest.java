package bookshop.controller.request;

import javax.validation.constraints.Pattern;

/**
 * 账户注册请求
 *
 * @author kaaass
 */
public class RegisterRequest {
    /**
     * 注册手机号
     */
    @Pattern(
            message = "手机号格式错误",
            regexp = "^\\d{11}$"
    )
    private String phone;

    /**
     * 密码
     */
    @Pattern(
            message = "密码应至少包含大写、小写、数字，长度6~20",
            regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{6,20}$"
    )
    private String password;

    public RegisterRequest() {
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterRequest)) return false;
        final RegisterRequest other = (RegisterRequest) o;
        if (!other.canEqual(this)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        return this$password == null ? other$password == null : this$password.equals(other$password);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof RegisterRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "RegisterRequest(phone=" + this.getPhone() + ", password=" + this.getPassword() + ")";
    }
}
