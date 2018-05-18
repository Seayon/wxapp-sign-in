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
		UNAUTHORIZATION.put("msg", "页面已过期，请重新进入页面");
		UNAUTHORIZATION.put("code", -4001);
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		String requestUrl = request.getRequestURI();
		String token = request.getHeader("Token");

		//注入一个openid和用户信息测试使用，后期需要移除！！！
		httpSession.setAttribute("openId","o03rW0hI3_njQw78qlZr1uDqhwl8");
		httpSession.setAttribute("userInfo", JSONObject.parseObject("{\n" +
				"\t\"openid\": \"o03rW0hI3_njQw78qlZr1uDqhwl8\",\n" +
				"\t\"nickname\": \"Seayon | 阿阳\",\n" +
				"\t\"sex\": \"1\",\n" +
				"\t\"province\": \"关岛\",\n" +
				"\t\"city\": \"CITY\",\n" +
				"\t\"country\": \"中国\",\n" +
				"\t\"headimgurl\": \"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46\",\n" +
				"\t\"privilege\": \"\",\n" +
				"\t\"unionid\": \"o6_bmasdasdsad6_2sgVt7hMZOPfL\"\n" +
				"}"));

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
