// pages/signevent/signevent.js
const util = require('../../utils/util.js');
const app = getApp();
Page({

  loadingEvent: function () {
    wx.request({
      url: app.globalData.apiUrl + 'signevent/' + this.data.eventId,
      method: 'GET',
      header: {
        'token': app.globalData.token,
      },
      success: res => {
        this.setData({
          event: res.data,
        })
        if (res.data.clazzNo != null) {
          this.loadingClazzUser();
        }
        this.loadingSignUser();
      }
    })
  },
  loadingSignUser: function () {
    wx.request({
      url: app.globalData.apiUrl + 'signrecord/signUser/' + this.data.eventId,
      method: 'GET',
      header: {
        'token': app.globalData.token,
      },
      success: res => {
        this.setData({
          signUser: res.data,
        })
      },
    })
  },
  // 如果是需要班级签到的，请求获得没有签到的人的信息
  loadingClazzUser: function () {
    wx.request({
      url: app.globalData.apiUrl + 'signrecord/noneSignStudent',
      data: {
        eventId: this.data.eventId,
        clazzNo: this.data.event.clazzNo,
      },
      method: 'POST',
      header: {
        'token': app.globalData.token,
      },
      success: res => {
        this.setData({
          noneSignStudent: res.data,

        })
      },
    })
  },
  loadingSigndata: function () {

  },
  /**m
   * 页面的初始数据
   */
  data: {
    signUser: [],
    noneSignStudent: [],
    event: null,
    eventId: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      eventId: options.eventId,
    })
    this.loadingEvent();
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})