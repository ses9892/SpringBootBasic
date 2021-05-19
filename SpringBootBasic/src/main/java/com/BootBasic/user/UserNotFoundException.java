package com.BootBasic.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//앞으로 UserNotFound에러는 Http 4xx 대 오류로 출력이 되게하는 어노테이션	
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String meg) {
		super(meg);	//생성자 호출시 메세지가 부모클래스로 넘어간다 => 런타임 에러 메세지로 넘어간다!
	}

}
