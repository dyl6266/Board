//package com.dy.project.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//			.antMatchers("/admin/**").hasRole("ADMIN")
//			.antMatchers("/comment/**").hasAnyRole("ADMIN, USER")
//			.anyRequest().authenticated();
////			.antMatchers("/**").authenticated();
//		
////		http.csrf().disable();
//
//		http.formLogin()
//			.loginPage("/login.do")
//			.loginProcessingUrl("/authenticate")
//			.defaultSuccessUrl("/board/list.do")
////			.successHandler(null)
//			.failureUrl("/login?error")
////			.failureHandler(null)
//			.usernameParameter("email")
//			.passwordParameter("password")
//			.permitAll();
//
//		http.logout()
////			.logoutRequestMatcher(logoutRequestMatcher)
//			.logoutUrl("/logout")
//			.invalidateHttpSession(true)
//			.logoutSuccessUrl("/login.do?logout")
//			.permitAll();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		super.configure(auth);
//	}
//
//	@Bean
//	/* 스프링에서 권장하는 Hash 알고리즘 */
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//
//}
