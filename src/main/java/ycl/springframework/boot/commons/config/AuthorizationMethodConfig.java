package ycl.springframework.boot.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.boot.commons.config.authorization.JwtAuthorizationConfig;
import ycl.springframework.boot.commons.config.authorization.RedisAuthorizationConfig;
import ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;
import ycl.springframework.boot.commons.properties.ProjectProperties;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/12 0012 22:49
 */
@Configuration
public class AuthorizationMethodConfig {

	@Resource
	private ProjectProperties projectProperties;

	@Bean
	public HandlerInterceptor handlerInterceptor() {
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
