package ycl.springframework.commons.base.handler;

import cn.hutool.core.util.StrUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import ycl.springframework.commons.ApiResult;
import ycl.springframework.commons.enums.ApiResultEnum;
import ycl.springframework.commons.exception.CustomException;
import ycl.springframework.commons.utils.ServletUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:46
 */
public interface AuthorizationHandler extends HandlerInterceptor {

	default void verifyTokenIsNull(String token, HttpServletResponse response) {
		if (StrUtil.isBlank(token)) {
			try {
				ServletUtil.writeResponse(response, ApiResult.fail(ApiResultEnum.SC_UNAUTHORIZED));
			}catch (IOException ignored){
				throw new CustomException(ApiResultEnum.SC_INTERNAL_SERVER_ERROR);
			}
		}
	}


}
