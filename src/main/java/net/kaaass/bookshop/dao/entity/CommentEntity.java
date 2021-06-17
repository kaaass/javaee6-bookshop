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
@Table(name = "comments")
public class CommentEntity implements IEntity<String> {
    @Id
    @GenericGenerator(name = Constants.ID_GENERATOR, strategy = Constants.UUID)
    @GeneratedValue(generator = Constants.ID_GENERATOR)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "uid")
    private UserAuthEntity user;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "rate")
    private int rate;

    @Column(name = "content")
    private String content;

    @Column(name = "comment_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            insertable = false,
            updatable = false)
    @Generated(GenerationTime.INSERT)
    private Timestamp commentTime;
}
