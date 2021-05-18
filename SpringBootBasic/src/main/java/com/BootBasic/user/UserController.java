package com.BootBasic.user;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private UserDaoService service;	//의존성주입으로사용해야한다.
	
	public UserController(UserDaoService service) {
		this.service = service;
	}
	@GetMapping(value="/users")
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
		
		
		
		
	
	
}
