package com.ycl.springframework.boot.commons.config;

import cn.hutool.core.lang.Assert;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.ycl.springframework.boot.commons.properties.ProjectProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import com.ycl.springframework.boot.commons.constants.GlobalConstant;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * swagger配置
 *
 * @author YCL
 * @date 2021-06-06 15:00:00
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private final String[] commonControllerPackages = {
			GlobalConstant.BASE_PACKAGE + ".base.controller",
	};
	@Resource
	private ProjectProperties projectProperties;

	@Bean
	public Docket createSwaggerApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.apiInfo(apiInfo())
				.select()
				.apis(getPackages())
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(securitySchemes())
				.securityContexts(securityContexts());
	}

	private Predicate<RequestHandler> getPackages() {
		int length = commonControllerPackages.length;
		String[] pkg = new String[commonControllerPackages.length + 1];
		System.arraycopy(commonControllerPackages, 0, pkg, 0, length);
		String s = projectProperties.getControllerPackage();
		Assert.notBlank(s, "you will write your controller package to application.yml, key: project.controllerPackage");
		pkg[length] = s;
		return v -> declaring(v).transform(handlerPackage(pkg)).or(true);
	}

	@SuppressWarnings("all")
	private Optional<? extends Class<?>> declaring(RequestHandler handler) {
		return Optional.fromNullable(handler.declaringClass());
	}

	@SuppressWarnings("all")
	private Function<Class<?>, Boolean> handlerPackage(String[] packages) {
		return v -> {
			for (String p : packages) {
				if (v.getPackage().getName().startsWith(p))
					return true;
			}
			return false;
		};
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
	 * @return 集合
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
	 * @return 集合
	 */
	private List<SecurityScheme> securitySchemes() {
		ArrayList<SecurityScheme> list = new ArrayList<>();
		list.add(new ApiKey(GlobalConstant.TOKEN, GlobalConstant.TOKEN, "header"));
		return list;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(projectProperties.getTitle())
				.description(projectProperties.getDescription())
				.termsOfServiceUrl(projectProperties.getServiceUrl())
				.version(projectProperties.getVersion())
				.build();
	}
}
