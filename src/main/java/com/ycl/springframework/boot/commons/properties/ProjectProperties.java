package com.ycl.springframework.boot.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import com.ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

/**
 * @author YCL
 * @date 2022/11/16 0016 11:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {

	private AuthorizationMethodEnum method = AuthorizationMethodEnum.JWT;
	private String controllerPackage;
	private String title = "basic swagger tip";
	private String description = "description";
	private String serviceUrl = "serviceUrl";
	private String version = "v1.0";
}
