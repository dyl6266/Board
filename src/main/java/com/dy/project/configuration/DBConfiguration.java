package com.dy.project.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/* 자바 기반의 설정 파일임을 명시 */
@Configuration
/* 설정 파일 위치 지정 */
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	/*
	 * application.properties에 설정했던 DB 관련 정보를 사용하도록 지정
	 * prefix에 지정되어 있는 값으로 시작하는 설정을 이용해서 히카리CP의 설정 파일을 생성
	 */
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}

	/* 앞에서 만든 히카리CP의 설정 파일을 이용해서 DataSource를 생성 */
	@Bean
	public DataSource dataSource() throws Exception {
		return new HikariDataSource(hikariConfig());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/sql-*.xml"));
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());

		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	/* prefix에 명시되어 있는 값으로 시작하는 설정을 이용해서 설정 파일 생성 */
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		/* MyBatis 설정을 자바 클래스로 만들어서 반환 */
		return new org.apache.ibatis.session.Configuration();
	}

}
