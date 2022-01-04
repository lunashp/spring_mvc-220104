package kr.co.lunasoft.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Member {
	@Email
	//@Pattern(regexp="^[a-zA-z0-9._%+-]+@[a-zA-z0-9.-], message ... 이메일 정규식 ="이메일 형식과 맞지 않음")
	@NotNull
	private String email;
	@NotNull
	@Size(min=8)
	private String pw;
	private String loginType;
	
}
