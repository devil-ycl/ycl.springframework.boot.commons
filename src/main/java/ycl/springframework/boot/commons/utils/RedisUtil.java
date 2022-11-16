package ycl.springframework.boot.commons.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.data.redis.core.StringRedisTemplate;
import ycl.springframework.boot.commons.base.entity.SecurityUser;
import ycl.springframework.boot.commons.constants.RedisConstant;

import javax.validation.constraints.NotNull;

/**
 * @author YCL
 * @date 2022/11/16 0016 15:31
 */
public class RedisUtil extends LoginUtil {

	private static final StringRedisTemplate STRING_REDIS_TEMPLATE = new StringRedisTemplate();


	public static void createToken(@NotNull(message = "未找到用户") SecurityUser user) {
		String old = user.getToken();
		if (StrUtil.isNotBlank(old))
			STRING_REDIS_TEMPLATE.delete(RedisConstant.getLoginUserKey(old));
		String username = user.getUsername();
		String s = username + System.currentTimeMillis();
		String token = SecureUtil.sha256(s);
		user.setToken(token);
		STRING_REDIS_TEMPLATE.opsForValue().set(
				RedisConstant.getLoginUserKey(token),
				JSONObject.toJSONString(user),
				RedisConstant.LOGIN_EFFECTIVE_TIME,
				RedisConstant.TIME_UNIT
		);
	}
}
