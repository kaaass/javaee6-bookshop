package net.kaaass.bookshop.controller.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserAddressRequest {

    @Size(
            message = "地区必须在3~20字之间",
            min = 3,
            max = 20
    )
    private String area;

    @Size(
            message = "详细地址必须在5~20字之间",
            min = 5,
            max = 20
    )
    private String detailAddress;

    @Digits(
            message = "邮编必须为6位数字",
            integer = 6,
            fraction = 0
    )
    private String mailCode;


    @Pattern(
            message = "手机号格式错误",
            regexp = "^\\d{11}$"
    )
    private String phone;

    @Size(
            message = "姓名必须在2~20字之间",
            min = 2,
            max = 20
    )
    private String name;

    public UserAddressRequest() {
    }

    public String getArea() {
        return this.area;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public String getMailCode() {
        return this.mailCode;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getName() {
        return this.name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public void setMailCode(String mailCode) {
        this.mailCode = mailCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAddressRequest)) return false;
        final UserAddressRequest other = (UserAddressRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$area = this.getArea();
        final Object other$area = other.getArea();
        if (this$area == null ? other$area != null : !this$area.equals(other$area)) return false;
        final Object this$detailAddress = this.getDetailAddress();
        final Object other$detailAddress = other.getDetailAddress();
        if (this$detailAddress == null ? other$detailAddress != null : !this$detailAddress.equals(other$detailAddress))
            return false;
        final Object this$mailCode = this.getMailCode();
        final Object other$mailCode = other.getMailCode();
        if (this$mailCode == null ? other$mailCode != null : !this$mailCode.equals(other$mailCode)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAddressRequest;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $area = this.getArea();
        result = result * PRIME + ($area == null ? 43 : $area.hashCode());
        final Object $detailAddress = this.getDetailAddress();
        result = result * PRIME + ($detailAddress == null ? 43 : $detailAddress.hashCode());
        final Object $mailCode = this.getMailCode();
        result = result * PRIME + ($mailCode == null ? 43 : $mailCode.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    public String toString() {
        return "UserAddressRequest(area=" + this.getArea() + ", detailAddress=" + this.getDetailAddress() + ", mailCode=" + this.getMailCode() + ", phone=" + this.getPhone() + ", name=" + this.getName() + ")";
    }
}
