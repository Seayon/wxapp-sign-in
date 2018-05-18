package com.cl.ytsignin.controller;

import com.cl.ytsignin.dao.mapper.DepartMapper;
import com.cl.ytsignin.dao.po.Depart;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	DepartMapper departMapper;
	@RequestMapping(value = "/t")
	public @ResponseBody Depart getDepart() {
		return departMapper.selectByPrimaryKey(0);
	}
}
