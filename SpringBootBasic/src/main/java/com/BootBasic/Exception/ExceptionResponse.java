package com.BootBasic.Exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//핸들러 예외 클래스
public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
}
