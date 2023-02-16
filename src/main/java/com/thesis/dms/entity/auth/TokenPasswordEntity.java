package com.thesis.dms.entity.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thesis.dms.entity.BaseEntity;
import com.thesis.dms.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "token_password")
@Getter
@Setter
public class TokenPasswordEntity extends BaseEntity {
    /**
     * Ngay het han cua session, 3 ngay tu ngay tao token
     */
    private LocalDateTime expiredDate;

    /**
     * Token de reset password
     */
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotFound(
            action = NotFoundAction.IGNORE)
    @JsonIgnore
    private UserEntity user;
}
