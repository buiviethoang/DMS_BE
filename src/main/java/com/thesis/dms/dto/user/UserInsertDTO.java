package com.thesis.dms.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
	List<Long> roleIds = new ArrayList<>();
}
