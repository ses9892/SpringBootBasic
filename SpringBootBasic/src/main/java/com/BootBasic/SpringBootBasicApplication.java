package com.BootBasic;

import java.util.Locale;

import org.apache.tomcat.util.descriptor.LocalResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
public class SpringBootBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBasicApplication.class, args);
	}
	
	//스프링 정보가 초기화될때 자동으로 초기화된다.
	@Bean
	public SessionLocaleResolver localResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		
		localeResolver.setDefaultLocale(Locale.KOREA);
		return localeResolver;
	}

}
	