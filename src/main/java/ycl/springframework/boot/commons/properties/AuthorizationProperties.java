package ycl.springframework.boot.commons.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import ycl.springframework.boot.commons.enums.AuthorizationMethodEnum;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:52
 */
@Data
@Component
@ConfigurationProperties(prefix = "authorization")
public class AuthorizationProperties {

	private AuthorizationMethodEnum method;
}
