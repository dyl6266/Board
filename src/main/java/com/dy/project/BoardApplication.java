package com.dy.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * 해당 애너테이션은 스프링 부트의 애너테이션 세 개로 구성되어 있음
 * 
 * @EnableAutoConfiguration : 스프링의 다양한 설정을 자동으로 완료할 수 있음
 * 
 * @ComponentScan : 기존의 스프링은 빈(bean) 클래스를 사용하기 위해 XML에 일일이 빈을 선언했었지만
 * 					해당 에너테이션은 컴포넌트 검색 기능을 활성화해서 자동으로 여러 가지 컴포넌트 클래스를
 * 					검색하고 검색된 컴포넌트 및 빈 클래스를 스프링 애플리케이션 컨텍스트에 등록하는 역할을 함
 * 
 * @Configuration : 해당 애너테이션이 붙은 클래스는 자바 기반의 설정 파일임을 의미
 */
@SpringBootApplication
public class BoardApplication {

	/*
	 * WAS에 배포하지 않고도 실행할 수 있는 JAR 파일로 웹 애플리케이션을 개발할 수 있게 해주는 메서드를 실행
	 * 즉, 순수 자바만을 이용해서 개발할 수 있음 (단 일부 라이브러리는 XML 기반의 설정이 필요할 수도 있음)
	 */
	public static void main(String[] args) {
		SpringApplication.run(BoardApplication.class, args);
	}

}
