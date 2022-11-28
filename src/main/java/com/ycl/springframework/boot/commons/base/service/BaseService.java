package com.ycl.springframework.boot.commons.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycl.springframework.boot.commons.base.entity.BaseEntity;
import com.ycl.springframework.boot.commons.base.entity.SecurityUser;
import com.ycl.springframework.boot.commons.config.authorization.AuthorizationHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @date 2022/11/16 0016 17:05
 */
public interface BaseService<T extends BaseEntity> extends IService<T> {

	AuthorizationHandler authorizationHandler();

	default boolean isLogin(HttpServletRequest request) {
		return this.authorizationHandler().isLogin(request);
	}

	default SecurityUser getLoginUser(HttpServletRequest request) {
		return this.authorizationHandler().getLoginUser(request);
	}

	default SecurityUser getLoginUserNotThrow(HttpServletRequest request) {
		return this.authorizationHandler().getLoginUserNotThrow(request);
	}

	default Long getLoginUserId(HttpServletRequest request) {
		return this.authorizationHandler().getLoginUserId(request);
	}

	default Long getLoginUserIdNotThrow(HttpServletRequest request) {
		return this.authorizationHandler().getLoginUserIdNotThrow(request);
	}
}
