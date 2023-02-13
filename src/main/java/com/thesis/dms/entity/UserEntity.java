package com.thesis.dms.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity{
    @Column(name = "user_code")
    private String userCode;

    @Column(name = "full_name")
    private String fullName;

    /**
     * Ten dang nhap
     */
    @Column(name = "username")
    private String username;

    /**
     * Dia chi email
     */
    @Column(name = "email")
    private String email;
    @Column(name = "col_gender")
    private Integer gender;

    /**
     * Mat khau dang nhap
     */
    @JsonIgnore
    @Column(name = "password")
    private String password;

    /**
     * so dien thoai
     */
    @Column(name = "phone")
    private String phone;
    /**
     * dia chi
     */
    @Column(name = "address")
    private String address;

    /**
     * Trang thai block cua lai khoan
     */
    @Column(name = "blocked")
    private Integer blocked;

    @Column(name = "verify")
    private Integer verify = 0;
    /**
     * Max xac nhan tai khoan
     */
    @JsonIgnore
    @Column(name = "verify_code")
    private String verifyCode;

    /**
     * Ghi chu
     */
    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @JsonIgnore
    @Column(columnDefinition = "TEXT", name = "reset_password_code")
    private String resetPasswordCode;
    /**
     * anh dai dien
     */
    @Column(name = "avatar")
    private String avatar;

    @Column(name = "role")
    private Integer role;
    /**
     * ngay sinh
     */
    @Column(name = "dob")
    private LocalDateTime dob;
    /**
     * email token
     */
    @JsonIgnore
    @Column(name = "email_token")
    private String emailToken;

    /**
     * Trang thai cua lai khoan
     */
    @Column(name = "active")
    private Integer active = 0;
    /**
     * Id social khi dang nhap bang google
     */
    @Column(name = "google_id")
    private String googleId;

    /**
     * Id social khi dang nhap bang facebook
     */
    @Column(name = "facebook_id")
    private String facebookId;
    /**
     * Lan cuoi cung login cua user nay
     */
    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    /**
     * hinh anh
     */
    @Column(name = "image")
    private String image;
    /**
     * cmt cua nguoi dung
     */
    @Column(name = "passport")
    private String passport;

    /**
     * ngay cap cmt
     */
    @Column(name = "date_range_pass_port")
    private LocalDateTime dateRangePassPort;

    /**
     * noi cap
     */
    @Column(name = "place_pass_port")
    private String placePassPort;
    /**
     * check yeu cau doi pass lan dau login<br>
     * 1: phai doi<br>
     * 0: k phai doi <br>
     */
    @Column(name = "flag")
    private Integer flag = 1;

    @Column(name = "source")
    private Integer source;

    @Column(name = "latest_version")
    private String latestVersion;

    @Column(name = "latest_device")
    private String latestDevice;

    @Column(name = "latest_os")
    private String latestOs;

    @Column(name = "number_house")
    private String numberHouse;
    @Column(name = "social_type")
    private Integer socialType = 1;

    @Column(columnDefinition = "TEXT", name = "login_token")
    private String loginToken;

    @Column(columnDefinition = "TEXT", name = "refresh_token")
    private String refreshToken;

    /**
     * Role cua nguoi dung
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();

    /**
     * Permission nguoi dung
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_permissions", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions = new HashSet<>();
}
