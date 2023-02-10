package com.thesis.dms.constant.common;

import java.time.format.DateTimeFormatter;

public class AppConst {

    private AppConst() {
    }

    /**
     * 1 hour to milisecond
     */
    public static final Integer ONE_HOUR = 60 * 60 * 1000;

    /**
     * 1 day to milisecond
     */
    public static final Integer ONE_DAY = 24 * ONE_HOUR;

    public static final String WEB_ADDRESS = "http://34.87.86.215:8688";

    public static final String WEB_SITE = "http://34.87.86.215:8081";


    public static final String APP_NAME = "EFN MANAGER";

    public static final String CONTACT_EMAIL = "";

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

    public static final String AUTHORIZATION = "Authorization";
    public static final long BVY_ID = 1;
    
    public static final String STATUS = "status";
    public static final String DATA = "data";
    public static final String BOOKING_ID = "booking_ref_id";
    public static final String SUCCESS = "SUCCESS";
    public static final String ODER_ID = "order_ref_id";
    public static final String REJECT = "reject";
}