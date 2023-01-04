package ycl.springframework.boot.commons;

import com.alibaba.fastjson2.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ycl.springframework.boot.commons.enums.ApiResultEnum;


import java.io.Serializable;

/**
 * 统一返回对象
 *
 * @author YCL
 * @date 2021-06-06 09:19:45
 */
@Data
@Accessors(chain = true)
public class ApiResult<T> implements Serializable {


	private static final long serialVersionUID = 2047317951422915349L;

	private T data;

	@ApiModelProperty(value = "状态:200.成功  500.失败  2.未登录  3.没有权限  4.密码错误 -1.未知异常，请联系管理员")
	private int code;

	private boolean isSuccess;

	@ApiModelProperty(value = "消息内容")
	private String msg;


	private ApiResult() {
	}

	//成功返回 --------------------


	public static <T> ApiResult<T> success() {
		return success(ApiResultEnum.SC_OK, null);
	}

	public static <T> ApiResult<T> success(T data) {
		return success(ApiResultEnum.SC_OK, data);
	}


	//失败返回 --------------------

	public static <T> ApiResult<T> fail() {
		return fail(ApiResultEnum.SC_INTERNAL_SERVER_ERROR);
	}

	public static <T> ApiResult<T> fail(T data) {
		return fail(ApiResultEnum.SC_INTERNAL_SERVER_ERROR, data);
	}

	public static <T> ApiResult<T> fail(String message) {
		return fail(ApiResultEnum.SC_INTERNAL_SERVER_ERROR.code, message, null);
	}

	public static <T> ApiResult<T> fail(String message, T data) {
		return fail(ApiResultEnum.SC_INTERNAL_SERVER_ERROR.code, message, data);
	}


	public static <T> ApiResult<T> fail(ApiResultEnum enums) {
		return fail(enums.code, enums.message, null);
	}

	public static <T> ApiResult<T> fail(ApiResultEnum enums, T data) {
		return fail(enums.code, enums.message, data);
	}

	public static <T> ApiResult<T> success(ApiResultEnum enums, T data) {
		return custom(enums.code, enums.message, data, true);
	}

	private static <T> ApiResult<T> fail(int code, String message, T data) {
		return custom(code, message, data, false);
	}

	private static <T> ApiResult<T> custom(int code, String message, T data, boolean success) {
		ApiResult<T> result = new ApiResult<>();
		result.setCode(code)
				.setMsg(message)
				.setData(data)
				.setSuccess(success);
		return result;
	}


	public static <T> ApiResult<T> error(int code, String message) {
		return fail(code, message, null);
	}

	public static <T> ApiResult<T> defaultError(Class<T> clazz) {
		return fail(ApiResultEnum.SC_INTERNAL_SERVER_ERROR, null);
	}

	public static <T> ApiResult<T> badRequest(String msg) {
		return fail(ApiResultEnum.SC_BAD_REQUEST);
	}


	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
