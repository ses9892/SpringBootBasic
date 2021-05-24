package com.BootBasic.user;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.micrometer.core.ipc.http.HttpSender.Method;

@RestController
@RequestMapping(value="/jpa")
public class UserJpaController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	//전체사용자 목록 조회
	@GetMapping(value="/users")
	public List<User> selectAllUsers(){
		return userRepository.findAll();
	}
	@GetMapping(value="/users/{id}")
	public Resource<User> selectAllUsers(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("해당 번호의 유저는 존재 하지 않습니다");
		}
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo
				(ControllerLinkBuilder.methodOn(this.getClass()).selectAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	//전체사용자 목록 조회
	@DeleteMapping(value="/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	@PostMapping(value="/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		User savedUser =  userRepository.save(user);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	//해당 유저의 번호를 입력하고 해당의저가 작성한 글목록을 가지고 오는 메소드
	@GetMapping(value="/users/{id}/posts")
	public List<Post> selectAllPostsByUser(@PathVariable int id){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("해당 번호의 유저는 존재 하지 않습니다");
		}
		// 해당유저의 정보 안에서 getPost를 이용해 게시글을 가지고오는메소드
		return user.get().getPosts(); 
		//결국 .get() 할때부터 모든 데이터 (정보,게시글) 이 다넘어온다. 우리는 포스트만 보는거고
	}
	
	@PostMapping(value="/users/{id}/posts")// id 받고 해당 id가 존재 하면 json으로 넘어온 post내영을 java object 내용으로 바꾼 post 객체에서  우리는 해당 글을쓴유저 int id 를 setid로 넣고
	//postRespository = 게시글저장 dao? 에서 save 매소드 의 인수로 게시글 내용+쓴사람을 넣어서 DB에 저장해준다.
	//추가적으로 정상저장됫다는 신호를 보내기 위해 responseEntity 메소드를 이용하여 주소를 만들어 리턴해준다.
	public ResponseEntity<Post> createPost(@PathVariable int id,@RequestBody Post post){
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("해당 번호의 유저는 존재 하지 않습니다");
		}
		post.setUser(user.get());
		Post savedPost = postRepository.save(post);
		
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedPost.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
}
