package net.kaaass.bookshop.util;

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
        final String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        final String oldOrderNumStr = lastOrderId.substring(lastOrderId.length() - 4, lastOrderId.length());
        final int oldNum = Integer.parseInt(oldOrderNumStr);
        final String orderNum = String.format("%04d", oldNum + 1);
        return dateStr + orderNum;
    }
}
