package kr.co.lunasoft.validator;



import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import kr.co.lunasoft.domain.Member;

public class MemberValidator implements Validator {

	//유효성 검사 지원 여부를 설정하는 메소드
	@Override
	public boolean supports(Class<?> clazz) {
		//Member 클래스에 대한 유효성 검사 기능 여부를 리턴
		return Member.class.isAssignableFrom(clazz);
	}

	//유효성 검사를 수행하는 메소드
	@Override
	//target은 유효성 검사를 수행하는 객체
	//errors는 유효성 검사에서 유효성을 실패했을 때의 메시지를 저장하는 객체
	public void validate(Object target, Errors errors) {
		
		/*
		//유효성 검사를 수행할 객체
		Member member = (Member)target;
		//검사수행
		if(member.getEmail() == null ||
				member.getEmail().trim().isEmpty()) {
			errors.rejectValue("email","required");
		}
		if(member.getPw() == null ||
				member.getPw().trim().isEmpty()) {
			errors.rejectValue("pw","required");
		}
		*/
		
		ValidationUtils.rejectIfEmpty(errors, "email", "required");
		ValidationUtils.rejectIfEmpty(errors, "pw", "required");

	} 

}
