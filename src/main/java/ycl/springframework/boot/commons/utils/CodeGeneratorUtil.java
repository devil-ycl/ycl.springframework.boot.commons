package ycl.springframework.boot.commons.utils;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import ycl.springframework.boot.commons.base.entity.BaseEntity;
import ycl.springframework.boot.commons.base.mapper.BaseDao;
import ycl.springframework.boot.commons.base.service.BaseService;
import ycl.springframework.boot.commons.base.service.impl.BaseServiceImpl;
import ycl.springframework.boot.commons.base.controller.BaseController;
import ycl.springframework.boot.commons.models.CodeGeneratorReq;

import java.util.Collections;

/**
 * @author YCL
 * @date 2022/01/19 22:32:02
 */
public class CodeGeneratorUtil {

	private static final String templatesPrefix = "/templates/";
	private static final String property = System.getProperty("user.dir");

	public static void create(CodeGeneratorReq req) {
		String finalModuleName = property;
		FastAutoGenerator.create(
				req.getUrl() + req.getDatabase(),
				req.getUsername(),
				req.getPassword()
		).globalConfig(builder -> {
			builder.author(req.getAuthor())               //作者
					.outputDir(finalModuleName + "\\src\\main\\java")    //输出路径(写到java目录)
					.enableSwagger()           //开启swagger
					.commentDate("yyyy-MM-dd HH:mm:ss");
			//.fileOverride();            //开启覆盖之前生成的文件
		}).packageConfig(builder -> builder.parent(req.getPackageName())//包名输出
				.entity("entity")//实体类位置
				.service("service")//逻辑层位置
				.serviceImpl("service.impl")//逻辑实现类位置
				.controller("controller")//控制器位置
				.mapper("mapper")//数据库层位置
				.xml("mapper")//xml位置
				//xml输出位置
				.pathInfo(Collections.singletonMap(OutputFile.xml, finalModuleName + "\\src\\main\\resources\\mapper"))
		).strategyConfig(builder -> builder
				//实体类策略
				.entityBuilder()
				.formatFileName("%s")
				.superClass(BaseEntity.class)
				.enableLombok()
				.enableChainModel()
				.logicDeleteColumnName("deleted")
				.enableTableFieldAnnotation()
				.enableActiveRecord()

				//控制器策略
				.controllerBuilder()
				.superClass(BaseController.class)
				.formatFileName("%sController")
				.enableRestStyle()

				//数据库策略
				.mapperBuilder()
				.enableBaseResultMap()  //生成通用的resultMap
				.superClass(BaseDao.class)
				.enableBaseColumnList()
				.formatMapperFileName("%sMapper")
				.enableMapperAnnotation()
				.formatXmlFileName("%sMapper")

				//逻辑层策略
				.serviceBuilder()
				.superServiceClass(BaseService.class)
				.formatServiceFileName("%sService")
				.superServiceImplClass(BaseServiceImpl.class)
				.formatServiceImplFileName("%sServiceImpl")
		).templateConfig(builder -> builder.disable(TemplateType.ENTITY)
				.entity(templatesPrefix + "entity.java")
				.service(templatesPrefix + "service.java")
				.serviceImpl(templatesPrefix + "serviceImpl.java")
				.mapper(templatesPrefix + "mapper.java")
				.xml(templatesPrefix + "mapper.xml")
				.controller(templatesPrefix + "controller.java")
		).execute();
	}


}
