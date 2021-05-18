	package com.BootBasic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// lombok
// 클래스 생성시 Getter/Setter,toString , equals 등을 생성 하지 않아도
// 사용할 수 있게 만드는 API 이며 , @Data 어노테이션을 붙여서 사용한다.

@Data
@AllArgsConstructor
@NoArgsConstructor
// @AllArgsConstructor = 모든 argument를 가지고 있은 Constructor를 생성한다.
public class HelloWorldBean {
	private String meg;	//lombok을 사용하기 때문에 게터/세터를 생성하지 않아도 된다.
	/*
	 */

}
