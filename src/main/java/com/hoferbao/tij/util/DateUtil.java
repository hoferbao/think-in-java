package com.hoferbao.tij.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author hofer.bhf
 * created on 2018/05/31 4:01 PM
 */
public class DateUtil {
    public static void main(String[] args) {
        dateTimeFormatter();
    }

    private static void dateTimeFormatter() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        System.out.println(dateFormat.format(date));

        LocalDate localDate = LocalDate.now();
        localDate = localDate.plusDays(1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(df.format(localDate));
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
