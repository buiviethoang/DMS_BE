package com.thesis.dms.dto;

import com.thesis.dms.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {
    UserEntity user;
    String socialId;
    String accessToken;
    String applicationId;
    Long countryId;
    Long districtId;
    Long provinceId;
    Long zoneId;
}
