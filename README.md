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

## 代码生成工具

~~~java
package ycl.lingmeng.hotel.util;


import ycl.springframework.boot.commons.models.CodeGeneratorReq;

/**
 * @author YCL
 * @date 2022/01/19 22:32:02
 */
public class CodeGeneratorUtil {

	public static void main(String[] args) {
		CodeGeneratorReq req = new CodeGeneratorReq(
				"ycl.lingmeng.hotel",
				"jdbc:mysql://localhost:3306/",
				"ycl_system",
				"root",
				"0000",
				"ycl");
		ycl.springframework.boot.commons.utils.CodeGeneratorUtil.create(req);
	}

}
~~~

## 启动类

~~~java

@SpringBootApplication(scanBasePackages = {
		"ycl.springframework.boot.commons",//common包的路径
		"ycl.lingmeng.hotel"//自己的启动器路径
})
public class HotelApplication {

}

~~~

# End
