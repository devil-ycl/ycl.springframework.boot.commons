package com.ycl.springframework.boot.commons.converter;

import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author YCL
 * @date 2022/11/16 0016 11:27
 */
@Component
@ConfigurationPropertiesBinding
public class AuthorizationMethodEnumConverter
		implements Converter<String, AuthorizationMethodEnum> {


	@Override
	@SuppressWarnings("all")
	public AuthorizationMethodEnum convert(String source) {
		return AuthorizationMethodEnum.get(source);
	}
}
