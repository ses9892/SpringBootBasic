package com.BootBasic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링 부트에서 선언하는 어노테이션 
//컨트롤러로 사용한다.
@RestController
public class HelloWorldController {
	// GET
	// /hello-world -> (endPoint)
	
	// RequestMapping() 도 가능하다. 단! (method=RequestMethod.GET) 을 적어주어야한다.
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "HelloWorld Im'jinogu";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	//REST 컨트롤러를 사용하게되면 반환 시키고자 하는 데이터값을 @ResponseBody 에 저장하지 않더라도 자동으로 JSON 포맷으로 저장되어 반환하는 것을 확인 할 수 있다.
}
