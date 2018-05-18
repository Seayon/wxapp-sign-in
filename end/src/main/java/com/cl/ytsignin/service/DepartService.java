package com.cl.ytsignin.service;

import com.cl.ytsignin.dao.mapper.DepartMapper;
import com.cl.ytsignin.dao.mapper.SigneventMapper;
import com.cl.ytsignin.dao.po.Depart;
import com.cl.ytsignin.dao.po.Signevent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@Component
public class DepartService {
	@Autowired
	DepartMapper departMapper;

	public boolean insertDepart(Depart depart) {
		return departMapper.insert(depart) > 0 ? true : false;
	}
}
