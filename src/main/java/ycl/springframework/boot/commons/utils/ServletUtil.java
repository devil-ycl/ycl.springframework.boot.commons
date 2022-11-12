package ycl.springframework.boot.commons.utils;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.enums.ApiResultEnum;
import ycl.springframework.boot.commons.exception.CustomException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:45
 */
public class ServletUtil {


	public static <T> void writeResponse(HttpServletResponse response, ApiResult<T> result) throws IOException {
		writeResponse(response, JSONObject.toJSONString(result), new CustomException(ApiResultEnum.SC_NOT_ACCEPTABLE));
	}

	public static void writeResponse(HttpServletResponse response, String s) {
		writeResponse(response, s, new CustomException(ApiResultEnum.SC_UNAUTHORIZED));
	}

	public static <T extends RuntimeException> void writeResponse(HttpServletResponse response, String s, T ex) {
		try {
			setDefaultResponse(response);
			response.getWriter().write(s);
			response.getWriter().flush();
		} catch (IOException ignored) {
			throw ex;
		}
	}


	public static void setDefaultResponse(HttpServletResponse response) {
		response.setHeader("ContentType", "application/json");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
	}

	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		assert attributes != null;
		return attributes.getRequest();
	}

	public static final String REMOTE_UNKNOWN = "unknown";

	/**
	 * 获取用户ip
	 *
	 * @param request 请求
	 * @return ip
	 */
	public static String getRemoteIp(HttpServletRequest request) {
		if (request == null)
			return REMOTE_UNKNOWN;
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || REMOTE_UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");

		if (ip == null || ip.length() == 0 || REMOTE_UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader("X-Forwarded-For");

		if (ip == null || ip.length() == 0 || REMOTE_UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");

		if (ip == null || ip.length() == 0 || REMOTE_UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getHeader("X-Real-IP");

		if (ip == null || ip.length() == 0 || REMOTE_UNKNOWN.equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();

		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip.split(",")[0];
	}

}
