package ycl.springframework.boot.commons.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author YCL
 * @date 2022/11/16 0016 18:07
 */
@Data
public class CodeGeneratorReq {

	//ycl.lingmeng.hotel
	private String packageName;
	//jdbc:mysql://localhost:3306
	private String url;
	//xxxxxxxxx
	private String database;
	//root
	private String username;
	//0000
	private String password;
	//ycl
	private String author;

	public CodeGeneratorReq(
			@NotBlank String packageName,
			@NotBlank String url,
			@NotBlank String database,
			@NotBlank String username,
			@NotBlank String password,
			@NotBlank String author) {
		this.packageName = packageName;
		this.url = url;
		this.database = database;
		this.username = username;
		this.password = password;
		this.author = author;
	}
}
