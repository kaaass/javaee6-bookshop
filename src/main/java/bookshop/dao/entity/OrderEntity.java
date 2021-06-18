package bookshop.dao.entity;

import bookshop.dao.IEntity;
import bookshop.dto.OrderType;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
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

    public OrderEntity() {
    }

    public String getId() {
        return this.id;
    }

    public String getUid() {
        return this.uid;
    }

    public UserAddressEntity getAddress() {
        return this.address;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public OrderType getType() {
        return this.type;
    }

    public float getPrice() {
        return this.price;
    }

    public float getMailPrice() {
        return this.mailPrice;
    }

    public String getDeliverCode() {
        return this.deliverCode;
    }

    public String getReason() {
        return this.reason;
    }

    public List<OrderItemEntity> getProducts() {
        return this.products;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public Timestamp getPayTime() {
        return this.payTime;
    }

    public Timestamp getDeliverTime() {
        return this.deliverTime;
    }

    public Timestamp getFinishTime() {
        return this.finishTime;
    }

    public Timestamp getRefundTime() {
        return this.refundTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setAddress(UserAddressEntity address) {
        this.address = address;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setMailPrice(float mailPrice) {
        this.mailPrice = mailPrice;
    }

    public void setDeliverCode(String deliverCode) {
        this.deliverCode = deliverCode;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setProducts(List<OrderItemEntity> products) {
        this.products = products;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setPayTime(Timestamp payTime) {
        this.payTime = payTime;
    }

    public void setDeliverTime(Timestamp deliverTime) {
        this.deliverTime = deliverTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public void setRefundTime(Timestamp refundTime) {
        this.refundTime = refundTime;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderEntity)) return false;
        final OrderEntity other = (OrderEntity) o;
        if (!other.canEqual(this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$uid = this.getUid();
        final Object other$uid = other.getUid();
        if (this$uid == null ? other$uid != null : !this$uid.equals(other$uid)) return false;
        final Object this$address = this.getAddress();
        final Object other$address = other.getAddress();
        if (this$address == null ? other$address != null : !this$address.equals(other$address)) return false;
        final Object this$requestId = this.getRequestId();
        final Object other$requestId = other.getRequestId();
        if (this$requestId == null ? other$requestId != null : !this$requestId.equals(other$requestId)) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        if (Float.compare(this.getPrice(), other.getPrice()) != 0) return false;
        if (Float.compare(this.getMailPrice(), other.getMailPrice()) != 0) return false;
        final Object this$deliverCode = this.getDeliverCode();
        final Object other$deliverCode = other.getDeliverCode();
        if (this$deliverCode == null ? other$deliverCode != null : !this$deliverCode.equals(other$deliverCode))
            return false;
        final Object this$reason = this.getReason();
        final Object other$reason = other.getReason();
        if (this$reason == null ? other$reason != null : !this$reason.equals(other$reason)) return false;
        final Object this$products = this.getProducts();
        final Object other$products = other.getProducts();
        if (this$products == null ? other$products != null : !this$products.equals(other$products)) return false;
        final Object this$createTime = this.getCreateTime();
        final Object other$createTime = other.getCreateTime();
        if (this$createTime == null ? other$createTime != null : !this$createTime.equals(other$createTime))
            return false;
        final Object this$payTime = this.getPayTime();
        final Object other$payTime = other.getPayTime();
        if (this$payTime == null ? other$payTime != null : !this$payTime.equals(other$payTime)) return false;
        final Object this$deliverTime = this.getDeliverTime();
        final Object other$deliverTime = other.getDeliverTime();
        if (this$deliverTime == null ? other$deliverTime != null : !this$deliverTime.equals(other$deliverTime))
            return false;
        final Object this$finishTime = this.getFinishTime();
        final Object other$finishTime = other.getFinishTime();
        if (this$finishTime == null ? other$finishTime != null : !this$finishTime.equals(other$finishTime))
            return false;
        final Object this$refundTime = this.getRefundTime();
        final Object other$refundTime = other.getRefundTime();
        return this$refundTime == null ? other$refundTime == null : this$refundTime.equals(other$refundTime);
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $uid = this.getUid();
        result = result * PRIME + ($uid == null ? 43 : $uid.hashCode());
        final Object $address = this.getAddress();
        result = result * PRIME + ($address == null ? 43 : $address.hashCode());
        final Object $requestId = this.getRequestId();
        result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        result = result * PRIME + Float.floatToIntBits(this.getPrice());
        result = result * PRIME + Float.floatToIntBits(this.getMailPrice());
        final Object $deliverCode = this.getDeliverCode();
        result = result * PRIME + ($deliverCode == null ? 43 : $deliverCode.hashCode());
        final Object $reason = this.getReason();
        result = result * PRIME + ($reason == null ? 43 : $reason.hashCode());
        final Object $products = this.getProducts();
        result = result * PRIME + ($products == null ? 43 : $products.hashCode());
        final Object $createTime = this.getCreateTime();
        result = result * PRIME + ($createTime == null ? 43 : $createTime.hashCode());
        final Object $payTime = this.getPayTime();
        result = result * PRIME + ($payTime == null ? 43 : $payTime.hashCode());
        final Object $deliverTime = this.getDeliverTime();
        result = result * PRIME + ($deliverTime == null ? 43 : $deliverTime.hashCode());
        final Object $finishTime = this.getFinishTime();
        result = result * PRIME + ($finishTime == null ? 43 : $finishTime.hashCode());
        final Object $refundTime = this.getRefundTime();
        result = result * PRIME + ($refundTime == null ? 43 : $refundTime.hashCode());
        return result;
    }

    public String toString() {
        return "OrderEntity(id=" + this.getId() + ", uid=" + this.getUid() + ", address=" + this.getAddress() + ", requestId=" + this.getRequestId() + ", type=" + this.getType() + ", price=" + this.getPrice() + ", mailPrice=" + this.getMailPrice() + ", deliverCode=" + this.getDeliverCode() + ", reason=" + this.getReason() + ", products=" + this.getProducts() + ", createTime=" + this.getCreateTime() + ", payTime=" + this.getPayTime() + ", deliverTime=" + this.getDeliverTime() + ", finishTime=" + this.getFinishTime() + ", refundTime=" + this.getRefundTime() + ")";
    }
}
