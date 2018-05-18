package com.cl.ytsignin.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.seayon.dao.po.Student;
import com.seayon.service.LibraryService;
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
public class LibraryInterceptor extends HandlerInterceptorAdapter {
	private final static JSONObject UNAUTHORIZATION = new JSONObject();

	static {
		UNAUTHORIZATION.put("msg", "图书馆密码错误");
		UNAUTHORIZATION.put("code", -4003);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("libraryService") != null) {
			return true;
		} else {
			LibraryService libraryService = new LibraryService();
			Student student = (Student) httpSession.getAttribute("student");
			if (libraryService.readerLogin(student)) {
				httpSession.setAttribute("libraryService", libraryService);
				return true;
			} else {
				response.setContentType("application/json; charset=utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.write(UNAUTHORIZATION.toJSONString());
				return false;
			}
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
