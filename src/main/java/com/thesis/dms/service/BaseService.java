package com.thesis.dms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.dms.common.constant.DateTimeConst;
import com.thesis.dms.common.response.IPaging;
import com.thesis.dms.common.response.IResult;
import com.thesis.dms.converter.CustomDozerBeanMapper;
import com.thesis.dms.converter.CustomDozerJdk8BeanMapper;
import com.thesis.dms.dto.ReturnPaginationDTO;
import com.thesis.dms.entity.UserEntity;
import com.thesis.dms.exception.CustomException;
import com.thesis.dms.repository.UserRepository;
import com.thesis.dms.utils.GsonUtils;
import com.thesis.dms.utils.LogUtils;
import com.thesis.dms.utils.StrUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BaseService implements IResult, IPaging {

    @Autowired
    public CustomDozerBeanMapper customDozerBeanMapper;

    @Autowired
    public CustomDozerJdk8BeanMapper customDozerJdk8BeanMapper;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    public void init() {
        log = LogUtils.getInstance();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url = request.getMethod() + " " + request.getRequestURI();
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            url += "?" + queryString;
        }
        log.info(url);
        log.info("getRemoteAddr: " + request.getRemoteAddr());

        String token = request.getHeader("Authorization");
        if (!StrUtils.isNullOrWhiteSpace(token)) {
            log.info("Token " + token);
        } else {
            log.info("khong co token Token ");
        }
    }

    /**
     * The time the service is called, used to calculate "service running time"
     */

    protected Long userId;

    /**
     * 1: mobi <br> 0: web
     */
    protected Integer typeBase;

    protected String hospitalUid;

    protected Integer roleBase;

    public Long getUserId() {
        return userId;
    }

    protected JSONObject jsonData;

    public void init(Object object) {

    }

    protected void save(Object o) {
        // save user
    }

    /**
     * Log controller
     */
    protected LogUtils log;

    /**
     * Get Object with key in jsonData
     */
    public <U> U getObject(String key, Class<U> cls) {
        if (jsonData.has(key)) {
            return GsonUtils.toObject(jsonData.get(key).toString(), cls);
        }
        return null;
    }

    public JSONObject getJSONObject(String key) {
        if (jsonData.has(key)) {
            return jsonData.getJSONObject(key);
        }
        return null;
    }

    public JSONArray getJSONArray(String key) {
        if (jsonData.has(key)) {
            return jsonData.getJSONArray(key);
        }
        return null;
    }

    /**
     * Get param input as Integer
     */
    public Integer getInteger(String key) {
        if (!jsonData.has(key))
            return null;
        return jsonData.getInt(key);
    }

    /**
     * Get param input as Integer
     */
    public Integer getInteger(String key, Integer defaultValue) {
        if (jsonData.has(key)) {
            return jsonData.getInt(key);
        }
        return defaultValue;

    }

    /**
     * Get param input as Long
     */
    public Long getLong(String key) {
        if (jsonData.has(key))
            return jsonData.getLong(key);
        return null;
    }

    /**
     * Get param input as LocalDate
     */
    public LocalDate getDate(String key) {
        if (jsonData.has(key)) {
            String date = jsonData.getString(key);
            return LocalDate.parse(date, DateTimeConst.DATE_FORMATTER);
        }
        return null;
    }

    /**
     * Get param input as LocalDate
     */
    public LocalDateTime getDateTime(String key) {
        if (jsonData.has(key)) {
            String date = jsonData.getString(key);
            return LocalDateTime.parse(date, DateTimeConst.DATE_TIME_FORMATTER);
        }
        return null;
    }

    public Long getLong(String key, Long defaultValue) {
        if (jsonData.has(key)) {
            return jsonData.getLong(key);
        }
        return defaultValue;
    }

    /**
     * Get param input as String, neu khong co properties nay thi tra ve null
     */
    public String getString(String key) {
        if (jsonData.has(key)) {
            return jsonData.getString(key);
        }
        return null;
    }

    public String getString(String key, String defaultValue) {
        if (jsonData.has("key")) {
            return jsonData.getString(key);
        }
        return defaultValue;
    }

    protected <T> T getEntityById(JpaRepository repository, Long id) {
        if (id == null || Objects.equals(0L, id))
            return null;

        Optional entityOpt = repository.findById(id);
        if (!entityOpt.isPresent()) {
            return null;
        }
        return (T) entityOpt.get();
    }

    /**
     * format du lieu
     *
     * @param format
     * @param args
     * @return
     */
    protected String format(String format, Object... args) {
        return String.format(format, args);
    }

    /**
     * Tao text hien thi tren adress trinh duyet (linkAlias)
     *
     * @param text text muon chuyen thanh linkAlias
     * @return linkAlias
     */
    public String getTextUrl(String text) {
        return StrUtils.createUrlFromString(text);
    }

    CustomException getUnauthentication(String message) {
        return new CustomException(HttpServletResponse.SC_UNAUTHORIZED, message);
    }

    CustomException getUnauthentication() {
        return new CustomException(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token ");
    }

    protected CustomException caughtException(String message, Object... objects) {
        return new CustomException(String.format(message, objects));
    }

    /**
     * Tra ve loi voi cac thong so
     *
     * @param code    ma loi
     * @param message Noi dung loi
     */
    protected CustomException caughtException(int code, String message, Object... objects) {
        String testMessage = String.format(message, objects);
        return new CustomException(code, String.format(message, objects));
    }

    protected CustomException caughtException(int code, String message) {
        return new CustomException(code, message);
    }

    public UserEntity getCurrentUser() {
        return getUser(userId);
    }

    // region Fast Get Entity
    UserEntity getUser(Long id) {
        UserEntity userEntity = getEntityById(userRepository, id);
        return userEntity;
    }

    // region AutoWired Repository
    // endregion

    // region Fast Get Entity

    // endregion

    // region khai bao cacs constance name of database
    protected static final String USER = "user";
    protected static final String USER_CREATE = "userCreate";
    protected static final String USER_UPDATE = "userUpdate";
    protected static final String USERS = "users";
    protected static final String USER_CONFERENCE = "userConference";
    protected static final String CONFERENCE = "conference";
    protected static final String DEVICE = "device";
    protected static final String DEVICES = "devices";
    protected static final String IMAGE = "image";
    protected static final String IMAGES = "images";
    protected static final String KEY_VALUE = "keyValue";
    protected static final String SCHEDULE = "schedule";
    protected static final String SUPPORT = "support";
    protected static final String CONFERENCE_TOPIC = "conferenceTopic";
    protected static final String CONFERENCE_SESSION = "conferenceSession";
    protected static final String CONFERENCE_QUESTION = "conferenceQuestion";
    protected static final String PROVINCE = "province";
    protected static final String ADMIN_SALE = "adminSale";
    protected static final String ADVERTISE = "advertise";
    protected static final String USER_SESSION = "userSession";
    protected static final String SURVEY = "survey";
    protected static final String ANSWER = "answer";
    protected static final String USER_ANSWER = "userAnswer";
    protected static final String CONFERENCE_NOTIFICATION = "conferenceNotification";
    protected static final String SPECIALIST = "specialist";
    protected static final String SPECIALISTS = "specialists";
    protected static final String SYMPTOM = "symptom";
    protected static final String SYMPTOMS = "symptoms";
    protected static final String DISEASE = "disease";
    protected static final String DISEASES = "diseases";
    protected static final String FACILITY = "facility";
    protected static final String FACILITIES = "facilities";
    protected static final String DRUG = "drug";
    protected static final String COUNTRY = "country";
    protected static final String METHOD_USE = "methodUse";
    protected static final String METHOD_USES = "methodUses";
    protected static final String MENU = "menu";
    protected static final String MENUS = "menus";
    protected static final String PHARMACY = "pharmacy";
    protected static final String SLIDE_ITEM = "slideItem";
    protected static final String SLIDE_ITEMS = "slideItems";
    protected static final String SLIDE = "slide";
    protected static final String SLIDE_PLACE = "slidePlace";
    protected static final String TOKEN_PASSWORD = "tokenPassword";
    protected static final String DRUG_FACILITY = "drug_facility";
    protected static final String FILE = "file";
    protected static final String HOSPITAL = "hospital";
    protected static final String HOSPITALS = "hospitals";
    protected static final String POST = "post";
    protected static final String AUTHOR = "author";
    protected static final String COMMENT = "comment";
    protected static final String ASIGNEE = "assignee";
    protected static final String DOCTOR_DEPARTMENT = "doctorDepartment";
    protected static final String DOCTORS = "doctors";
    protected static final String DOCTOR = "doctor";
    protected static final String DOCTOR_DEPARTMENTS = "doctorDepartments";
    protected static final String TEMP_DEPARTMENT = "tempDepartment";
    protected static final String TEMP_DEPARTMENTS = "tempDepartments";
    protected static final String TEMP_DOCTOR = "tempDoctor";
    protected static final String TEMP_DOCTORS = "tempDoctors";
    protected static final String TEMP_SERVICE = "tempService";
    protected static final String TEMP_SERVICES = "tempServices";
    protected static final String TEMP_SPECIALIST = "tempSpecialist";
    protected static final String TEMP_SPECIALISTS = "tempSpecialists";

    protected static final String TEMP_BOOKING = "tempBooking";
    protected static final String TEMP_BOOKINGS = "tempBookings";
    protected static final String ROLES = "roles";
    protected static final String ROLE = "role";
    protected static final String PERMISSION = "permission";
    protected static final String CREATE_PERSON = "createPerson";
    protected static final String UPDATE_PERSON = "updatePerson";
    protected static final String DOCTOR_INF = "doctorInf";
    protected static final String NOTIFICATION = "notification";

    protected static final String BOOKING = "booking";
    protected static final String BOOKINGS = "bookings";

    protected static final String DEPARTMENTS = "departments";
    protected static final String DEPARTMENT = "department";

    protected static final String ROOMS = "rooms";
    protected static final String ROOM = "room";

    protected static final String SERVICES = "services";
    protected static final String SERVICE = "service";

    protected static final String SCHEDULES = "schedules";
    protected static final String PROFILE = "profile";
    protected static final String PROFILES = "profiles";

    protected static final String PROVINCES = "provinces";
    protected static final String DISTRICTS = "districts";
    protected static final String ZONES = "zones";
    protected static final String DISTRICT = "district";
    protected static final String ZONE = "zone";
    protected static final String PATIENT_HISTORY = "patientHistory";
    protected static final String PATIENT_HISTORYS = "patientHistorys";
    protected static final String SERVICE_TYPE = "serviceType";
    protected static final String DOCTOR_VENDOR = "doctorVendor";

    // endregion

    /**
     * Run a runnable Object in other thread
     *
     * @param runnable
     */
    void runInThread(Runnable runnable) {
        try {
            Thread thread = new Thread(runnable::run);
            thread.start();
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

    }

    public static String getMD5Hex(final String inputString) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputString.getBytes());

        byte[] digest = md.digest();

        return convertByteToHex(digest);
    }

    private static String convertByteToHex(byte[] byteData) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }
    public static <T> ReturnPaginationDTO<T> getPaginationResult(List<T> content, int page, int totalPages, long totalElements){
        ReturnPaginationDTO<T> result= new ReturnPaginationDTO<T>();
        result.setContent(content);
        result.setPageNumber(page);
        result.setTotalPages(totalPages);
        result.setTotalElements((int)totalElements);
        return result;
    }

    public static LocalDateTime convertObjectToLocalDateTime(Object object){
        java.sql.Timestamp time = (Timestamp)object;
        return  time.toLocalDateTime();
    }
    public static float CeilAfterComma2Digits(float money){
        return (float) (Math.ceil(money * 100.0) / 100.0);
    }

    public String formatCode(Long id,String str){
        int num = id.toString().length();
        int numRule = 7;
//        String str = Integer.toString(j);
        for(int i = 0 ; i < (numRule-num) ; i++){
            str = new StringBuffer(str).insert(str.length(),"0").toString();
        }
        str = new StringBuffer(str).insert(str.length(),id.toString()).toString();
        return str;
    }
}
