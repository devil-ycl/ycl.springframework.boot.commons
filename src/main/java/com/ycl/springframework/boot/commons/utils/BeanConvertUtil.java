package com.ycl.springframework.boot.commons.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类型复制工具
 *
 * @author YCL
 * @date 2021-06-04 11:55:17
 */
@Slf4j
@SuppressWarnings("all")
public class BeanConvertUtil {

	private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

	public BeanConvertUtil() {
	}

	public static MapperFactory getConvert() {
		return MAPPER_FACTORY;
	}


	/**
	 * 映射实体字段, 映射一个新类, 保证两个类里面的字段和类型一致
	 *
	 * @param e   源
	 * @param t   新对象
	 * @param <T> 任意类
	 * @return 目的类
	 */
	public static <T> T convert(Object e, Class<T> t) {
		return MAPPER_FACTORY.getMapperFacade().map(e, t);
	}


	/**
	 * 映射实体字段, 调用者创建类, 保证两个类里面的字段和类型一致
	 *
	 * @param e   源
	 * @param t   新对象
	 * @param <E> 任意类
	 * @param <T> 任意类
	 */
	public static <E, T> void convert(E e, T t) {
		MAPPER_FACTORY.getMapperFacade().map(e, t);
	}

	/**
	 * 映射实体字段, 调用者创建类, 保证两个类里面的字段和类型一致
	 *
	 * @param e      源
	 * @param t      新对象
	 * @param ignore 忽略字段
	 * @param <E>    任意类
	 * @param <T>    任意类
	 */
	public static <E, T> void convert(E e, T t, String... ignore) {
		MapperFactory factory = new DefaultMapperFactory.Builder().build();
		ClassMapBuilder<?, ?> builder = factory.classMap(t.getClass(), e.getClass());
		for (String s : ignore) {
			builder.exclude(s);
		}
		builder.byDefault().register();
		factory.getMapperFacade().map(e, t);
	}


	/**
	 * 集合映射到对应的实体集合
	 *
	 * @param c   源集合
	 * @param t   新对象类
	 * @param <E> 集合
	 * @param <T> 类
	 * @return 类的对象的集合
	 */
	public static <E, T> List<T> convertList(Collection<E> c, Class<T> t) {
		return MAPPER_FACTORY.getMapperFacade().mapAsList(c, t);
	}

	public static <T> Map<String, Object> convertMap(T t) {
		if (t instanceof Void || t instanceof String
				|| t instanceof Boolean || t instanceof Byte
				|| t instanceof Character || t instanceof Short
				|| t instanceof Integer || t instanceof Float
				|| t instanceof Double || t instanceof Long)
			return new HashMap<>();
		return (Map<String, Object>) JSONObject.parseObject(JSONObject.toJSONString(t), Map.class);
	}
}
