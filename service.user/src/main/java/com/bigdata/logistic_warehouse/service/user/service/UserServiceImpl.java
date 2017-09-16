package com.bigdata.logistic_warehouse.service.user.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bigdata.logistic_warehouse.service.user.entity.Response;
import com.bigdata.logistic_warehouse.service.user.entity.User;
import com.bigdata.logistic_warehouse.service.user.redis.RedisService;
import com.bigdata.logistic_warehouse.service.user.repository.UserRepository;
import com.bigdata.logistic_warehouse.service.user.util.CookieUtils;
import com.bigdata.logistic_warehouse.service.user.util.MD5Encryp;
import com.google.gson.Gson;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RedisService redisService;
	@Value("${redis.ttl}")
	private Integer redis_ttl;
	@Value("${cookie.name}")
	private String cookie_name;
	@Override
	public Response login(User user,HttpServletRequest request,HttpServletResponse response) {
		//验证
		if(StringUtils.isBlank(user.getName()))
			return Response.NAME_NULL;
		if(!user.getName().matches("\\w{6,10}"))
			return Response.NAME_INVALID;
		if(StringUtils.isBlank(user.getPassword()))
			return Response.PASSWORD_NULL;
		if(!user.getPassword().matches("\\w{6,10}"))
			return Response.PASSWORD_INVALID;
		//查库
		User findByName = userRepository.findByName(user.getName());
		if(findByName==null)
			return Response.USER_NOT_EXISTS;
		if(!findByName.getPassword().equals(MD5Encryp.md5Encrypt(user.getPassword())))
			return Response.PASSWORD_ERROR;
		//存储用户信息-session
		//(1)用户信息存储在redis中，key是sessionId,value是用户信息
		//获取一个唯一标识（sessionId）
		String sessionId = UUID.randomUUID().toString();
		String userString=findByName.toString();
		redisService.set(sessionId, userString, redis_ttl);
		//(2)将key响应回用户，存储在cookie中
		//request:取cookie
		//response:存cookie
		CookieUtils.setCookie(request, response, cookie_name, sessionId, redis_ttl);
		return Response.OK;
	}

	@Override
	public Response logout() {
		//清空session
		
		
		
		return null;
	}

	@Override
	public Response register(User user) {
		//验证
		if(StringUtils.isBlank(user.getName()))
			return Response.NAME_NULL;
		if(!user.getName().matches("\\w{6,10}"))
			return Response.NAME_INVALID;
		if(StringUtils.isBlank(user.getPassword()))
			return Response.PASSWORD_NULL;
		if(!user.getPassword().matches("\\w{6,10}"))
			return Response.PASSWORD_INVALID;
		//去重
		User user2=userRepository.findByName(user.getName());
		if(user2!=null)
			return Response.USER_EXISTS;
		//入库
		//为了保证安全性，需要对密码进行加密，MD5加密技术-----不可逆的技术
		user.setPassword(MD5Encryp.md5Encrypt(user.getPassword()));
		User save = userRepository.save(user);
		if(save!=null)
			return Response.OK;
		return Response.ERROR;
	}

	@Override
	public User getUser(HttpServletRequest request) {
		//获取cookie
				String sessionId = CookieUtils.getCookieValue(request, cookie_name);
				if(StringUtils.isBlank(sessionId))
					return null;
				Object object = redisService.get(sessionId);
				if(object==null)
					return null;
				String userString=(String)object;
				User fromJson = new Gson().fromJson(userString,User.class);
				if(fromJson==null)
					return null;
				//初始化过期时间
				redisService.set(sessionId, userString, redis_ttl);
				return fromJson;
	}
}
