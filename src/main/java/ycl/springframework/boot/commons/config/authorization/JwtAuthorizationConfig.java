package ycl.springframework.boot.commons.config.authorization;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.constants.GlobalConstant;
import ycl.springframework.boot.commons.enums.ApiResultEnum;
import ycl.springframework.boot.commons.utils.JwtUtil;
import ycl.springframework.boot.commons.utils.ServletUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:43
 */
@Component
public class JwtAuthorizationConfig implements HandlerInterceptor {


	@Override
	public boolean preHandle(
			HttpServletRequest request,
			@NotNull HttpServletResponse response,
			@NotNull Object handler) throws Exception {
		String token = request.getHeader(GlobalConstant.TOKEN);
		if (StrUtil.isBlank(token)) {
			ServletUtil.writeResponse(response, ApiResult.fail(ApiResultEnum.SC_UNAUTHORIZED));
			return false;
		}
		Claims claims = JwtUtil.analysisToken(token, false);
		if (claims == null) {
			ServletUtil.writeResponse(response, ApiResult.fail(ApiResultEnum.SC_UNAUTHORIZED));
			return false;
		}
		return true;
	}
}
