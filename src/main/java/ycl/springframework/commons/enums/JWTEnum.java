package ycl.springframework.commons.enums;

/**
 * JWT枚举
 *
 * @author YCL
 * @date 2022/9/22 0022 9:22
 */
public enum JWTEnum {

	USER_ID("userId"),
	USERNAME("username"),
	ROLE("rol"),
	TYPE("typ"),
	;

	public final String key;

	JWTEnum(String key) {
		this.key = key;
	}
}
