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
}
