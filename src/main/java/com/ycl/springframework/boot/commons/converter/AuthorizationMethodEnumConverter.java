package com.ycl.springframework.boot.commons.converter;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

/**
 * @author YCL
 * @date 2022/11/16 0016 11:27
 */
@Component
@ConfigurationPropertiesBinding
public class AuthorizationMethodEnumConverter
		implements Converter<String, AuthorizationMethodEnum> {


	@Override
	public AuthorizationMethodEnum convert(@NotNull("请配置登录方式") String source) {
		return AuthorizationMethodEnum.get(source);
	}
}
