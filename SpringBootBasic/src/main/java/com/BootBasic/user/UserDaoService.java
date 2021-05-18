package com.BootBasic.user;

import java.util.ArrayList;
import java.util.Date;
import org.springframework.stereotype.Service;

//@Service or @Component 을붙여 스프링이 어노테이션을붙여놧기때문에 메핑할 수 있다.
@Service
//어노테이션을 붙여 줘야 컨트롤러에서 이서비스를 쓴다고 스프링에 등록시켜주기때문 
// @service -> @Autowired 로 간다.
public class UserDaoService {
//비지니스 로직에 관한 클래스 현재 메모리에관한 데이터를 사용 관계형 DB는 아직 사용하지 않는다.
//보통 관계형 DB에서는 Dao - Service 로 나뉘는데 현재 관계형 DB를 사용하지 않아 Dao가 의미가 없기때문에 DaoService라고 합친것이다.
	private static ArrayList<User> users = new ArrayList<User>();
	private static int usersCount=3;
	//3개의 데이터값이 메모리에 초기화된다.
	//static을 쓰는이유 ? 
	//관계형DB가 아니라서 데이터가 휘발성이기때문에 메모리에 저장해서 값을 유지 시키기 위함
	static {
		users.add(new User(1,"jinho",new Date()));
		users.add(new User(2,"Alice",new Date()));
		users.add(new User(3,"Elena",new Date()));
	}
	//모든 사용자를 찾아주는 메소드
	public ArrayList<User> findAll(){
		return users;
	}
	
	
	//사용자를 추가하는 데이터
	public User save(User user) {
		if(user.getId()==0) {	//추가할때 null이면
			user.setId(++usersCount);	// 4번으로 지정
		}
		users.add(user);
		return user;
	}
	
	
	// 사용자중 key값을받아 한명의 사용자를 찾아주는 메소드
	public User fineOne(int id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
}
