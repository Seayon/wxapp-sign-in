package com.cl.ytsignin.interceptor;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @Version 1.0
 * @author: Seayon
 * @Date: 2018/3/14
 * 管理员后台管理系统页面拦截器
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("admin") != null) {
			return true;
		} else {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(IndexInterceptor.UNAUTHORIZATION.toJSONString());
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response,
						   Object handler,
						   ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
