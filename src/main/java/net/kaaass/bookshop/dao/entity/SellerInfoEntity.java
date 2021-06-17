package net.kaaass.bookshop.dao.entity;

import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "seller_info")
public class SellerInfoEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @OneToOne
    @JoinColumn(name = "uid",
            unique = true)
    private UserAuthEntity auth;

    @Column(name = "wechat",
            columnDefinition = "TEXT DEFAULT NULL")
    private String wechat;

    @ManyToOne
    @JoinColumn(name = "avatar")
    private MediaEntity avatar;

    @Column(name = "last_update_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp lastUpdateTime;

    public SellerInfoEntity() {
    }

    public String getId() {
        return this.id;
    }

    public UserAuthEntity getAuth() {
        return this.auth;
    }

    public String getWechat() {
        return this.wechat;
    }

    public MediaEntity getAvatar() {
        return this.avatar;
    }

    public Timestamp getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuth(UserAuthEntity auth) {
        this.auth = auth;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public void setAvatar(MediaEntity avatar) {
        this.avatar = avatar;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SellerInfoEntity)) return false;
        final SellerInfoEntity other = (SellerInfoEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$auth = this.getAuth();
        final Object other$auth = other.getAuth();
        if (this$auth == null ? other$auth != null : !this$auth.equals(other$auth)) return false;
        final Object this$wechat = this.getWechat();
        final Object other$wechat = other.getWechat();
        if (this$wechat == null ? other$wechat != null : !this$wechat.equals(other$wechat)) return false;
        final Object this$avatar = this.getAvatar();
        final Object other$avatar = other.getAvatar();
        if (this$avatar == null ? other$avatar != null : !this$avatar.equals(other$avatar)) return false;
        final Object this$lastUpdateTime = this.getLastUpdateTime();
        final Object other$lastUpdateTime = other.getLastUpdateTime();
        return this$lastUpdateTime == null ? other$lastUpdateTime == null : this$lastUpdateTime.equals(other$lastUpdateTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SellerInfoEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $auth = this.getAuth();
        result = result * PRIME + ($auth == null ? 43 : $auth.hashCode());
        final Object $wechat = this.getWechat();
        result = result * PRIME + ($wechat == null ? 43 : $wechat.hashCode());
        final Object $avatar = this.getAvatar();
        result = result * PRIME + ($avatar == null ? 43 : $avatar.hashCode());
        final Object $lastUpdateTime = this.getLastUpdateTime();
        result = result * PRIME + ($lastUpdateTime == null ? 43 : $lastUpdateTime.hashCode());
        return result;
    }

    public String toString() {
        return "SellerInfoEntity(id=" + this.getId() + ", auth=" + this.getAuth() + ", wechat=" + this.getWechat() + ", avatar=" + this.getAvatar() + ", lastUpdateTime=" + this.getLastUpdateTime() + ")";
    }
}
