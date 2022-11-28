package com.ycl.springframework.boot.commons.base.service;

import cn.hutool.core.lang.Assert;
import com.ycl.springframework.boot.commons.base.entity.SecurityUser;
import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;
import com.ycl.springframework.boot.commons.models.RegisterReq;
import com.ycl.springframework.boot.commons.properties.ProjectProperties;
import com.ycl.springframework.boot.commons.utils.JwtUtil;
import com.ycl.springframework.boot.commons.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @date 2022/11/16 0016 15:20
 */
public interface LoginService {

	SecurityUser loginByWechatId(HttpServletRequest request, String wechatId);

	<E extends RegisterReq> void register(HttpServletRequest request, E e);

	ProjectProperties properties();

	/**
	 * token 已保存到 user.token 里
	 *
	 * @param user 用户
	 */
	default void saveToken(SecurityUser user) {
		ProjectProperties properties = this.properties();
		Assert.notNull(properties, "please writed ProjectProperties in your service impl");
		AuthorizationMethodEnum method = properties.getMethod();
		switch (method) {
			case MYSQL:
			case REDIS:
				RedisUtil.createToken(user);
				break;
			case JWT:
			default:
				JwtUtil.createToken(user);
				break;
		}
	}


}
