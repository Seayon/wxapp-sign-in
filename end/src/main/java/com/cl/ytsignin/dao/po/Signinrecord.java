package com.cl.ytsignin.dao.po;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table signinrecord
 *
 * @mbg.generated do_not_delete_during_merge
 */
public class Signinrecord {
    /** 签到活动id*/
    private Integer eventId;

    /** 签到人OpenId*/
    private String openId;

    /** 签到时间*/
    private Date createTime;

    /** 更新时间*/
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signinrecord.eventId
     *
     * @return the value of signinrecord.eventId
     *
     * @mbg.generated
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signinrecord.eventId
     *
     * @param eventId the value for signinrecord.eventId
     *
     * @mbg.generated
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signinrecord.openId
     *
     * @return the value of signinrecord.openId
     *
     * @mbg.generated
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signinrecord.openId
     *
     * @param openId the value for signinrecord.openId
     *
     * @mbg.generated
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signinrecord.createTime
     *
     * @return the value of signinrecord.createTime
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signinrecord.createTime
     *
     * @param createTime the value for signinrecord.createTime
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column signinrecord.updateTime
     *
     * @return the value of signinrecord.updateTime
     *
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column signinrecord.updateTime
     *
     * @param updateTime the value for signinrecord.updateTime
     *
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}