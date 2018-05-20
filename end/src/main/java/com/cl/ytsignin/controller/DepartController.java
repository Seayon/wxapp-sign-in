package com.cl.ytsignin.controller;

import com.cl.ytsignin.dao.mapper.DepartMapper;
import com.cl.ytsignin.dao.po.Depart;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/20
 */
@RestController
@RequestMapping(value = "/depart")
public class DepartController {
	@Autowired
	List<Depart> departList;
	@Autowired
	DepartMapper departMapper;
	@ApiOperation(value = "获取全部部门")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody
	List<Depart> getDeparts() {
		departList = departMapper.selectAll(0, 100);
		return departList;
	}
	@ApiOperation(value = "根据部门id获取部门信息")
	@RequestMapping(value = "/{departId}",method = RequestMethod.GET)
	public @ResponseBody Depart getDepart(@PathVariable(value = "departID")Integer departId){
		return departMapper.selectByPrimaryKey(departId);
	}
}
