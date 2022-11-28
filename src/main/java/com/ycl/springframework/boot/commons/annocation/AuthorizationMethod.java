package com.ycl.springframework.boot.commons.annocation;

import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:24
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationMethod {

	boolean intercept() default true;

	AuthorizationMethodEnum method() default AuthorizationMethodEnum.JWT;
}
