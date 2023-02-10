

package com.thesis.dms.constant.common;

import java.time.format.DateTimeFormatter;

public class DateTimeConst {

    private DateTimeConst() {
    }

    /**
     * 1 hour to milisecond
     */
    public static final Integer ONE_HOUR = 60 * 60 * 1000;

    /**
     * 1 day to milisecond
     */
    public static final Integer ONE_DAY = 24 * ONE_HOUR;

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT_SQl = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_DOT = "yyyy.MM.dd";
    public static final String DATE_FORMAT_DTO = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DTO_2 = "MM/dd/yyyy";

    public static final String WEB_ADDRESS = "http://";

    public static final String APP_NAME = "EFN";

    public static final String CONTACT_EMAIL = "efn@gmail.com";

    /**
     * Link ung dung tren google store
     */
    public static final String LINK_ANDROID = "https://play.google.com/store";

    /**
     * Link ung dugn tren apple store
     */
    public static final String LINK_IOS = "https://www.apple.com/lae/ios/app-store";

    public static final String DEFAULT_AVATAR = "images/default.png";

    public static String FILE_DIR = "";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateTimeConst.DATE_TIME_FORMAT);
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DateTimeConst.DATE_FORMAT);
    public static final DateTimeFormatter DATE_FORMATTER_DOT = DateTimeFormatter.ofPattern(DateTimeConst.DATE_FORMAT_DTO);
    public static final DateTimeFormatter DATE_FORMATTER_DOT_2 = DateTimeFormatter.ofPattern(DateTimeConst.DATE_FORMAT_DTO_2);
    public static final String AUTHORIZATION = "Authorization";
    public static final long BVY_ID = 1;
    /**
     * 1 hour IN MINUTE
     */
    public static long ONE_HOUR_IN_MINUTE = (long) 60;
}