package ycl.springframework.boot.commons.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ycl.springframework.boot.commons.constants.GlobalConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置
 *
 * @author: YCL
 * @date: 2021-06-06 15:00:00
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {

	private final String packageImport = "ycl.springframework.controller";
	private final String title = "swagger测试";
	private final String description = "swagger-bootstrap-ui";
	private final String serviceUrl = "http://localhost:8079/";
	private final String version = "1.0";

	@Bean
	public Docket createSwaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage(packageImport))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}


	private List<SecurityContext> securityContexts() {
		ArrayList<SecurityContext> list = new ArrayList<>();
		SecurityContext build = SecurityContext.builder()
				.securityReferences(securityReferences())
				.build();
		list.add(build);
		return list;
	}

	/**
	 * 全局认证
	 *
	 * @return
	 */
	private List<SecurityReference> securityReferences() {
		AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] scopes = new AuthorizationScope[1];
		scopes[0] = scope;
		ArrayList<SecurityReference> list = new ArrayList<>();
		list.add(new SecurityReference(GlobalConstant.TOKEN, scopes));
		return list;
	}

	/**
	 * 默认请求头token
	 *
	 * @return
	 */
	private List<SecurityScheme> securitySchemes() {
		ArrayList<SecurityScheme> list = new ArrayList<>();
		list.add(new ApiKey(GlobalConstant.TOKEN, GlobalConstant.TOKEN, "header"));
		return list;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(title)
				.description(description)
				.termsOfServiceUrl(serviceUrl)
				.version(version)
				.build();
	}
}
