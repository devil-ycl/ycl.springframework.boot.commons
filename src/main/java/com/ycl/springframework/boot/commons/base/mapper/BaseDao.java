package com.ycl.springframework.boot.commons.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.ycl.springframework.boot.commons.base.entity.BaseEntity;

import java.util.List;

/**
 * @author YCL
 * @date 2022/11/16 0016 17:06
 */
@Mapper
@Repository
public interface BaseDao<T extends BaseEntity> extends BaseMapper<T> {


	List<T> findPage(Page<T> page, T query);


	/**
	 * 查询数据库中所有表名
	 *
	 * @param sqlName 数据库名
	 * @return 表名集合
	 */
	@Select("select table_name from information_schema.tables where table_schema = '${sqlName}'")
	List<String> selectAllTableName(String sqlName);


	/**
	 * 清空表
	 *
	 * @param tableName 表名
	 */
	@Select("truncate table ${tableName}")
	void truncate(String tableName);

}
