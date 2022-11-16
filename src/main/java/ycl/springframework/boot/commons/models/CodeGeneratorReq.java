package ycl.springframework.boot.commons.models;

import lombok.Data;

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

}
