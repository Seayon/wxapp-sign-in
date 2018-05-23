package com.cl.ytsignin.controller;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.controller.vo.UserVo;
import com.cl.ytsignin.controller.vo.WxuserVo;
import com.cl.ytsignin.dao.mapper.SigninrecordMapper;
import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.mapper.WxuserMapper;
import com.cl.ytsignin.dao.po.User;
import com.cl.ytsignin.dao.po.Wxuser;
import groovy.util.logging.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "/student", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject bindStudent(@RequestBody JSONObject requestJSON, HttpServletRequest request) {

		JSONObject resultJSON = new JSONObject();
		String name = requestJSON.getString("name");
		String sID = requestJSON.getString("sID");
		String openId = (String) request.getSession().getAttribute("openId");
		User user = userMapper.selectBysID(sID);
		if (user != null && user.getsID().equals(sID) && user.getName().equals(name)) {
			user.setOpenId(openId);
			user.setType(2);
			//先解绑之前的
			userMapper.unBindOpenId(openId);
			int c = userMapper.updateByPrimaryKey(user);
			if (c > 0) {
				resultJSON.put("code", 0);
				resultJSON.put("msg", "绑定成功");
			} else {
				resultJSON.put("code", -2);
				resultJSON.put("msg", "操作失败");
			}
		} else {
			requestJSON.put("code", -1);
			resultJSON.put("msg", "绑定失败，账号不存在或您输入的信息有误");
		}
		return resultJSON;
	}

	@Autowired
	SigninrecordMapper signinrecordMapper;

	@RequestMapping(value = "/info", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject setInfo(@RequestBody JSONObject requsetJSON, HttpServletRequest request) {
		JSONObject resultJSON = new JSONObject();
		String openId = (String) request.getSession().getAttribute("openId");
		String name = requsetJSON.getString("name");
		String sex = requsetJSON.getString("sex");
		userMapper.unBindOpenId(openId);
		//当学生重新绑定为普通人的时候，需要把它之前的签到信息给删除
		signinrecordMapper.deleteByOpenIdWithLock(openId);
		//如果该openId已经存入的有信息，就更新
		User user = new User();
		user.setType(1);
		user.setOpenId(openId);
		user.setName(name);
		user.setSex(sex);
		user.setCreateTime(new Timestamp(System.currentTimeMillis()));
		user.setUpdateTime(new Timestamp(System.currentTimeMillis()));
		int result = userMapper.insertWithUpdate(user);
		if (result > 0) {
			resultJSON.put("code", 0);
			resultJSON.put("msg", "执行成功");
		} else {
			resultJSON.put("code", -2);
			resultJSON.put("msg", "操作失败");
		}
		return resultJSON;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	UserVo getUsert(HttpServletRequest request) {
		String openId = (String) request.getSession().getAttribute("openId");
		return userMapper.selectVoByOpenID(openId);
	}

	@RequestMapping(value = "clazzNo", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getAllClazz() {
		return userMapper.selectAllClazz();
	}

	@Autowired
	WxuserMapper wxuserMapper;

	@RequestMapping(value = "wxUser", method = RequestMethod.PUT)
	public @ResponseBody
	JSONObject saveWxUser(@RequestBody WxuserVo wxuserVo, HttpServletRequest request) {
		JSONObject resultJSON = new JSONObject();
		String openId = (String) request.getSession().getAttribute("openId");
		Wxuser wxuser = new Wxuser();
		wxuser.setAvataUrl(wxuserVo.getAvataUrl());
		wxuser.setCity(wxuserVo.getCity());
		wxuser.setCountry(wxuserVo.getCountry());
		wxuser.setGender(wxuserVo.getGender());
		wxuser.setLanguage(wxuserVo.getLanguage());
		wxuser.setNickName(wxuserVo.getNickName());
		wxuser.setProvince(wxuserVo.getProvince());
		wxuser.setOpenId(openId);
		int a = wxuserMapper.insert(wxuser);
		if (a > 0) {
			resultJSON.put("code", 0);
			resultJSON.put("msg", "执行成功");
		} else {
			resultJSON.put("code", -3);
			resultJSON.put("msg", "操作失败");
		}
		return resultJSON;
	}
}
