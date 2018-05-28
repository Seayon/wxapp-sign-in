package com.cl.ytsignin.service;

import com.cl.ytsignin.dao.mapper.UserMapper;
import com.cl.ytsignin.dao.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class UserService {
	@Autowired
	UserMapper userMapper;

	public boolean insertList(List<User> userList) {
		boolean flag = false;
		Iterator iterator = userList.iterator();
		while (iterator.hasNext()) {
			User user = (User) iterator.next();
			if (userMapper.selectBysID(user.getsID()) == null) {
				if (userMapper.insert(user) > 0) {
					flag = true;
				}

			}
		}
		return flag;
	}
}