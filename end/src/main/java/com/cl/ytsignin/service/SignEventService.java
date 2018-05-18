package com.cl.ytsignin.service;

import com.cl.ytsignin.dao.mapper.SigneventMapper;
import com.cl.ytsignin.dao.po.Signevent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@Service
public class SignEventService {
	@Autowired
	SigneventMapper signeventMapper;

	/**
	 * 发起一个签到活动
	 *
	 * @param signevent
	 * @return
	 */
	public boolean addSignEvent(Signevent signevent) {
		return signeventMapper.insert(signevent) > 0 ? true : false;
	}

	/**
	 * 修改一个签到
	 *
	 * @param signevent
	 * @return
	 */
	public boolean patchSignEvent(Signevent signevent) {
		return signeventMapper.updateByPrimaryKey(signevent) > 0 ? true : false;
	}

	/**
	 * 删除一个签到
	 * @param signevent
	 * @return
	 */
	public boolean deleteSignEvent(Signevent signevent) {
		return signeventMapper.deleteByPrimaryKey(signevent.getEventId()) > 0 ? true : false;
	}
}
