package net.kaaass.bookshop.dao.entity;

import lombok.Data;
import net.kaaass.bookshop.dao.IEntity;
import net.kaaass.bookshop.dto.OrderType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "table_order")
public class OrderEntity implements IEntity<String> {
    /**
     * 订单号
     * <p>
     * 格式：yyyyMMdd + 当日4位自增
     */
    @Id
    private String id;

    @Column(name = "uid")
    private String uid;

    @OneToOne
    @JoinColumn(name = "address_id")
    private UserAddressEntity address;

    @Column(name = "request_id",
            unique = true)
    private String requestId;

    @Column(name = "order_type")
    @Enumerated(EnumType.STRING)
    private OrderType type = OrderType.CREATED;

    @Column(name = "price")
    private float price;

    @Column(name = "mail_price")
    private float mailPrice;

    /**
     * 运单号
     */
    @Column(name = "deliver_code")
    private String deliverCode = null;

    /**
     * 各类原因
     */
    @Column(name = "reason")
    private String reason = null;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> products = new ArrayList<>();

    @Column(name = "create_time",
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Generated(GenerationTime.INSERT)
    private Timestamp createTime;

    @Column(name = "pay_time")
    private Timestamp payTime;

    @Column(name = "deliver_time")
    private Timestamp deliverTime;

    @Column(name = "finish_time")
    private Timestamp finishTime;

    @Column(name = "refund_time")
    private Timestamp refundTime;
}
