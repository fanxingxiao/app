package com.bigdata.logistic_warehouse.service.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata.logistic_warehouse.service.user.entity.Response;
import com.bigdata.logistic_warehouse.service.user.entity.User;

public interface UserService{
	Response login(User user,HttpServletRequest request,HttpServletResponse response);
	Response logout();
	Response register(User user);
	User getUser(HttpServletRequest request);
}
