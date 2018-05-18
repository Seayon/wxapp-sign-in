package com.cl.ytsignin.service;

import com.cl.ytsignin.dao.mapper.DepartMapper;
import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.po.Depart;
import com.cl.ytsignin.dao.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	DepartMapper departMapper;

	/**
	 * 绑定原有账户，根据输入的id和名称先到库里对比，对比一样就绑定，不一样不予以绑定
	 *
	 * @param id
	 * @param name
	 * @param openId
	 * @return
	 */
	public boolean bindUser(String id, String name, String openId) {
		User user = userMapper.selectByPrimaryKey(id);
		if (user!=null&&user.getId().equals(id) && user.getName().equals(name)) {
			return userMapper.updateOpenIdById(openId, id) > 0 ? true : false;
		} else {
			return false;
		}
	}

	/**
	 * 注册一个用户，新加的,并且由于部门名称的唯一性，在添加部门的时候需要
	 *
	 * @param user
	 * @param depart
	 * @return
	 */
	public boolean register(User user, Depart depart) {
		int d = departMapper.insert(depart);
		Depart depart1 = departMapper.selectByPrimaryKey(depart.getDepartId());
		user.setDepartId(depart1.getDepartId());
		int a = userMapper.insert(user);
		if (d >= 0 && a > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 解绑某个openId下对应的id
	 *
	 * @param id
	 * @param openId
	 * @return
	 */
	public boolean unBind(String id, String openId) {
		return userMapper.updateOpenIdById(null, id) > 0 ? true : false;
	}
}
