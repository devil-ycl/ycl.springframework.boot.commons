package com.ycl.springframework.boot.commons.enums;

import java.util.Arrays;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:25
 */
public enum AuthorizationMethodEnum {

	JWT("JWT"),
	REDIS("REDIS"),
	MYSQL("MYSQL"),
	;

	public final String string;

	AuthorizationMethodEnum(String string) {
		this.string = string;
	}

	public static AuthorizationMethodEnum get(String s) {
		return Arrays.stream(values())
				.filter(v -> s.equals(v.string))
				.findAny()
				.orElse(JWT);
	}


}
