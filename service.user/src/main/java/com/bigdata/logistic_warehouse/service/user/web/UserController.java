package com.bigdata.logistic_warehouse.service.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bigdata.logistic_warehouse.service.user.entity.Response;
import com.bigdata.logistic_warehouse.service.user.entity.User;
import com.bigdata.logistic_warehouse.service.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 登录服务
	 * @param user
	 * @return
	 */
	@RequestMapping("/login")
	public Response login(User user,HttpServletRequest request,HttpServletResponse response){
		return userService.login(user,request,response);
	}
	/**
	 * 注销服务
	 * 服务器怎么识别那个用户注销的？
	 * 通过sessionId
	 * 
	 * 怎么存储的sessionId
	 * 存储在浏览器的cookie中了
	 * 
	 * 怎么传递的sessionId
	 * 每一次的请求都会带cookie
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/logout")
	public Response logout(){
		return userService.logout();
	}
	@RequestMapping("/register")
	public Response register(User user){
		System.out.println(user.toString());
		return userService.register(user);
	}
	/**
	 * 页面登录验证，获取登录用户信息
	 * @return
	 */
	@RequestMapping("/getUser")
	public User getUser(HttpServletRequest request){
		return userService.getUser(request);
	}
	
}
