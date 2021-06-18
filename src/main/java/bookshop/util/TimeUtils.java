package bookshop.util;

import java.sql.Timestamp;
import java.util.Date;

public class TimeUtils {

    public static Timestamp dateToTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public static Date timestampToDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public static Timestamp nowTimestamp() {
        return dateToTimestamp(new Date());
    }

    public static Date dayShift(Date date, int offsetDay) {
        return new Date(date.getTime() + offsetDay * 24 * 60 * 60 * 1000L);
    }

    public static Date dayStart(Date date) {
        long cur = date.getTime();
        return new Date(cur - (cur % (24 * 60 * 60 * 1000L)) + Constants.TIME_OFFSET * 60 * 60 * 1000L);
    }
}
