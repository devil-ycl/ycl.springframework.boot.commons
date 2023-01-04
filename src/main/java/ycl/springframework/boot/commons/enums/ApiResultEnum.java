package ycl.springframework.boot.commons.enums;

import lombok.Getter;

/**
 * @author YCL
 * @date 2022/01/24 16:11:57
 */
@Getter
public enum ApiResultEnum {
	SC_CONTINUE(100, "请求成功一部分, 请继续发送信息"),
	SC_SWITCHING_PROTOCOLS(101, "服务器切换协议"),
	SC_OK(200, "请求成功"),
	SC_CREATED(201, "完整请求, 创建新资源"),
	SC_ACCEPTED(202, "请求被处理, 但处理不完整"),
	SC_NON_AUTHORITATIVE_INFORMATION(203, ""),
	SC_NO_CONTENT(204, ""),
	SC_RESET_CONTENT(205, ""),
	SC_PARTIAL_CONTENT(206, ""),
	SC_MULTIPLE_CHOICES(300, "链接列表, 用户可选择其中一个并进入, 最多5个"),
	SC_MOVED_PERMANENTLY(301, "页面转移到新url"),
	SC_MOVED_TEMPORARILY(302, "页面临时转移到新url"),
	SC_FOUND(302, "页面可在其他url中找到"),
	SC_SEE_OTHER(303, ""),
	SC_NOT_MODIFIED(304, ""),
	SC_USE_PROXY(305, ""),
	SC_TEMPORARY_REDIRECT(307, ""),
	SC_BAD_REQUEST(400, "服务器无法处理"),
	SC_UNAUTHORIZED(401, "未登录或登录过期"),
	SC_PAYMENT_REQUIRED(402, "权限不足"),
	SC_FORBIDDEN(403, "禁止访问此页面"),
	SC_NOT_FOUND(404, "页面未找到"),
	SC_METHOD_NOT_ALLOWED(405, "方法访问不规范"),
	SC_NOT_ACCEPTABLE(406, "响应内容不被客户端接受"),
	SC_PROXY_AUTHENTICATION_REQUIRED(407, "请使用代理服务器验证"),
	SC_REQUEST_TIMEOUT(408, "请求超时"),
	SC_CONFLICT(409, "请求冲突"),
	SC_GONE(410, "页面无法使用"),
	SC_LENGTH_REQUIRED(411, "无法处理不带Content-Length的请求"),
	SC_PRECONDITION_FAILED(412, ""),
	SC_REQUEST_ENTITY_TOO_LARGE(413, "请求体过大"),
	SC_REQUEST_URI_TOO_LONG(414, "url太长"),
	SC_UNSUPPORTED_MEDIA_TYPE(415, "媒体类型不支持"),
	SC_REQUESTED_RANGE_NOT_SATISFIABLE(416, ""),
	SC_EXPECTATION_FAILED(417, ""),
	SC_INTERNAL_SERVER_ERROR(500, "服务器异常"),
	SC_NOT_IMPLEMENTED(501, ""),
	SC_BAD_GATEWAY(502, "服务器无响应"),
	SC_SERVICE_UNAVAILABLE(503, "服务器死机"),
	SC_GATEWAY_TIMEOUT(504, "网关超时"),
	SC_HTTP_VERSION_NOT_SUPPORTED(505, "HTTP协议不支持"),
	//---------------------自定义---------------------
	COLUMN_NOT_KNOW_EXCEPTION(1000, "未知异常, 请联系管理员"),
	COLUMN_TEMP_PASSWORD(1001, "临时密码"),
	COLUMN_INNER_ERROR(1002, "内部错误, 请联系管理员"),
	COLUMN_SERVER_REFUSE_OPERATION(1011, "服务器拒绝操作"),
	CUSTOM_MOBILE_REGISTER(2001, "手机号已被注册"),
	CUSTOM_VERIFY_ID_CARD_NOT_PASS(2101, "身份证校验不通过"),
	CUSTOM_FILE_UPLOAD_EXCEPTION(2201, "文件上传异常"),
	;

	public final int code;
	public final String message;


	ApiResultEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}
}
