// pages/signevent/signevent.js
var QRCode = require('../../utils/weapp-qrcode.js')

Page({
  
  /**
   * 页面的初始数据
   */
  data: {
    width:200,
    height:200,
  },
  setSize:function(){
    let windowwidth = wx.getSystemInfoSync().windowWidth;
    let targetWidth = windowwidth*0.6;
    this.setData({
      width: targetWidth,
      height: targetWidth
    })
  },
  loadQR: function (eventId){
    var qrcode = new QRCode('canvas', {
      text: "{\"eventId\":" + eventId + ",\"code\":\"22\"}",
      width: this.data.width,
      height: this.data.height,
      colorDark: "#000000",
      colorLight: "#ffffff",
      correctLevel: QRCode.CorrectLevel.H,
    });
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setSize();
    this.loadQR(options.eventId);
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