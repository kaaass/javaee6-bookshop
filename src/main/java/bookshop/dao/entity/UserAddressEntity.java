package bookshop.dao.entity;

import bookshop.dao.IEntity;
import bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_address")
public class UserAddressEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "uid")
    private UserAuthEntity user;

    @Column(name = "area",
            columnDefinition = "TEXT DEFAULT NULL")
    private String area;

    @Column(name = "detail_address",
            columnDefinition = "TEXT DEFAULT NULL")
    private String detailAddress;

    @Column(name = "mail_code",
            columnDefinition = "TEXT DEFAULT NULL",
            length = 10)
    private String mailCode;

    @Column(name = "phone",
            columnDefinition = "TEXT DEFAULT NULL",
            length = 20)
    private String phone;

    @Column(name = "name",
            columnDefinition = "TEXT DEFAULT NULL")
    private String name;

    @Column(name = "default_address")
    private boolean defaultAddress = false;

    @Column(name = "last_update_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp lastUpdateTime;

    public UserAddressEntity() {
    }

    public String getId() {
        return this.id;
    }

    public UserAuthEntity getUser() {
        return this.user;
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

    public Timestamp getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUser(UserAuthEntity user) {
        this.user = user;
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

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAddressEntity)) return false;
        final UserAddressEntity other = (UserAddressEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$user = this.getUser();
        final Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
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
        if (this.isDefaultAddress() != other.isDefaultAddress()) return false;
        final Object this$lastUpdateTime = this.getLastUpdateTime();
        final Object other$lastUpdateTime = other.getLastUpdateTime();
        return this$lastUpdateTime == null ? other$lastUpdateTime == null : this$lastUpdateTime.equals(other$lastUpdateTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAddressEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
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
        final Object $lastUpdateTime = this.getLastUpdateTime();
        result = result * PRIME + ($lastUpdateTime == null ? 43 : $lastUpdateTime.hashCode());
        return result;
    }

    public String toString() {
        return "UserAddressEntity(id=" + this.getId() + ", user=" + this.getUser() + ", area=" + this.getArea() + ", detailAddress=" + this.getDetailAddress() + ", mailCode=" + this.getMailCode() + ", phone=" + this.getPhone() + ", name=" + this.getName() + ", defaultAddress=" + this.isDefaultAddress() + ", lastUpdateTime=" + this.getLastUpdateTime() + ")";
    }
}
