package com.thesis.dms.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO {
	private String fullName;
	private String username;
	private String avatar;
	private String code;
	private String email;
	private String password;
	private String phone;
	private Integer role;
}
