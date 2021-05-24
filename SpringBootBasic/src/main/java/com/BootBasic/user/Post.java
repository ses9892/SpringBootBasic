package com.BootBasic.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue	//자동증가
	private int id;
	
	private String description;	//게시물의 내용
	
	// User ; Post -> 1:(0~N), Main : Sub -> Parent : Child
	// getPost에서 여기까지 자동으로 넘어와서 @OneToMany 에서 @ManyToOne 을 받아준다.
	//@ManyToOne : 유저들 : 게시글 이라고 보면됨  하나의 유저 정보를 보여주는것에서 여기로 이동되서 전체유저중 게시글쓴 유저만 되돌려지게함
//	유저들 : 게시글에서 데이터를 느슨하게 가져오는 전략
	@ManyToOne(fetch=FetchType.LAZY)	//지연 로딩 방식 LAZY
	@JsonIgnore	//외부 공개 x
	private User user;
}
