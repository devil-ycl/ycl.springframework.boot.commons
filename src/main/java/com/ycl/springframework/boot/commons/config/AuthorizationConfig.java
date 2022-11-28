package com.ycl.springframework.boot.commons.config;

import com.ycl.springframework.boot.commons.config.authorization.AuthorizationHandler;
import com.ycl.springframework.boot.commons.config.authorization.JwtAuthorizationConfig;
import com.ycl.springframework.boot.commons.properties.ProjectProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.ycl.springframework.boot.commons.config.authorization.RedisAuthorizationConfig;
import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/12 0012 22:49
 */
@Configuration
public class AuthorizationConfig {

	@Resource
	private ProjectProperties projectProperties;

	@Bean
	public AuthorizationHandler authorizationHandler() {
		AuthorizationMethodEnum method = projectProperties.getMethod();
		switch (method) {
			case MYSQL:
			case REDIS:
				return new RedisAuthorizationConfig();
			case JWT:
			default:
				return new JwtAuthorizationConfig();
		}
	}
}
