package com.cl.ytsignin.controller;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/9
 * Spring统一异常处理类
 */
@ControllerAdvice
@EnableWebMvc
public class DefaultExceptionHandler implements HandlerExceptionResolver {
	private final static Logger logger = Logger.getLogger(DefaultExceptionHandler.class);

	@Override
	public @ResponseBody
	ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setView(view);
		if (e instanceof DataAccessException) {
			modelAndView.addObject("code", -2);
			modelAndView.addObject("msg", "数据操作出错");
		} else if (e instanceof InvalidFormatException) {
			modelAndView.addObject("code", -3);
			modelAndView.addObject("msg", "文件解析出错");
		} else {
			modelAndView.addObject("code", -999);
			modelAndView.addObject("msg", "未知的错误");
			logger.error(e.getStackTrace());
		}
		return modelAndView;
	}
}
