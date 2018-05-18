package com.cl.ytsignin.interceptor;


import com.alibaba.fastjson.JSONObject;
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
 * 普通用户页面的拦截器，需要openId来访问
 */
public class IndexInterceptor extends HandlerInterceptorAdapter {
	private final static JSONObject UNAUTHORIZATION = new JSONObject();
	static {
		UNAUTHORIZATION.put("msg", "身份验证失败");
		UNAUTHORIZATION.put("code", -4000);
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		String requestUrl = request.getRequestURI();
		String token = request.getHeader("Token");



		//Token和Session两种验证方式，Token适用于小程序，Session适用于公众号的Web版本
		if (httpSession.getAttribute("openId") != null || token != null) {
			return true;
		} else {
			response.setContentType("application/json; charset=utf-8");
			PrintWriter printWriter = response.getWriter();
			printWriter.write(UNAUTHORIZATION.toJSONString());
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
