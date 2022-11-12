package ycl.springframework.commons.exception;

import lombok.Getter;
import ycl.springframework.commons.enums.ApiResultEnum;


/**
 * 自定义异常
 *
 * @author ycl
 * @date 2022/6/26 0026 22:50:14
 */
@Getter
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = -549611288138443327L;

	private final ApiResultEnum enums;

	public CustomException(ApiResultEnum enums) {
		super(enums.message);
		this.enums = enums;
	}

}
