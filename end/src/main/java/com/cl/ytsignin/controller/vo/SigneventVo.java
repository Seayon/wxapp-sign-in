package com.cl.ytsignin.controller.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table signevent
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class SigneventVo {
    /** 活动Id*/
    private Integer eventId;

    /** 签到码*/
    private String code;

    /** 活动名称标题*/
    private String title;

    /** 签到有效开始时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Timestamp startTime;

    /** 签到限制结束时间*/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Timestamp endTime;

    /** 可签到部门Id*/
    private Integer departId;

    /** 签到位置信息，经纬度*/
    private String location;

    /** 活动介绍文本*/
    private String intro;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.eventId
     *
     * @return the value of signevent.eventId
     *
     * @mbg.generated
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.eventId
     *
     * @param eventId the value for signevent.eventId
     *
     * @mbg.generated
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.code
     *
     * @return the value of signevent.code
     *
     * @mbg.generated
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.code
     *
     * @param code the value for signevent.code
     *
     * @mbg.generated
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.title
     *
     * @return the value of signevent.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.title
     *
     * @param title the value for signevent.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.startTime
     *
     * @return the value of signevent.startTime
     *
     * @mbg.generated
     */
    public Date getStartTime() {
        return startTime;
    }

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}



    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.departId
     *
     * @return the value of signevent.departId
     *
     * @mbg.generated
     */
    public Integer getDepartId() {
        return departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.departId
     *
     * @param departId the value for signevent.departId
     *
     * @mbg.generated
     */
    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.location
     *
     * @return the value of signevent.location
     *
     * @mbg.generated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.location
     *
     * @param location the value for signevent.location
     *
     * @mbg.generated
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signevent.intro
     *
     * @return the value of signevent.intro
     *
     * @mbg.generated
     */
    public String getIntro() {
        return intro;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signevent.intro
     *
     * @param intro the value for signevent.intro
     *
     * @mbg.generated
     */
    public void setIntro(String intro) {
        this.intro = intro;
    }
}