package com.cl.ytsignin.interceptor;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Version 1.0
 * @author: Seayon
 * @Date: 2018/3/14
 * 管理员后台管理系统页面拦截器
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("preHandle(),在访问Controller之前被调用");
		HttpSession httpSession = request.getSession();
		String requestUrl = request.getRequestURI();
		//Token和Session两种验证方式
		if (httpSession.getAttribute("user") != null) {
			return true;
		} else {
			if (requestUrl.contains("admin")) {
				response.sendRedirect("login");
			} else {
				response.sendRedirect("index");
			}
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request,
						   HttpServletResponse response,
						   Object handler,
						   ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle(), 在访问Controller之后，访问视图之前被调用,这里可以注入一个时间到modelAndView中，用于后续视图显示");
		//modelAndView.addObject("data", "由拦截器生成的时间：" + new Date());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("afterCompletion(), 在访问视图之后被调用");
	}
}
