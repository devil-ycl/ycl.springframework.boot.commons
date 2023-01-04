package com.ycl.springframework.boot.commons.utils;

import cn.hutool.core.collection.CollUtil;
import com.ycl.springframework.boot.commons.constants.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 路径工具
 *
 * @author: YCL
 * @date: 2021-06-21 22:14:00
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class UrlUtil {


	/**
	 * 为特定字段加上全部路径
	 *
	 * @param url          路径
	 * @param o            任意对象
	 * @param verifyParent 为提升性能, 由工程师定义是否需要校验父类, 如果工程师明确父类没有需要反射的字段就填false
	 */
	public static void resetUrl(String url, Object o, boolean verifyParent) {
		if (o == null)
			return;

		Class<?> c = o.getClass();
		List<Field> fields = new ArrayList<>(Arrays.asList(c.getDeclaredFields()));//反射字段

		if (verifyParent) {
			Class<?> p = c.getSuperclass();
			while (p != null) {
				fields.addAll(Arrays.asList(p.getDeclaredFields()));
				p = p.getSuperclass();
			}
		}

		if (CollUtil.isEmpty(fields))
			return;

		fields.forEach(f -> {
			String name = f.getName();
			if (name.equals("serialVersionUID"))
				return;
			if (! name.endsWith("Temp"))
				return;
			Type t = f.getGenericType();
			if (! t.equals(String.class))
				return;

			String tempName = f.getName();
			try {
				String relative = tempName.split("Temp")[0];
				String getRelative = getMethodName(relative);
				Method method = c.getMethod(getRelative);
				Object invoke = method.invoke(o);
				String value = invoke.toString();

				for (String s : GlobalConstant.FILE_UPLOAD_PREFIX) {
					if (! value.startsWith(s))
						continue;

					f.setAccessible(true);
					String setTempName = setMethodName(tempName);
					Method setMethod = c.getMethod(setTempName, String.class);
					setMethod.invoke(o, url + value);
					f.setAccessible(false);
					break;
				}
			} catch (IllegalAccessException | InvocationTargetException ignored) {
				log.error("invoke 异常, 字段: {}", tempName);
			} catch (NoSuchMethodException ignored) {
				log.error("getMethod 异常, 字段: {}", tempName);
			} catch (NullPointerException ignored) {
				log.warn("图像字段为空, 字段: {}", tempName);
			}
		});
	}

	/**
	 * 查看对象的字段名和属性值
	 *
	 * @param o 对象
	 */
	private static void seeFields(Object o) {
		Class<?> c = o.getClass();
		Field[] fields = c.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			String filedName = f.getName();
			if (filedName.equals("serialVersionUID"))
				continue;

			String getMethodName = getMethodName(filedName);
			try {
				Method m = c.getMethod(getMethodName);
				String value = String.valueOf(m.invoke(o));
				System.out.println(filedName + " : " + value);
			} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
				log.error(e.getMessage(), e);
			}
		}
	}


	/**
	 * 设置方法名
	 *
	 * @param name
	 * @return
	 */
	private static String setMethodName(String name) {
		String s = name.substring(0, 1);
		String s1 = name.replaceFirst(s, s.toUpperCase());
		return "set" + s1;
	}

	/**
	 * 获取方法名
	 *
	 * @param name
	 * @return
	 */
	private static String getMethodName(String name) {
		String s = name.substring(0, 1);
		String s1 = name.replaceFirst(s, s.toUpperCase());
		return "get" + s1;
	}


}
