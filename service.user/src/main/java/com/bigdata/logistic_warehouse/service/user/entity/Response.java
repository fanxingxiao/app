package com.bigdata.logistic_warehouse.service.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Response {
	OK						(200,"操作成功"),
	ERROR					(400,"操作失败"),
	NAME_NULL				(401,"用户名不能为空"),
	NAME_INVALID			(402,"用户名无效，请输入6-10位数字字母或者下划线"),
	PASSWORD_NULL			(403,"密码不能为空"),
	PASSWORD_INVALID		(404,"密码无效，请输入6-10位数字字母或者下划线"),
	USER_EXISTS				(405,"用户已存在"),
	USER_NOT_EXISTS			(406,"用户不存在"),
	PASSWORD_ERROR			(407,"密码错误")
	;
	
	private Integer code;
	private String result;
	private Response(Integer code, String result) {
		this.code = code;
		this.result = result;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
