package com.cl.ytsignin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.controller.vo.SigneventVo;
import com.cl.ytsignin.dao.po.Signevent;
import com.cl.ytsignin.service.SignEventService;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	List<SigneventVo> getUserSignevent(HttpServletRequest request) throws Exception {
		List<SigneventVo> signeventVoList  =signEventService.getSignevent((String) request.getSession().getAttribute("openId"));
		return signeventVoList;
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject addSignevent(HttpServletRequest request, @RequestBody Signevent signevent) {
		JSONObject resultJSON = new JSONObject();
		//Signevent signevent = new Signevent();
		//signevent.setCode(requestJSON.getString("code"));
		//signevent.setDepartId(requestJSON.getInteger("departId"));
		//signevent.setIntro(requestJSON.getString("intro"));
		//signevent.setLocation(requestJSON.getString("location"));
		//signevent.setTitle(requestJSON.getString("title"));
		//signevent.setStartTime(new Date());
		//signevent.setEndTime(new Date());
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
