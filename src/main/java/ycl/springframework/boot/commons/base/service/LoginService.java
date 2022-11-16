package ycl.springframework.boot.commons.base.service;

import ycl.springframework.boot.commons.base.entity.SecurityUser;
import ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;
import ycl.springframework.boot.commons.utils.JwtUtil;
import ycl.springframework.boot.commons.utils.RedisUtil;

/**
 * @author YCL
 * @date 2022/11/16 0016 15:20
 */
public interface LoginService {

	SecurityUser loginByWechatId(String wechatId);

	/**
	 * token 已保存到 user.token 里
	 *
	 * @param user   用户
	 * @param method 登录方式
	 */
	default void saveToken(SecurityUser user, AuthorizationMethodEnum method) {
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
