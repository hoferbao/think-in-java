package com.hofer.tij.util;

import java.util.Date;

/**
 * @author hofer.bhf
 * created on 2018/05/31 4:01 PM
 */
public class DateUtil {
    public static void main(String[] args) {
        System.out.println(getDateFromMilliseconds(1527647870033L));
    }

    /**
     * 毫秒数转时间
     *
     * @param milliseconds
     * @return
     */
    private static Date getDateFromMilliseconds(long milliseconds) {
        Date date = new Date();
        date.setTime(milliseconds);
        return date;
    }
}
