package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_auth")
public class UserAuthEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @Column(name = "phone",
            length = 20,
            unique = true)
    private String phone;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "auth_token")
    private String authToken;

    @OneToOne(mappedBy = "auth", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private UserInfoEntity userInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<UserAddressEntity> addresses = new ArrayList<>();

    @Column(name = "register_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp registerTime;

    @Column(name = "last_login_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastLoginTime;

    public UserAuthEntity() {
    }

    public String getId() {
        return this.id;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public String getRole() {
        return this.role;
    }

    public String getAuthToken() {
        return this.authToken;
    }

    public UserInfoEntity getUserInfo() {
        return this.userInfo;
    }

    public List<UserAddressEntity> getAddresses() {
        return this.addresses;
    }

    public Timestamp getRegisterTime() {
        return this.registerTime;
    }

    public Timestamp getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUserInfo(UserInfoEntity userInfo) {
        this.userInfo = userInfo;
    }

    public void setAddresses(List<UserAddressEntity> addresses) {
        this.addresses = addresses;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public void setLastLoginTime(Timestamp lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UserAuthEntity)) return false;
        final UserAuthEntity other = (UserAuthEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$role = this.getRole();
        final Object other$role = other.getRole();
        if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
        final Object this$authToken = this.getAuthToken();
        final Object other$authToken = other.getAuthToken();
        if (this$authToken == null ? other$authToken != null : !this$authToken.equals(other$authToken)) return false;
        final Object this$userInfo = this.getUserInfo();
        final Object other$userInfo = other.getUserInfo();
        if (this$userInfo == null ? other$userInfo != null : !this$userInfo.equals(other$userInfo)) return false;
        final Object this$addresses = this.getAddresses();
        final Object other$addresses = other.getAddresses();
        if (this$addresses == null ? other$addresses != null : !this$addresses.equals(other$addresses)) return false;
        final Object this$registerTime = this.getRegisterTime();
        final Object other$registerTime = other.getRegisterTime();
        if (this$registerTime == null ? other$registerTime != null : !this$registerTime.equals(other$registerTime))
            return false;
        final Object this$lastLoginTime = this.getLastLoginTime();
        final Object other$lastLoginTime = other.getLastLoginTime();
        return this$lastLoginTime == null ? other$lastLoginTime == null : this$lastLoginTime.equals(other$lastLoginTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UserAuthEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $role = this.getRole();
        result = result * PRIME + ($role == null ? 43 : $role.hashCode());
        final Object $authToken = this.getAuthToken();
        result = result * PRIME + ($authToken == null ? 43 : $authToken.hashCode());
        final Object $userInfo = this.getUserInfo();
        result = result * PRIME + ($userInfo == null ? 43 : $userInfo.hashCode());
        final Object $addresses = this.getAddresses();
        result = result * PRIME + ($addresses == null ? 43 : $addresses.hashCode());
        final Object $registerTime = this.getRegisterTime();
        result = result * PRIME + ($registerTime == null ? 43 : $registerTime.hashCode());
        final Object $lastLoginTime = this.getLastLoginTime();
        result = result * PRIME + ($lastLoginTime == null ? 43 : $lastLoginTime.hashCode());
        return result;
    }

    public String toString() {
        return "UserAuthEntity(id=" + this.getId() + ", phone=" + this.getPhone() + ", password=" + this.getPassword() + ", role=" + this.getRole() + ", authToken=" + this.getAuthToken() + ", userInfo=" + this.getUserInfo() + ", addresses=" + this.getAddresses() + ", registerTime=" + this.getRegisterTime() + ", lastLoginTime=" + this.getLastLoginTime() + ")";
    }
}
