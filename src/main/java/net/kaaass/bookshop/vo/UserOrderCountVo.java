package net.kaaass.bookshop.vo;

import lombok.Data;

/**
 * 用户订单统计 VO
 * @author kaaass
 */
@Data
public class UserOrderCountVo {

    private int toPay;

    private int toDeliver;

    private int toComment;
}
