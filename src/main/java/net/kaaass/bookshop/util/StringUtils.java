package net.kaaass.bookshop.util;

import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String orderId(String lastOrderId) {
        if (lastOrderId.length() != 12) {
            return Constants.INIT_ORDER_ID;
        }
        val dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        val oldOrderNumStr = lastOrderId.substring(lastOrderId.length() - 4, lastOrderId.length());
        val oldNum = Integer.valueOf(oldOrderNumStr);
        val orderNum = String.format("%04d", oldNum + 1);
        return dateStr + orderNum;
    }
}
