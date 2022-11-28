package com.ycl.springframework.boot.commons.config.authorization;

import org.springframework.web.servlet.HandlerInterceptor;
import com.ycl.springframework.boot.commons.base.entity.SecurityUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @date 2022/11/16 0016 17:12
 */
public interface AuthorizationHandler extends HandlerInterceptor {


	boolean isLogin(HttpServletRequest request);

	SecurityUser getLoginUser(HttpServletRequest request);

	default SecurityUser getLoginUserNotThrow(HttpServletRequest request) {
		try {
			return this.getLoginUser(request);
		} catch (Exception ignored) {
			return null;
		}
	}

	default Long getLoginUserId(HttpServletRequest request) {
		return this.getLoginUser(request).getId();
	}

	default Long getLoginUserIdNotThrow(HttpServletRequest request) {
		SecurityUser user = getLoginUserNotThrow(request);
		return user != null ? user.getId() : null;
	}
}
