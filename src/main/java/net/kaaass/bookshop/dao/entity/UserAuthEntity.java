package net.kaaass.bookshop.dao.entity;

import lombok.Data;
import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.util.Constants;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
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

    @Column(name = "register_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp registerTime;

    @Column(name = "last_login_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp lastLoginTime;
}
