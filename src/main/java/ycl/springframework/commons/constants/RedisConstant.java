package ycl.springframework.commons.constants;

import cn.hutool.core.lang.Assert;
import ycl.springframework.commons.enums.UserTypeEnum;

/**
 * redis常量参数
 *
 * @author ycl
 * @date 2022/5/24 0024 10:31:26
 */
public final class RedisConstant {


	private final static String USER_LOGIN = "USER:LOGIN:";
	private final static String USER_REGISTER = "USER:REGISTER:";
	//登录失败次数
	public final static int LOGIN_FAIL_COUNT = 5;
	//锁住时间
	public final static int LOCK_MINUTE = 10;
	//有效时间
	public final static long LOGIN_EFFECTIVE_TIME = 12;
	//邮件
	public final static String EMAIL = "EMAIL:";
	//邮件次数
	public final static String EMAIL_COUNT = "EMAIL:COUNT:";


	/**
	 * 获取用户登录的key
	 *
	 * @param token 令牌
	 * @return key
	 */
	public static String getLoginUserKey(String token) {
		return getLoginUserKey(null, token);
	}

	/**
	 * 获取用户登录的key
	 *
	 * @param loginType 登录方式
	 * @param token     令牌
	 * @return key
	 */
	public static String getLoginUserKey(String loginType, String token) {
		loginType = UserTypeEnum.getUserType(loginType);
		return USER_LOGIN + loginType + ":" + token;
	}


	/**
	 * 获取注册用户的键
	 *
	 * @param key 键
	 * @return redis键
	 */
	public static String getRegisterUserKey(String key) {
		Assert.notBlank(key, "请输入手机号");
		return USER_REGISTER + key;
	}

	/**
	 * 获取邮箱的键
	 *
	 * @param email 键
	 * @return redis键
	 */
	public static String getEmailKey(String email) {
		Assert.notBlank(email, "请传入邮箱");
		return EMAIL + email;
	}

	/**
	 * 获取邮箱的键
	 *
	 * @param email 键
	 * @return redis键
	 */
	public static String getEmailCountKey(String email) {
		Assert.notBlank(email, "请传入邮箱");
		return EMAIL_COUNT + email;
	}

	@Deprecated
	public static String getUserDetail(String token) {
		return USER_LOGIN + token + ":DETAIL";
	}

	@Deprecated
	public static String getUserPermission(String token) {
		return USER_LOGIN + token + ":PERMISSION";
	}

	@Deprecated
	public static String getUserRole(String token) {
		return USER_LOGIN + token + ":ROLE";
	}


	/**
	 * 获取 redis 上锁的 key
	 *
	 * @param key 键
	 * @return redis键
	 */
	public static String getUserLockKey(String key) {
		return "USER:LOCK:" + key;
	}

	/**
	 * 获取 redis 错误次数的 key
	 *
	 * @param key 键
	 * @return redis键
	 */
	public static String getUserLoginFailKey(String key) {
		return "USER:LOGINFAIL:" + key;
	}

	public static String getUserPasswordKey(String key) {
		return "USER:PASSWORD:" + key;
	}
}
