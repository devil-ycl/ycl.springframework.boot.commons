package com.ycl.springframework.boot.commons.constants;

/**
 * 全局常量
 *
 * @author YCL
 * @date 2022/11/12 0012 20:57
 */
public class GlobalConstant {

	public static final String TOKEN = "token";
	public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_TIME_GMT8 = "GMT+8";
	public static final String BASE_PACKAGE = "com.ycl.springframework.boot.commons";

	private static final String UPLOAD_PATH = "/uploads/";
	//文件统一上传路径
	public static final String[] FILE_UPLOAD_PREFIX = {
			UPLOAD_PATH + "images/",
			UPLOAD_PATH + "videos/",
			UPLOAD_PATH + "audios/",
	};
}
