package com.air.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author pd_liu on 2018/6/8.
 * <p>
 * TimeUtil
 * 时间处理工具类
 * </p>
 */

public final class TimeUtil {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private TimeUtil() {
        throw new UnsupportedOperationException("非法的创建对象");
    }

    public static String getTime() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        Date date = calendar.getTime();
        return SIMPLE_DATE_FORMAT.format(date);

    }

    private String append(int year, int month, int day, int hour, int minute, int second) {
        StringBuffer sb = new StringBuffer();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append(" ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);
        sb.append(":");
        sb.append(second);

        return sb.toString();
    }
}
