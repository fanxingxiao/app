package com.wbj.bigdata.logistic_warehouse.service.user.test;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {
	@Test
	public void test001(){
		String astring="123";
		//md5加密
		String md5=DigestUtils.md5DigestAsHex(astring.getBytes());
		//倒转
		String str=new StringBuilder().append(md5).reverse().toString();
		System.out.println(str);
		//再次加密
		String res=DigestUtils.md5DigestAsHex(str.getBytes());
//		System.out.println(res);
	}
}
