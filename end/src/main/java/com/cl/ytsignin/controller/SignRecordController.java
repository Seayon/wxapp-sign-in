package com.cl.ytsignin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.dao.mapper.SigneventMapper;
import com.cl.ytsignin.dao.mapper.SigninrecordMapper;
import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.po.Signevent;
import com.cl.ytsignin.dao.po.Signinrecord;
import com.cl.ytsignin.dao.po.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/20
 */
@RequestMapping(value = "/signrecord")
@RestController
public class SignRecordController {
	@Autowired
	SigninrecordMapper signinrecordMapper;
	@Autowired
	SigneventMapper signeventMapper;
	@Autowired
	UserMapper userMapper;

	@ApiOperation(value = "添加一个签到记录")
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject addSignRecord(@RequestBody JSONObject requestJSON,HttpServletRequest request) {
		Integer eventId = requestJSON.getInteger("eventId");
		String openId = (String) request.getSession().getAttribute("openId");
		Signevent signevent = signeventMapper.selectByPrimaryKey(eventId);
		User user = userMapper.selectByOpenId(openId);
		JSONObject resultJSON = new JSONObject();
		//需要完善签到时候的鉴权，需不需要限定部门签到之类
		//TODO
		if (signevent!=null&&signevent.getClazzNo() != null && signevent.getClazzNo().equals(user.getClazzNo())) {
			resultJSON.put("code", -4);
			resultJSON.put("msg", "您无权限签到");
		} else {
			if (signevent.getEndTime().getTime() > new Timestamp(System.currentTimeMillis()).getTime() && signevent.getStartTime().getTime() < new Timestamp(System.currentTimeMillis()).getTime()) {
				resultJSON.put("code", -3);
				resultJSON.put("msg", "签到时间不符");
			} else {
				Signinrecord signinrecord = new Signinrecord();
				Signinrecord signinrecordDB = signinrecordMapper.selectUnique(eventId, openId);
				if (signinrecordDB != null) {
					resultJSON.put("code", 0);
					resultJSON.put("msg", "您已经签到过");
				} else {
					signinrecord.setEventId(eventId);
					signinrecord.setCreateTime(new Timestamp(System.currentTimeMillis()));
					signinrecord.setOpenId(openId);
					if (signinrecordMapper.insert(signinrecord) > 1) {
						resultJSON.put("code", 0);
						resultJSON.put("msg", "签到成功");
					} else {
						resultJSON.put("code", -2);
						resultJSON.put("msg", "操作失败");
					}
				}
			}
		}

		return resultJSON;
	}


}
