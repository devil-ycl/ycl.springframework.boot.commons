package ycl.springframework.boot.commons.base.entity;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ycl.springframework.boot.commons.constants.GlobalConstant;


import java.time.LocalDateTime;

/**
 * @author YCL
 * @date 2022/9/21 0021 12:54
 */
@Getter
@Setter
@Accessors(chain = true)
public class SecurityUser {

	@ApiModelProperty("id")
	private Long id;

	@ApiModelProperty("用户名")
	private String username;

	@ApiModelProperty("用户头像")
	private String avatar;


	@ApiModelProperty("登录信息")
	private String token;

	@ApiModelProperty("用户类型，0超管，1普通用户")
	private Integer type;


	@ApiModelProperty("手机号")
	private String mobile;

	@ApiModelProperty("状态，0正常，1禁用")
	private Integer status;

	@ApiModelProperty("注册时间")
	@JsonFormat(pattern = GlobalConstant.DATE_TIME_PATTERN, timezone = GlobalConstant.DATE_TIME_GMT8)
	private LocalDateTime registerTime;

	@ApiModelProperty("登录地址")
	private String loginIp;

	@ApiModelProperty("登录时间")
	@JsonFormat(pattern = GlobalConstant.DATE_TIME_PATTERN, timezone = GlobalConstant.DATE_TIME_GMT8)
	private LocalDateTime loginTime;

	@ApiModelProperty("上次登录时间")
	@JsonFormat(pattern = GlobalConstant.DATE_TIME_PATTERN, timezone = GlobalConstant.DATE_TIME_GMT8)
	private LocalDateTime lastLoginTime;

	@ApiModelProperty("上次登录地址")
	private String lastLoginIp;

	@ApiModelProperty("退出时间")
	@JsonFormat(pattern = GlobalConstant.DATE_TIME_PATTERN, timezone = GlobalConstant.DATE_TIME_GMT8)
	private LocalDateTime logoutTime;


	/**
	 * ====================================
	 * <p>
	 * 以上是 SysUser 全部字段
	 * <p>
	 * ------------------------------------
	 * <p>
	 * 以下是自定义字段
	 * <p>
	 * ====================================
	 */

	@ApiModelProperty("是否是管理员")
	private Boolean isAdmin;

	@ApiModelProperty("是否已绑定手机号")
	private Boolean isBindMobile;

	@ApiModelProperty("是否已完成认证")
	private Boolean isAuth;


	public SecurityUser() {
		this.isAdmin = (this.type != null && this.type == 0);
		this.isBindMobile = StrUtil.isNotBlank(mobile);
	}
}
