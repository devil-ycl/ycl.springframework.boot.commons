package ycl.springframework.boot.commons.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 登录成功返回
 *
 * @author YCL
 * @date: 2021-06-14 22:47:26
 */
@Data
@ApiModel("登录成功返回")
public class LoginRes {

	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "用户名")
	private String username;

	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "性别，1男，0女")
	private Boolean sex;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "状态，0正常，1禁用")
	private Integer status;

	@ApiModelProperty(value = "登录地址")
	private String loginIp;

	@ApiModelProperty(value = "登录时间")
	private LocalDateTime loginTime;

	@ApiModelProperty(value = "上次登录地址")
	private String lastLoginIp;

	@ApiModelProperty(value = "上次登录时间")
	private LocalDateTime lastLoginTime;
}
