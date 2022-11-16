package ycl.springframework.boot.commons.base.service.impl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ycl.springframework.boot.commons.base.entity.SecurityUser;
import ycl.springframework.boot.commons.base.service.LoginService;
import ycl.springframework.boot.commons.properties.ProjectProperties;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/16 0016 15:45
 */
@Service
@Lazy
public class LoginServiceImpl implements LoginService {

	@Resource
	private ProjectProperties projectProperties;

	@Override
	public SecurityUser loginByWechatId(String wechatId) {
		System.out.println(projectProperties.getMethod().string);
		throw new IllegalArgumentException(
				"this login method is temporary, user can't login by this default login method. " +
						"please override this method.");
	}
}
