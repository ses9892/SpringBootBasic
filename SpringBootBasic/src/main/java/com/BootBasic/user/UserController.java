package com.BootBasic.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	private UserDaoService service;	//의존성주입으로사용해야한다.
	
	public UserController(UserDaoService service) {
		this.service = service;
	}
	@GetMapping(value="/users")	//Get으로 요청이 들어왔을때 처리하는 컨트롤러 메소드
	public ArrayList<User> SelectAllUsers() {
		
		return service.findAll();
	}
	// 2XX 대 코드 ->OK  
	// 4XX ->Client 오류
	// 5XX ->서버측 오류
	// 만약 사용자가 존재하지 않을경우 ? 
	// 서버에러가아닌 200번대 코드를 받았을때 오류를 생성하게 해줘야한다. 단순히 서버오류가 아니기때문이다.
	@GetMapping(value="/users/{id}") //우리가 int 형태로 전달을 해도 서버측에서는 String 으로 받는다.
	public User SelectOneUsers(@PathVariable int id) {
		User user = service.fineOne(id);
		if(user==null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		return user;
	}
	
	@GetMapping(value="/users/save/{id}&{name}") //우리가 int 형태로 전달을 해도 서버측에서는 String 으로 받는다.
	public User SelectOneUsers(@PathVariable int id, @PathVariable String name) {
		Date joinDate = new Date();
		User user = new User(id, name, joinDate);
		return service.save(user);
	}
	@PostMapping("/users")//Get이 아닌 Post로 요청이 들어왔을때 유저를 추가하는 메소드
	//@RequestBody는 클라이언트가 전송하는 Json(application/json) 형태의 HTTP Body 내용을 Java Object로 변환시켜주는 역할
	//Post메소드에 사용한다 .Get 메소드에 사용시 에러가난다. Json으로 받은 형태 를 추가한다.
	//Postman을 통해 Json 형태로 데이터를 보내어 user를 save 한다.
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		
		//ServletUriComponentsBuilder : 사용자에 요청에 따른 작업 처리후 결과 값을 토대로 관련 UR 를 생성해주는 역할
		// 등록이 성공한 후 클라이언트에게 성공여부와 상세정보 페이지에 대한 uRI값을 전달 하기 위해 URI 객체를 생성하는데 사용한다.
		// 생성된 URI 객체는 클라이언트의 response header response body 에 포함하여 전달된다.
		// 그리고 현재 가지오있는 request값을 fromCurrentRequest 메소드를 통하고
		// path() 를 통해  반환시켜주는 id값을 반환
		// 추가로 save 되어진 id에 가변변수인 id값을 추가로 확장
		// 마지막으로 모든형태를 URI로 지정하고 location이라 초기화
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		//ResponseEntity는 반환객체를 생성해주는 것이며 created() 메소드는 반환객체에 대한 response 타입을 결정한다. 
		//created로 할경우 201 코드 를 반환  = 201은 POST 요청과 같이 서버에 리소스 추가할때 대한 응답코드 이며 정상처리의신호이다.
		//결국 이후 201신호라는것을 받으면 생성완료됬다는 신호이다! 생성완료 신호를받고 추가로 이벤트 구성을 해주는것으로 처리한다?
	}
		
		
		
		
	
	
}
