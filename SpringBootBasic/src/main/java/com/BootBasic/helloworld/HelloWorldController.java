package com.BootBasic.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//스프링 부트에서 선언하는 어노테이션 
//컨트롤러로 사용한다.
@RestController
public class HelloWorldController {
	// GET
	// /hello-world -> (endPoint)
	//현재 스프링프레임워크 빈중 같은 타입의 빈을 등록해준다.
	@Autowired
	private MessageSource messagesource;
	
	// RequestMapping() 도 가능하다. 단! (method=RequestMethod.GET) 을 적어주어야한다.
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "HelloWorld Im'jino";
	}
	
	@GetMapping(path="/hello-world-bean/path/variable/{name}")        // {name}은 인수로 들어온 String name 이 된다.
	public HelloWorldBean helloWorldBean(@PathVariable String name) { /*@PathVariable 해당 인수가 가변데이터로 사용될 것 이다. 라는 어노테이션 */
		System.out.println("variable/"+name+"이 실행됨");
		return new HelloWorldBean(String.format("Hello World,%s", name));
	}
	//외국어 치환
	@GetMapping(path="/hello-world-internationalized")   
	public String helloworldInternationalized(
			@RequestHeader(name="Accept-Language",required=false)Locale locale) { //  의 [언어][나라] 등의 정보를 담고 있는 클래스
		return messagesource.getMessage("greeting.message", null,locale);
	}
	//REST 컨트롤러를 사용하게되면 반환 시키고자 하는 데이터값을 @ResponseBody 에 저장하지 않더라도 자동으로 JSON 포맷으로 저장되어 반환하는 것을 확인 할 수 있다.
}
