package com.cl.ytsignin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.controller.vo.SigneventVo;
import com.cl.ytsignin.dao.po.Signevent;
import com.cl.ytsignin.service.SignEventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@RestController
@RequestMapping(value = "/signevent")
public class SigneventController {
	@Autowired
	SignEventService signEventService;

	@ApiOperation(value = "获取当前登陆发起的签到")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	List<SigneventVo> getUserSignevent(HttpServletRequest request) throws Exception {
		List<SigneventVo> signeventVoList = signEventService.getSignevent((String) request.getSession().getAttribute("openId"));
		return signeventVoList;
	}

	@ApiOperation(value = "获取当前登陆参与的签到")
	@RequestMapping(value = "/userIn", method = RequestMethod.GET)
	public @ResponseBody
	List<SigneventVo> getSigneventUserIn(HttpServletRequest request) throws Exception {
		List<SigneventVo> signeventVoList = signEventService.getSigneventUserIn((String) request.getSession().getAttribute("openId"));
		return signeventVoList;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject addSignevent(HttpServletRequest request, @RequestBody Signevent signevent) {
		JSONObject resultJSON = new JSONObject();
		signevent.setOpenId((String) request.getSession().getAttribute("openId"));
		if (signEventService.addSignEvent(signevent)) {
			resultJSON.put("code", 0);
			resultJSON.put("msg", "发起签到成功！");
		} else {
			resultJSON.put("code", -2);
			resultJSON.put("msg", "操作失败");
		}
		return resultJSON;
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject deleteSignevent(HttpServletRequest request, @RequestBody JSONObject requestJSON) {
		JSONObject resultJSON = new JSONObject();
		int eventId = resultJSON.getInteger("eventId");
		String openId = (String) request.getSession().getAttribute("openId");
		Signevent signeventData = signEventService.getByEventId(eventId);
		if (openId.equals(signeventData.getEventId()) && signEventService.deleteSignEvent(eventId)) {
			resultJSON.put("code", 0);
			resultJSON.put("msg", "发起签到成功！");
		} else {
			resultJSON.put("code", -2);
			resultJSON.put("msg", "操作失败");
		}
		return resultJSON;
	}
}
