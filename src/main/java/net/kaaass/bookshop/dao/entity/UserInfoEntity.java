package net.kaaass.bookshop.dao.entity;

import lombok.Data;
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
@Data
@Table(name = "user_info")
public class UserInfoEntity implements IEntity<String> {
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
}
