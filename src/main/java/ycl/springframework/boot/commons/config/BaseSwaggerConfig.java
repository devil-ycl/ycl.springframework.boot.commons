package ycl.springframework.boot.commons.config;

import cn.hutool.core.lang.Assert;
import com.google.common.base.Function;
import com.google.common.base.Optional;
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
import ycl.springframework.boot.commons.constants.GlobalConstant;

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
public abstract class BaseSwaggerConfig {

	private final String packageImport;
	private final String[] commonControllerPackages = {
			"ycl.springframework.boot.commons.base.controller",
	};
	private final String title;
	private final String description = "swagger";
	private final String serviceUrl = "none";
	private final String version = "1.0";


	public BaseSwaggerConfig(String packageImport, String title) {
		Assert.notBlank(packageImport,
				"you will write your controller package to application.yml, key: project.controllerPackage");
		this.packageImport = packageImport;
		this.title = title;
	}

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
		pkg[length] = packageImport;
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
				.title(title)
				.description(description)
				.termsOfServiceUrl(serviceUrl)
				.version(version)
				.build();
	}
}
