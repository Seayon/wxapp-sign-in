package com.cl.ytsignin.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/28
 */
@RestController
@RequestMapping(value = "admin/user")
public class UserManagerController {
	@Autowired
	UserMapper userMapper;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	List<User> listUser(Integer page) {
		return userMapper.selectByLimit(page - 1, 100);
	}
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public @ResponseBody
	JSONObject deleteUser(Integer Id) {
		JSONObject result = new JSONObject();
		if (userMapper.deleteByPrimaryKey(Id) > 0) {
			result.put("code", 0);
			result.put("msg", "删除成功");
		}else{
			result.put("code", 0);
			result.put("msg", "操作失败");
		}
		return result;
	}

}
