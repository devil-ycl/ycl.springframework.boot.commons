# 工程引用必读

## 须在配置文件中配置:
### 1. 数据库映射地址
~~~yaml
spring:
    datasource:
        url: jdbc:mysql://localhost:3306/ycl_system?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowMultiQueries=true
~~~


### 2. 工程包地址及认证方法(目前支持2种: redis / jwt)
~~~yaml
project:
    controllerPackage: ycl.springframework.test.controller
    method: REDIS
~~~




## 须在自己的工程中配置swagger
~~~java
package ycl.springframework.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ycl.springframework.boot.commons.config.BaseSwaggerConfig;

/**
 * swagger配置
 *
 * @author YCL
 * @date: 2021-06-06 15:00:00
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

	public SwaggerConfig(
			@Value("${project.controllerPackage}") String packageImport) {
		super(packageImport, "swagger-test");
	}

}
~~~


# End
