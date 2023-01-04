package ycl.springframework.boot.commons.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author YCL
 * @date 2022/11/16 0016 14:19
 */
@Data
@ApiModel("任意对象请求")
public class ObjReq<T> {

	@ApiModelProperty(value = "任意对象, 由后端工程师提示传参", required = true)
	@NotNull(message = "请传入参数")
	private T obj;
}
