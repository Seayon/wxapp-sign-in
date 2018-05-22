package com.cl.ytsignin.controller.vo;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * @Version 1.0
 * @author: cl
 * @Date: 2018/5/22
 */
@Component
public class StudentNoneSignrecordVo {
	private Integer id;
	private String name;
	private String sex;
	private String clazzNo;
	private String sID;
	private Timestamp createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
