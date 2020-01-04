package com.helloworld.transport.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class description
 * 日期工具类，用于实现日期类型和字符串类型的转换
 *
 * @author LiangHang
 * @createTime 2019/11/17 23:41
 */
public class DateUtils {
    private final String DATE_STRING = "yyyy-MM-dd";
    private final String TIME_STRING = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_STRING);
    private final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_STRING);
    private final String dateStringRegex = "\\d{4}-\\d{2}-\\d{2}";
    private final String timeStringRegex = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}";

    public String formatDate(Date date) {
        return dateFormat.format(date);
    }

    public String formatTime(Date time) {
        return timeFormat.format(time);
    }

    public Date parseDate(String dateString) throws Exception {
        if (!dateString.matches(dateStringRegex)) {
            throw new Exception("日期格式有误！！");
        }
        return dateFormat.parse(dateString);
    }

    public Date parseTime(String timeString) throws Exception {
        if (!timeString.matches(timeStringRegex)) {
            throw new Exception("时间格式有误！！");
        }
        return dateFormat.parse(timeString);
    }
}
