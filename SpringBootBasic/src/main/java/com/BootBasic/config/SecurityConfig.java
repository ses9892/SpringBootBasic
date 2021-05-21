package com.BootBasic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration	//해당어노테이션이 등록되어 있는 클래스 들은 스프링이 시작되면서   메모리의 설정정보를 같이 로딩하게 된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) 
		throws Exception{
			auth.inMemoryAuthentication()
			.withUser("jinho")
			.password("{noop}test0000")
			.roles("USER");
		}
		//{noop} : 인코딩없이 사용할 수 있도록 동작하게 해주는 뜻
	}
	

