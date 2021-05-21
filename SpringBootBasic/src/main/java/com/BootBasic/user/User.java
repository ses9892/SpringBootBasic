package com.BootBasic.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value= {"password"})	
//@JsonFilter("UserInfo")
@ApiModel(description="사용자 상세 정보를 위한 도매인 객체")
public class User {
	int id;
	
	@Size(min=2,message="Name은 2글자 이상 입력해 주세요!") //사이즈가 최소 2이상이여야함
	@ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
	String name;
	
	@Past	//현재보다 과거인가
	@ApiModelProperty(notes="등록일을 입력해 주세요.")
//	@JsonIgnore //그 데이터값을 무시해주세요 JSon으로 전달될 값이 무시가 된다.
	Date joinDate;
	
	@Size(min=8)
	@ApiModelProperty(notes="사용자의 패스워드를 입력해 주세요.")
	String password;
	
	@ApiModelProperty(notes="사용자의 주민번호를 입력해 주세요.")
	String ssn;
	
}

