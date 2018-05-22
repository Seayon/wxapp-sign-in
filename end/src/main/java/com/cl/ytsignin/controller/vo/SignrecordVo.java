package com.cl.ytsignin.controller.vo;

import org.springframework.stereotype.Component;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/22
 */
@Component
public class SignrecordVo {
	private Integer eventId;
	private String createTime;
	private String name;
	private String sex;
	private String clazzNo;
	private String sID;

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getClazzNo() {
		return clazzNo;
	}

	public void setClazzNo(String clazzNo) {
		this.clazzNo = clazzNo;
	}

	public String getsID() {
		return sID;
	}

	public void setsID(String sID) {
		this.sID = sID;
	}
}
