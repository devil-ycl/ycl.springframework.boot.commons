package ycl.springframework.boot.commons.config.authorization;

import cn.hutool.core.util.StrUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.constants.GlobalConstant;
import ycl.springframework.boot.commons.constants.RedisConstant;
import ycl.springframework.boot.commons.enums.ApiResultEnum;
import ycl.springframework.boot.commons.utils.ServletUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YCL
 * @date 2022/11/12 0012 22:34
 */
@Component
public class RedisAuthorizationConfig implements HandlerInterceptor {

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request,
							@NotNull HttpServletResponse response,
							@NotNull Object handler) throws Exception {
		String token = request.getHeader(GlobalConstant.TOKEN);
		//在redis里查用户登录信息
		String key = RedisConstant.getLoginUserKey(token);
		String value = stringRedisTemplate.opsForValue().get(key);
		if (StrUtil.isBlank(value)) {
			ServletUtil.writeResponse(response, ApiResult.fail(ApiResultEnum.SC_UNAUTHORIZED));
			return false;
		}

		return true;
	}
}
