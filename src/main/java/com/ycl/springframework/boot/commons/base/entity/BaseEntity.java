package com.ycl.springframework.boot.commons.base.entity;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import springfox.documentation.schema.Maps;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YCL
 * @date 2022/11/12 0012 21:34
 */
@Data
@Accessors(chain = true)
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -8280048470677145868L;

	@TableId(type = IdType.AUTO)
	private Long id;

	@TableField(exist = false)
	@ApiModelProperty(hidden = true)
	private Map<String, Object> params;

	public Map<String, Object> getParams() {
		if (CollUtil.isEmpty(params))
			params = new HashMap<>();
		return params;
	}
}

