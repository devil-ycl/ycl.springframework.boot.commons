package ycl.springframework.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.commons.config.authorization.JwtAuthorizationConfig;
import ycl.springframework.commons.config.authorization.RedisAuthorizationConfig;
import ycl.springframework.commons.enums.AuthorizationMethodEnum;
import ycl.springframework.commons.properties.AuthorizationProperties;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/12 0012 22:49
 */
@Configuration
public class AuthorizationMethodConfig {

	@Resource
	private AuthorizationProperties authorizationProperties;

	@Bean
	public HandlerInterceptor handlerInterceptor(){
		AuthorizationMethodEnum method = authorizationProperties.getMethod();
		if (method.equals(AuthorizationMethodEnum.JWT))
			return new  JwtAuthorizationConfig();
		else if (method.equals(AuthorizationMethodEnum.REDIS))
			return new RedisAuthorizationConfig();
		else
			throw new IllegalArgumentException("login method is not configuration, unable to start.");
	}
}
