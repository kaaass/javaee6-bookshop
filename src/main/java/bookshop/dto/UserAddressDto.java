package bookshop.dto;

/**
 * 用户地址 DTO
 *
 * @author kaaass
 */
public class UserAddressDto {

    private String id;

    private String area;

    private String detailAddress;

    private String mailCode;

    private String phone;

    private String name;

    private boolean defaultAddress;

    public UserAddressDto() {
    }

    public String getId() {
        return this.id;
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

    public boolean isDefaultAddress() {
        return this.defaultAddress;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAddressDto)) return false;
        final UserAddressDto other = (UserAddressDto) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
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
        return this.isDefaultAddress() == other.isDefaultAddress();
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAddressDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
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
        result = result * PRIME + (this.isDefaultAddress() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "UserAddressDto(id=" + this.getId() + ", area=" + this.getArea() + ", detailAddress=" + this.getDetailAddress() + ", mailCode=" + this.getMailCode() + ", phone=" + this.getPhone() + ", name=" + this.getName() + ", defaultAddress=" + this.isDefaultAddress() + ")";
    }
}
