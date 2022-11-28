package com.ycl.springframework.boot.commons.exception;

import com.alibaba.fastjson2.JSONException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.ycl.springframework.boot.commons.ApiResult;

/**
 * 异常统一处理
 *
 * @author YCL
 * @date 2021-06-11 13:45:27
 */
@Order(999)
@Slf4j
@RestControllerAdvice
public class ExceptionInterception {

	/**
	 * 系统逻辑异常
	 *
	 * @param e 异常
	 * @return 统一返回值
	 */
	@ExceptionHandler(Exception.class)
	public ApiResult<Object> systemException(Exception e) {
		log.error(e.getMessage(), e);
		return ApiResult.fail("系统异常");
	}


	/**
	 * 前端传参异常
	 *
	 * @param e 异常
	 * @return 统一返回值
	 */
	@ExceptionHandler(JSONException.class)
	public ApiResult<Object> jsonException(JSONException e) {
		return ApiResult.fail("传参异常");
	}

	/**
	 * 已知异常(如 Assert 里抛出的异常)
	 *
	 * @param e 异常
	 * @return 统一返回值
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	public ApiResult<Object> illegalArgumentException(IllegalArgumentException e) {
		return ApiResult.fail(e.getMessage());
	}

	/**
	 * 校验统一异常处理: 接收前端参数校验不通过
	 *
	 * @param e 异常
	 * @return 统一返回值
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		return exceptionMsg(e.getBindingResult());
	}

	/**
	 * 异常消息处理
	 *
	 * @param e 异常消息
	 * @return 统一返回值
	 */
	private ApiResult<Object> exceptionMsg(BindingResult e) {
		return ApiResult.fail(e.getFieldErrors().get(0).getDefaultMessage());
	}

}
