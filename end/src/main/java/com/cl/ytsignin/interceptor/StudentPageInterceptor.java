package com.cl.ytsignin.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.seayon.dao.po.Student;
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
 * 需要学生身份来访问的页面的拦截器
 */
public class StudentPageInterceptor extends HandlerInterceptorAdapter {
	private final static JSONObject UNAUTHORIZATION = new JSONObject();

	static {
		UNAUTHORIZATION.put("msg", "未绑定学号");
		UNAUTHORIZATION.put("code", -4002);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		String requestUrl = request.getRequestURI();
		String token = request.getHeader("Token");
		//System.setProperty("http.proxyHost", "127.0.0.1");
		//System.setProperty("https.proxyHost", "127.0.0.1");
		//System.setProperty("http.proxyPort", "8888");
		//System.setProperty("https.proxyPort", "8888");
		//注入一个学生用户信息测试使用，后期需要移除！！！
		Student student = new Student();
		//student.setsID("Z17095621");
		//student.setsID("Z17125131");
		student.setsID("B16070914");
		student.setPw("199858");
		//student.setsID("B17033119");
		//student.setPw("147044");
		httpSession.setAttribute("student", student);
		if (httpSession.getAttribute("student") != null) {
			return true;
		}else{
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
