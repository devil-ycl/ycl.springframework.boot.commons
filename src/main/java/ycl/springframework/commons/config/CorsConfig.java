package ycl.springframework.commons.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:39
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {


	@Resource
	private HandlerInterceptor handlerInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
				.addResourceLocations(
						"classpath:/META-INF/resources/"
						, "classpath:/resources/"
						, "classpath:/static/"
						, "classpath:/public/"
				)
		;
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> paths = new ArrayList<>();
		//统一放行公共资源
		paths.add("/swagger-resources/**");
		paths.add("/webjars/**");
		paths.add("/v2/**");
		paths.add("/swagger-ui.html/**");
		paths.add("/doc.html/**");
		paths.add("/api-docs-ext/**");

		//放行特例资源
		paths.add("/region-country/getAll");
		paths.add("/sms/**");
		paths.add("/region/**");
		paths.add("/login/**");
		paths.add("/upload/**");
		paths.add("/gps/**");
		registry.addInterceptor(handlerInterceptor)
				.addPathPatterns("/*", "/**", "/**/**")
				.excludePathPatterns(paths);
	}
}
