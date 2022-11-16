package ycl.springframework.boot.commons.base.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.base.entity.SecurityUser;
import ycl.springframework.boot.commons.base.service.LoginService;
import ycl.springframework.boot.commons.models.ObjReq;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/16 0016 14:22
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录")
@ApiOperation("登录")
public class LoginController {

	@Resource
	private LoginService loginService;

	@PostMapping("/login")
	@ApiOperation("登录, 用微信id")
	public ApiResult<SecurityUser> loginByWechatId(ObjReq<String> objReq){
		SecurityUser res = loginService.loginByWechatId(objReq.getObj());
		return ApiResult.success(res);
	}
}
