package ycl.springframework.boot.commons.base.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ycl.springframework.boot.commons.base.entity.BaseEntity;
import ycl.springframework.boot.commons.base.mapper.BaseDao;
import ycl.springframework.boot.commons.config.authorization.AuthorizationHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ycl.springframework.boot.commons.base.service.BaseService;

import javax.annotation.Resource;

/**
 * @author YCL
 * @date 2022/11/16 0016 17:06
 */
@Service
public abstract class BaseServiceImpl<M extends BaseDao<T>, T extends BaseEntity>
		extends ServiceImpl<M, T>
		implements BaseService<T> {

	@Autowired
	private BaseDao<T> baseDao;
	@Resource
	private AuthorizationHandler authorizationHandler;

	@Override
	public AuthorizationHandler authorizationHandler() {
		return this.authorizationHandler;
	}
}
