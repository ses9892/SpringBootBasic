package com.BootBasic.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact DEFAULT_CONTACT = new Contact("Jinho-Jang", "http://www.joneconsulting.co.kr", "ses1238@gmail.com");
	//사용자 정보를 발생하기위한 contact 객체
	
	private static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Spring Boot API", "My User Mangement"
			, "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",new ArrayList<>());

	private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMS = new HashSet<>(Arrays.asList("aplication/json","aplicaion/xml"));
	@Bean
	public Docket api() {
		System.out.println("API문서 자동실행");
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(DEFAULT_API_INFO).produces(DEFAULT_PRODUCES_AND_CONSUMS).consumes(DEFAULT_PRODUCES_AND_CONSUMS);
	}
	
	private ApiInfo apiinfo() {
		return new ApiInfoBuilder().title("Spring Boot Basic").version("1.0").description("스프링부트 프로젝트").license("라이센스").build();
	}
}
