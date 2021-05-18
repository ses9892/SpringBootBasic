package com.BootBasic.user;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	@GetMapping(value="/users/{id}") //우리가 int 형태로 전달을 해도 서버측에서는 String 으로 받는다.
	public User SelectOneUsers(@PathVariable int id) {
		return service.fineOne(id);
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
	public void createUser(@RequestBody User user) {
		User savedUser = service.save(user);
	}
		
		
		
		
	
	
}
