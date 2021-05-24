package com.BootBasic.user;

import java.awt.List;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Entity
public class User {
	

	@Id
	@GeneratedValue	
	int id;
	
	@Size(min=2,message="Name은 2글자 이상 입력해 주세요!") //사이즈가 최소 2이상이여야함
	@ApiModelProperty(notes="사용자 이름을 입력해 주세요.")
	String name;
	
	@Past	//현재보다 과거인가
	@ApiModelProperty(notes="등록일을 입력해 주세요.")
//	@JsonIgnore //그 데이터값을 무시해주세요 JSon으로 전달될 값이 무시가 된다.
	Date join_Date;
	
	@Size(min=8)
	@ApiModelProperty(notes="사용자의 패스워드를 입력해 주세요.")
	String password;
	
	@ApiModelProperty(notes="사용자의 주민번호를 입력해 주세요.")
	String ssn;
	
	
	//@OneToMany = 하나 대 다수  즉 , 한명의 데이터에서 한명이 작성한 어떤걸 가지고오는것
	// 게시글 : 유저 =  관계필드를 지정한다? user로 지정햇으니까 해당 게시글에서 private User {user} 이것이 작성된 테이블을 가져온다이말
//	mappedBy = 'user'  현재는 Post 클래스에서 user가 선언되어있고 어노테이션이 달려있기때문에 해당 멤버 변수와 관계필드를 유지한다.
//	즉,post의 정보를 가져온다 해당user객체에
	@OneToMany(mappedBy="user")
	private java.util.List<Post> posts; //게시글을 가지고온다. getPost때문에 여기가 작동이  되며

	public User(int id,  String name, Date join_Date,
			String password, String ssn) {
		this.id = id;
		this.name = name;
		this.join_Date = join_Date;
		this.password = password;
		this.ssn = ssn;
	}
	
	
	
	
}

