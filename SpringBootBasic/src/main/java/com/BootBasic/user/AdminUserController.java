package com.BootBasic.user;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;

import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

	private UserDaoService service;	//의존성주입으로사용해야한다.
	
	public AdminUserController(UserDaoService service) {
		this.service = service;
	}
	@GetMapping(value="/v1/users")	//Get으로 요청이 들어왔을때 처리하는 컨트롤러 메소드
	public MappingJacksonValue SelectAllUsersV1() {
		ArrayList<User> users = service.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
		
		mapping.setFilters(filters);
		return mapping;
	}
	@GetMapping(value="/v2/users")	//Get으로 요청이 들어왔을때 처리하는 컨트롤러 메소드
	public MappingJacksonValue SelectAllUsersV2() {
		ArrayList<User> users = service.findAll();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");
		MappingJacksonValue mapping = new MappingJacksonValue(users);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
		
		mapping.setFilters(filters);
		return mapping;
	}
//	@GetMapping(value="/v1/users/{id}") //우리가 int 형태로 전달을 해도 서버측에서는 String 으로 받는다.
//	@GetMapping(value="/users/{id}",params="version=1") //파라미터로 버젼별로 요청을 받는다.
//	@GetMapping(value="/users/{id}",headers="X-API-VERSION=1") //헤더값으로 버젼별로 요청을 받는다.
	@GetMapping(value="/users/{id}",produces="application/vnd.company.appv1+json")	//produce = mime타입을 이용하여 요청
	public MappingJacksonValue SelectOneUsersV1(@PathVariable int id) {
		User user = service.fineOne(id);
		if(user==null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","password","ssn");
		MappingJacksonValue mapping = new MappingJacksonValue(user);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);
		
		mapping.setFilters(filters);
		return mapping;
	}
//	@GetMapping(value="/users/{id}",params="version=2")
//	@GetMapping(value="/users/{id}",headers="X-API-VERSION=2") 
	@GetMapping(value="/users/{id}",produces="application/vnd.company.appv2+json")
	public MappingJacksonValue SelectOneUsersV2(@PathVariable int id) {
		User user = service.fineOne(id);
		if(user==null) {
			throw new UserNotFoundException(String.format("ID[%s] not found", id));
		}
		// User=> User2
		UserV2 userV2 = new UserV2();
		BeanUtils.copyProperties(user, userV2); //스프링프레임워크 유틸클래스로서 Bean들간의 관련되있는 작업을 하는 서비스 (두 인스턴스간에 공통기능 카피등등)
		//즉, user값검사를햇던값을 V2값으로 카피 한다 이말
		userV2.setGrade("VIP");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","grade");
		MappingJacksonValue mapping = new MappingJacksonValue(userV2);
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2",filter);
		
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	
	
		
		
		
		
	
	
}
