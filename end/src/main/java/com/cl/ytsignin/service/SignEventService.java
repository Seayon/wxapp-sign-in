package com.cl.ytsignin.service;

import com.cl.ytsignin.controller.vo.SigneventVo;
import com.cl.ytsignin.dao.mapper.SigneventMapper;
import com.cl.ytsignin.dao.po.Signevent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/18
 */
@Service
public class SignEventService {
	@Autowired
	SigneventMapper signeventMapper;
	public List<SigneventVo> getSignevent(String openId) {
		return signeventMapper.selectVoByopenId(openId);
	}
	public List<SigneventVo> getSigneventUserIn(String openId) {
		return signeventMapper.selectVoUserIn(openId);
	}

	public Signevent getByEventId(int eventId) {
		return signeventMapper.selectByPrimaryKey(eventId);
	}
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
	 * @param eventId
	 * @return
	 */
	public boolean deleteSignEvent(Integer eventId) {
		return signeventMapper.deleteByPrimaryKey(eventId) > 0 ? true : false;
	}
}
