package hk.reco.music.iptv.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /**
     * 获取几天前/后的当前日期
     */
    public static Date getDayCurrTime(Date date, int days) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
        return now.getTime();
    }

    /**
     * 获取几天前/后的凌晨时间
     */
    public static long getDateSmallHours(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime().getTime();
    }

    public void getCurr() {
        //获取当前星期几
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar);
        System.out.println("当前月份：" + Calendar.DAY_OF_MONTH);
        String week;
        week = calendar.get(Calendar.DAY_OF_WEEK) - 1 + "";
        if ("0".equals(week)) {
            week = "7";
        }
        System.out.println("当前星期：" + week);
    }


    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";
        try {
            SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = myFormatter.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dayOfweek;
    }

    /**
     * 日期转字符串
     *
     * @param date      日期
     * @param formatStr 字符串格式
     * @return
     */
    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
        return simpleDateFormat.format(date);
    }



    public static void main(String[] args)  {
        System.out.println(getDayOfWeekByDate("2019-06-01"));
    }


}
