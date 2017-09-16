package com.bigdata.logistic_warehouse.service.user.util;

import org.springframework.util.DigestUtils;

public class MD5Encryp {
	public static String md5Encrypt(String old){
		//md5加密
		String md5=DigestUtils.md5DigestAsHex(old.getBytes());
		//倒转
		String str=new StringBuilder(md5).reverse().toString();
		//再次加密
		String res=DigestUtils.md5DigestAsHex(str.getBytes());
		return res;
	}
}
