package com.ycl.springframework.boot.commons.utils;

import com.alibaba.fastjson2.JSONObject;
import com.ycl.springframework.boot.commons.base.entity.SecurityUser;
import com.ycl.springframework.boot.commons.constants.SecurityConstant;
import com.ycl.springframework.boot.commons.enums.ApiResultEnum;
import com.ycl.springframework.boot.commons.enums.JWTEnum;
import com.ycl.springframework.boot.commons.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:59
 */
public class JwtUtil {


	protected static final String TOKEN_PREFIX = "Banner ";
	protected static final long EXPIRE_TIME = 1000 * 60 * 60;//expire time 1 hour


	/**
	 * 创建 token
	 *
	 * @param user 用户
	 * @return token
	 */
	public static void createToken(@NotNull(message = "未找到用户") SecurityUser user) {
		String username = user.getUsername();
		Map<String, Object> userInfo = BeanConvertUtil.convertMap(user);
		Map<String, Object> map = new HashMap<>(userInfo);

		String token = Jwts.builder()
				.setSubject(username)
				.setClaims(map)
				.claim(JWTEnum.USERNAME.key, username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstant.SIGN_KEY)
				.compact();
		user.setToken(TOKEN_PREFIX + token);
	}


	/**
	 * 解析token, 解析有异常返回null
	 *
	 * @param token token
	 * @return 主体
	 */
	public static Claims analysisToken(String token) {
		return analysisToken(token, true);
	}

	/**
	 * 解析token, 解析有异常返回null
	 *
	 * @param token token
	 * @return 主体
	 */
	public static Claims analysisToken(String token, boolean thr) {
		try {
			String s = token.substring(TOKEN_PREFIX.length());
			return Jwts.parser()
					.setSigningKey(SecurityConstant.SIGN_KEY)
					.parseClaimsJws(s)
					.getBody();
		} catch (Exception ignored) {
			if (thr)
				throw new CustomException(ApiResultEnum.SC_UNAUTHORIZED);
		}
		return null;
	}


	public static <T> T getMessage(String token, JWTEnum e, Class<T> c) {
		Claims claims = analysisToken(token);
		String msg = claims.get(e.key).toString();
		return JSONObject.parseObject(msg, c);
	}


	public static boolean notExpire(String token) {
		try {
			String s = token.substring(TOKEN_PREFIX.length());
			Jwts.parser()
					.setSigningKey(SecurityConstant.SIGN_KEY)
					.parseClaimsJws(s)
					.getBody();
			return true;
		} catch (Exception ignored) {
			return false;
		}
	}

}
