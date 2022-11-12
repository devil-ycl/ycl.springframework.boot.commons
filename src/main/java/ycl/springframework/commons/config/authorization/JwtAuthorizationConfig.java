package ycl.springframework.commons.config.authorization;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.commons.base.handler.AuthorizationHandler;
import ycl.springframework.commons.constants.GlobalConstant;
import ycl.springframework.commons.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:43
 */
@Component
public class JwtAuthorizationConfig implements AuthorizationHandler {


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader(GlobalConstant.TOKEN);
		verifyTokenIsNull(token, response);
		JwtUtil.analysisToken(token);
		return true;
	}
}
