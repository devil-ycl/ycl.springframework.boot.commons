package ycl.springframework.boot.commons.utils;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:06
 */
public class BaseUtil {

	public static void getPackage(AnnotationMetadata metadata) {
		AnnotationAttributes attributes = AnnotationAttributes.fromMap(metadata.getAnnotationAttributes(
				AutoConfigurationPackage.class.getName(),
				false));

	}
}
