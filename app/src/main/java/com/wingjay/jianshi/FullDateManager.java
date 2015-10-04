package com.wingjay.jianshi;

import android.util.Log;

import org.joda.time.DateTime;

import java.util.HashMap;

/**
 * Created by wingjay on 9/30/15.
 */
public class FullDateManager {

    public final static String YEAR_CHINESE = "年";
    public final static String MONTH_CHINESE = "月";
    public final static String DAY_CHINESE = "日";
    private int year = 0;
    private int month = 0;
    private int day = 0;
    private static final HashMap<Integer, String> intToChinese = new HashMap<>();
    private HashMap<Integer, String> yearMap = new HashMap<>();
    private HashMap<Integer, String> monthMap = new HashMap<>();
    private HashMap<Integer, String> dayMap = new HashMap<>();

    static {
        intToChinese.put(0, "零");
        intToChinese.put(1, "一");
        intToChinese.put(2, "二");
        intToChinese.put(3, "三");
        intToChinese.put(4, "四");
        intToChinese.put(5, "五");
        intToChinese.put(6, "六");
        intToChinese.put(7, "七");
        intToChinese.put(8, "八");
        intToChinese.put(9, "九");
        intToChinese.put(10, "十");
    }

    public FullDateManager() {}

    public FullDateManager(long dateSeconds) {
        DateTime dateTime = getDateTime(dateSeconds);
        this.year = dateTime.getYear();
        this.month = dateTime.getMonthOfYear();
        this.day = dateTime.getDayOfMonth();
    }

    public FullDateManager(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    private String yearToChinese(int year) {
        StringBuilder yearString = new StringBuilder();
        while (year > 0) {
            int y = year % 10;
            yearString.insert(0, intToChinese.get(y));
            year = year / 10;
        }
        return yearString.toString();
    }

    private String otherToChinese(int dayOrMonth) {
        if (dayOrMonth < 0) {
            return "";
        }
        if (dayOrMonth < 10) {
            return intToChinese.get(dayOrMonth);
        }
        StringBuilder otherString = new StringBuilder();
        int tens = dayOrMonth / 10;
        otherString.append( (tens == 1 ? "" : intToChinese.get(tens)) + "十");
        int units = dayOrMonth - tens * 10;
        otherString.append( (units <= 0) ? "" : intToChinese.get(units));
        return otherString.toString();
    }

    // format : "二零一五年 九月 十一日"
    public String getFullDate() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getYear(year));
        stringBuilder.append(getMonth(month));
        stringBuilder.append(getDay(day));
        return stringBuilder.toString();
    }

    public String getDay(int day) {
        return getPureDay(day) + DAY_CHINESE + " ";
    }

    public String getYear(int year) {
        return getPureYear(year) + YEAR_CHINESE + " ";
    }

    public String getMonth(int month) {
        return getPureMonth(month) + MONTH_CHINESE + " ";
    }

    public String getPureDay(int day) {
        return otherToChinese(day);
    }

    public String getPureYear(int year) {
        return yearToChinese(year);
    }

    public String getPureMonth(int month) {
        return otherToChinese(month);
    }

    public static long getTodayDateSeconds() {
        DateTime now = new DateTime();
        Log.i("test", "now millis : " + now.getMillis() / 1000);
        // clear info about hours and seconds, only need year, month and day.
        DateTime today = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0);
        Log.i("test", "today millis : " + today.getMillis() / 1000);
        return today.getMillis() / 1000;
    }

    public static long getDateSeconds(DateTime dateTime) {
        return getDateSeconds(dateTime.getYear(),
                dateTime.getMonthOfYear(),
                dateTime.getDayOfMonth());
    }

    public static long getDateSeconds(int year, int month, int day) {
        DateTime dateTime = new DateTime(year, month, day, 0, 0);
        return dateTime.getMillis() / 1000;
    }

    public static DateTime getDateTime(long dateSeconds) {
        return new DateTime(dateSeconds * 1000);
    }

}
