package com.cl.ytsignin.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import com.cl.ytsignin.utils.AES;
import org.apache.log4j.Logger;
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
	private final static JSONObject TOKEN_TIMEOUT = new JSONObject();
	private final static Logger logger = Logger.getLogger(IndexInterceptor.class);

	static {
		UNAUTHORIZATION.put("msg", "身份验证失败");
		UNAUTHORIZATION.put("code", -4000);
		TOKEN_TIMEOUT.put("msg", "Token过期");
		TOKEN_TIMEOUT.put("code", -4001);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession httpSession = request.getSession();
		String token = request.getHeader("Token");

		//验证思路：从Header中获取Token值，首先解密Token值，看是否能正常解密。能的话验证解密出来的时间值和加盐的值，否则都按过期无效计算。
		try {
			JSONObject decryptJSON = JSONObject.parseObject(AES.Decrypt(token));
			if (decryptJSON.containsKey("openId") && decryptJSON.containsKey("salt") && decryptJSON.containsKey("time") && decryptJSON.getString("salt").equals(WxConfig.ENCRYPT_SALT)) {
				if ((System.currentTimeMillis() - decryptJSON.getDouble("time") < 1800000)) {
					httpSession.setAttribute("openId", decryptJSON.getString("openId"));
					return true;
				} else {
					response.setContentType("application/json; charset=utf-8");
					PrintWriter printWriter = response.getWriter();
					printWriter.write(UNAUTHORIZATION.toJSONString());
					return false;
				}

			} else {
				response.setContentType("application/json; charset=utf-8");
				PrintWriter printWriter = response.getWriter();
				printWriter.write(UNAUTHORIZATION.toJSONString());
				return false;
			}
		} catch (Exception e) {
			logger.error("Token解析失败" + e.getStackTrace());
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
