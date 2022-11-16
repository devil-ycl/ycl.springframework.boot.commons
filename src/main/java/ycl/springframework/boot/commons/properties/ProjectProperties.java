package ycl.springframework.boot.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

/**
 * @author YCL
 * @date 2022/11/16 0016 11:12
 */
@Data
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectProperties {

	private String controllerPackage;
	private AuthorizationMethodEnum method = AuthorizationMethodEnum.JWT;
}
