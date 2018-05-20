const util = require('../../utils/util.js');
const app = getApp();
// pages/addEvent/addEvent.js
Page({
  getCode: function () {
    let codeT = Math.floor(Math.random() * (9999 - 1000)) + 1000;
    this.setData({
      code: codeT,
    })
  },
  bindTitleInput: function (e) {
    this.setData({
      title: e.detail.value,
    })
  },
  bindIntroInput: function (e) {
    this.setData({
      intro: e.detail.value,
    })
  },
  submit: function () {
  
    if(this.data.title==null||this.data.title.trim().length<=0){
      wx.showModal({
        title: '',
        content: '请输入活动名称',
      })
      return false;
    }

    let toast = wx.showToast({
      title: '提交中',
      icon:'loading'
    })
    wx.request({
      url: app.globalData.apiUrl + 'signevent',
      method: 'PUT',
      header: {
        'token': app.globalData.token,
      },
      data: {
        code: this.data.code,
        departId: this.data.departId,
        title: this.data.title,
        intro: this.data.intro,
        startTime: this.data.startDate + 'T' + this.data.startTime + ':00.000+0800',
        endTime: this.data.endDate + 'T' + this.data.endTime + ':00.000+0800',
        location: this.data.location,
      },
      success:res=>{
        toast = wx.showToast({
          title: res.data.msg,
          icon:'success'
        })

      },
      complete:res=>{
        
      }
    })
  },
  confirmTitle: function () {
    this.setData({
      titleFocus: false,
      introFocus: true,
    })
  },
  bindPickerChange: function (e) {
    this.setData({
      departId: e.detail.value,
    })
  },
  limitDepartChange: function (e) {
    if (e.detail.value == false) {
      this.setData({
        departId: null,
      })
    }
    this.setData({
      limit: e.detail.value,
    })
  },
  loadingDepart: function (e) {
    wx.request({
      url: app.globalData.apiUrl + 'depart',
      method: 'GET',
      header: {
        'token': app.globalData.token,
      },
      success: res => {
        console.log(res.data)
        this.setData({
          departList: res.data,
        })
      }
    })
  },
  bindstartDateChange: function (e) {
    this.setData({
      startDate: e.detail.value,
    })
  },
  bindstartTimeChange: function (e) {
    this.setData({
      startTime: e.detail.value,
    })
  },
  bindendDateChange: function (e) {
    this.setData({
      endDate: e.detail.value,
    })
  },
  bindendTimeChange: function (e) {
    this.setData({
      endTime: e.detail.value,
    })
  },
  data: {
    code: null,
    title: null,
    intro: null,
    titleFocus: true,
    introFocus: false,
    limit: false,
    departList: [],
    departId: null,
    startDate: util.formatDate(new Date),
    endDate: util.formatDate(new Date),
    startTime: util.formatTime(new Date),
    endTime: util.formatTime(new Date(new Date().getTime() + 180000))
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.loadingDepart()

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
    this.getCode();
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