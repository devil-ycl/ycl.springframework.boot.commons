package ycl.springframework.boot.commons.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import ycl.springframework.boot.commons.base.entity.BaseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解工具
 *
 * @author YCL
 * @date: 2021-08-23 21:35:34
 */
public class AnnotationUtil {


	/**
	 * 获取实体类表名
	 *
	 * @param c   实体类的类
	 * @param <T> 实体类
	 * @return 表名
	 */
	public static <T extends BaseEntity> String getTableName(Class<T> c) {
		TableName annotation = c.getAnnotation(TableName.class);
		String s = null;
		if (annotation != null)
			s = annotation.value();
		if (StrUtil.isBlank(s))
			throw new IllegalArgumentException("非法异常: 实体类" + c + "未加上表名");
		return s;
	}


	/**
	 * 切入点获取注解
	 *
	 * @param joinPoint 切入点
	 * @param c         注解
	 * @param <T>       注解类型
	 * @return 注解
	 */
	public static <T extends Annotation> T getPointcut(JoinPoint joinPoint, Class<T> c) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		return method.getAnnotation(c);
	}

	/**
	 * 获取到HttpServletRequest
	 *
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
		if (attributes == null)
			return null;
		return (HttpServletRequest) attributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
	}
}
