package com.cl.ytsignin.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.configuration.WxConfig;
import com.cl.ytsignin.dao.mapper.AdminMapper;
import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.po.Admin;
import com.cl.ytsignin.dao.po.User;
import com.cl.ytsignin.utils.AES;
import com.cl.ytsignin.utils.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@RestController
@RequestMapping(value = "admin/login")
public class AdminLoginController {
	@Autowired
	AdminMapper adminMapper;
	@RequestMapping(value = "", method = RequestMethod.POST)
	public @ResponseBody
	JSONObject getToken(@RequestBody JSONObject jsonObject, HttpServletRequest request) throws Exception {
		JSONObject responseJSON = new JSONObject();
		String userName = jsonObject.getString("userName");
		String password = jsonObject.getString("password");
		Admin admin = adminMapper.selectByPrimaryKey(userName);
		if (admin != null && admin.getUsername().equals(userName) && admin.getPassword().equals(password)) {
			responseJSON.put("code", 0);
			responseJSON.put("msg", "登陆成功");
			request.getSession().setAttribute("admin", admin);
		} else {
			responseJSON.put("code", -1);
			responseJSON.put("msg", "身份校验失败");
		}
		return responseJSON;
	}
}
