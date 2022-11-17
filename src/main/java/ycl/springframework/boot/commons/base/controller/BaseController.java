package ycl.springframework.boot.commons.base.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ycl.springframework.boot.commons.ApiResult;
import ycl.springframework.boot.commons.base.entity.BaseEntity;
import ycl.springframework.boot.commons.base.service.BaseService;

/**
 * @author YCL
 * @date 2022/11/16 0016 17:50
 */
@RestController
public abstract class BaseController<T extends BaseEntity> {

	@Autowired
	private BaseService<T> baseService;

	@GetMapping("/getById")
	@ApiOperation("根据id获取数据")
	@ApiOperationSupport(order = 1)
	public ApiResult<? extends BaseEntity> getById(Long id){
		return ApiResult.success(baseService.getById(id));
	}



}
