package ycl.springframework.commons.annocation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ycl.springframework.commons.enums.AuthorizationMethodEnum;
import ycl.springframework.commons.utils.AnnotationUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:26
 */
@Aspect
@Component
@Configuration
public class AuthorizationAspect {


	@Pointcut("@annotation(ycl.springframework.commons.annocation.AuthorizationMethod)")
	public void pointcut() {
	}


	@Before("pointcut()")
	public void before(JoinPoint joinPoint) {
		AuthorizationMethod annotation = AnnotationUtil.getPointcut(joinPoint, AuthorizationMethod.class);
		if (annotation == null || !annotation.intercept())
			return;
		HttpServletRequest request = AnnotationUtil.getHttpServletRequest();
		AuthorizationMethodEnum method = annotation.method();
	}
}
