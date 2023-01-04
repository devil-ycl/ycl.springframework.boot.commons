package ycl.springframework.boot.commons.base.controller;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import ycl.springframework.boot.commons.base.entity.SecurityUser;
import ycl.springframework.boot.commons.models.ObjReq;
import ycl.springframework.boot.commons.models.RegisterReq;
import ycl.springframework.boot.commons.models.T;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.base.service.LoginService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author YCL
 * @date 2022/11/16 0016 14:22
 */
@RestController
@RequestMapping("/login")
@Api(tags = "登录")
@ApiOperation("登录")
@ApiSupport(order = 1)
public class LoginController {

	@Resource
	private LoginService loginService;

	@PostMapping("/login")
	@ApiOperation("登录, 用微信id")
	public ApiResult<SecurityUser> loginByWechatId(HttpServletRequest request, ObjReq<String> objReq){
		SecurityUser res = loginService.loginByWechatId(request, objReq.getObj());
		loginService.saveToken(res);
		return ApiResult.success(res);
	}

	@PostMapping("/register")
	@ApiOperation("登录, 用微信id")
	public <E extends RegisterReq> ApiResult<T> register(HttpServletRequest request, E e){
		loginService.register(request, e);
		return ApiResult.success();
	}
}
