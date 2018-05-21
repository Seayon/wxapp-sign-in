// pages/index/index.js
const app = getApp();

Page({
  myScanCode: function (e) {
    
    wx.scanCode({
      onlyFromCamera: true,
      success: (res) => {
        console.log("'扫码得到的数据：'+")
        console.log(res)
        this.setData({
          qrResult: res.result,
        })
        this.doSign();
      }
    })
  },
  doSign: function () {
    let toast = wx.showToast({
      title: '签到中',
      icon:'loading'
    })
    console.log(this.data.qrResult)
    let result = JSON.parse(this.data.qrResult);
    console.log(result)
    wx.request({
      url: app.globalData.apiUrl + 'signrecord',
      method:'PUT',
      data: {
        eventId: result.eventId,
      },
      header: {
        'token': app.globalData.token,
      },
      success:(res)=>{
        console.log(res)
        wx.showToast({
          title: res.data.msg,
          icon: 'success'
        })
      }
    })
  },
  getUserInfoClick: function () {
    // 登录
    wx.login({
      success: res => {
        console.log(res)
        wx.request({
          url: app.globalData.apiUrl + 'login',
          data: res,
          method: 'POST',
          success: res => {
            console.log(res)
            app.globalData.token = res.data.token;
          },
          fail: res => {

          }
        })
      }
    })

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              app.globalData.userInfo = res.userInfo
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (app.userInfoReadyCallback) {
                app.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })

  },
  /**
   * 页面的初始数据
   */
  data: {
    qrResult: null,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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