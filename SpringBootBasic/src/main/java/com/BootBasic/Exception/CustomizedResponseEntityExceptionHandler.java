package com.BootBasic.Exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.BootBasic.user.UserNotFoundException;



//@ExceptionHandler 가 하나의 에러 핸들링이라면
//@ControllerAdvice는  모든 컨트롤러 전역 에서 발생할 수 있는 모든 에러의 예외처리를 잡아주는 어노테이션이다.	



@RestController
@ControllerAdvice //모든 컨트롤러가 실행이 될때 반드시 선언한 어노테이션을 가진 Bean이 실행되게 한다.
//이 어노테이션을 붙힘으로써 에러발생이 현재 클래스의 에러가 바로 실행될 수 있겧한다.
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	//어떤 에러가 나면 이 에러 메소드를 실핼할 것인가? 라는 어노테이션
	@ExceptionHandler(Exception.class)	// 모든에러에 대해
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
															//HttpStatus.INTERNAL_SERVER_ERROR : 500번에러
	}
		
	@ExceptionHandler(UserNotFoundException.class)	//UserNotFountException에 대한 에러가 나면 해당 메소드가 실행된다.
	public final ResponseEntity<Object> handleUserNotFountExceptions(Exception ex,WebRequest request){
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<Object>(exceptionResponse,HttpStatus.NOT_FOUND);
		//HttpStatus.INTERNAL_SERVER_ERROR : 500번에러
		
	}
}
