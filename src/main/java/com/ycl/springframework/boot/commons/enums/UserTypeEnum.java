package com.ycl.springframework.boot.commons.enums;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * 用户类型
 *
 * @author ycl
 * @date 2022-03-09 14:22:45
 */
@Getter
public enum UserTypeEnum {

	SYS_USER("sys-user"),
	APP_USER("app-user");

	public final String type;

	UserTypeEnum(String type) {
		this.type = type;
	}


	public static String getUserType(String type){
		if (StrUtil.isBlank(type))
			return SYS_USER.type;
		for (UserTypeEnum v : UserTypeEnum.values()) {
			if (type.equals(v.type))
				return v.type;
		}
		return SYS_USER.type;
	}
}
