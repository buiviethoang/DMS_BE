

package com.thesis.dms.utils;

import com.thesis.dms.common.constant.DateTimeConst;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

    public static long getCurrentTimeInMilis() {
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Get gio hien tai
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }


    /**
     * Add date
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDays(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTime();
    }

    /**
     * Lay thoi diem bat dau trong ngay 0h0m0s
     *
     * @return
     */
    public static Long getStartDayTime() {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(Calendar.HOUR_OF_DAY, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        Long time = calendarStart.getTimeInMillis();
        // remove ticktac
        time = time - time % 1000;
        return time;
    }

    /**
     * convert millis to string
     *
     * @param millis
     * @return
     */
    public static String toString(long millis) {
        Date date = new Date(millis);
        return date.toString();
    }

    /**
     * get current time in string
     *
     * @return
     */
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public static LocalDateTime fromMillis(long timeStamp) {
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timeStamp),
                        TimeZone.getDefault().toZoneId());
        return triggerTime;
    }

    public static LocalDateTime fromSeconds(long timeStamp) {
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochSecond(timeStamp),
                        TimeZone.getDefault().toZoneId());
        return triggerTime;
    }

    /**
     * Convert LocalDateTime to time in milis
     *
     * @return
     */
    public static Long getTimeInMillis(LocalDateTime localDateTime) {
        if (localDateTime == null) return 0L;
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(7)) * 1000;
    }

    /**
     * Convert LocalDateTime to time in milis
     *
     * @return
     */
    public static Long getTimeInMillis(LocalDate localDate) {
        if (localDate == null) return 0L;
        return localDate.atStartOfDay().toEpochSecond(ZoneOffset.ofHours(7)) * 1000;
    }

    /**
     * Get age from localDate
     *
     * @return
     */
    public static int getAge(LocalDateTime localDate) {
        if (localDate == null) return 0;
        return LocalDate.now().getYear() - localDate.getYear() + 1;
    }
    
    public static String convertLocalDateToString(LocalDate dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
        return formattedDateTime;
    }
    
    public static String convertLocalDateTimeToString(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
        return formattedDateTime;
    }

    public static LocalDateTime getLocalDateTime(String date){
        try {
            if (date == null) {
                return null;
            }
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static LocalDateTime getLocalDateTime2(String date){
        // for ISO format: YYYY-MM-DDTHH:MM:SS
        try {
            if (date.charAt(date.length() - 1) == 'Z' || date.charAt(date.length() - 1) == 'z') {
                date = StringUtils.chop(date);
            }
            return LocalDateTime.parse(date);
        } catch (Exception exception){
            return null;
        }
    }
    public static LocalDateTime getLocalDateTime(String date,String pattern){
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }
    public static LocalDateTime getLocalDateTimeToLocalDate(String date){
        return LocalDate.parse(date, DateTimeConst.DATE_FORMATTER).atStartOfDay();
    }
    public static LocalDateTime getLocalDateTimeToLocalDateFormatDot(String date){
        return LocalDate.parse(date, DateTimeConst.DATE_FORMATTER_DOT).atStartOfDay();
    }
    public static LocalDateTime getLocalDateTimeToLocalDateFormatDto(String date){
        if (date == null) {
            return null;
        }
        return LocalDate.parse(date, DateTimeConst.DATE_FORMATTER_DOT_2).atStartOfDay();
    }

}
