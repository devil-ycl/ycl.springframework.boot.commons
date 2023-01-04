package ycl.springframework.boot.commons.config.authorization;

import ycl.springframework.boot.commons.base.entity.SecurityUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @since 2022/12/16 0016 15:53
 */
@Component
public class NoneAuthorizationConfig implements AuthorizationHandler {

	@Override
	public boolean isLogin(HttpServletRequest request) {
		return false;
	}

	@Override
	public SecurityUser getLoginUser(HttpServletRequest request) {
		return null;
	}
}
