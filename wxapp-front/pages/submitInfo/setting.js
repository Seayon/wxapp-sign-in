// pages/setting/setting.js
const app = getApp();
Page({
  doSubmit:function(e){
      wx.request({
        url: '',
      })
  },
  radioChange:function(e){
    let userSex = 'user.sex';
    this.setData({
      [userSex]:e.detail.value,
    })
  },
  bindNameInput: function (e) {
    let userName = 'user.name';
    this.setData({
      [userName]: e.detail.value,
    })
    console.log(e.detail.value)
  },
  bindDepartInput: function (e) {
    let departName = 'user.departName';
    this.setData({
      [departName]: e.detail.value,
    })
    console.log(this.data.user)
  },
  /**
   * 页面的初始数据
   */
  data: {
    user: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      user: app.globalData.user,
    })
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