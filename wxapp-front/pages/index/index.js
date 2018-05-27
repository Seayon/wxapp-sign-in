// pages/index/index.js
const app = getApp();

Page({
  myScanCode: function (e) {
    wx.scanCode({
      onlyFromCamera: true,
      success: (res) => {
        console.log(res)
        this.setData({
          qrResult: res.result,
        })
        this.doSign();
      }
    })
  },
  doSign: function () {
    console.log(this.data.qrResult)
    let result;
    try {
      result = JSON.parse(this.data.qrResult);
    } catch (e) {
      wx.showToast({
        title: '无法识别二维码',
        image: '/resource/error.png',
      })
      return;
    }


    // 先获取一下该签到活动的信息,判断签到活动是否存在
    wx.request({
      url: app.globalData.apiUrl + 'signevent/' + result.eventId,
      method: 'GET',
      header: {
        'token': app.globalData.token,
      },
      success: res => {
        this.setData({
          event: res.data,
        })
        if (this.data.event.eventId == null) {
          wx.showToast({
            title: '签到活动不存在',
            image: '/resource/error.png',
          })
          return;
        }
        if (app.globalData.user.name == null) {
          wx.navigateTo({
            url: '/pages/setting/setting',
          })
          return;
        }
        // 然后判断当前是否符合签到班级和时间等
        let timeNow = new Date();
        if (timeNow < this.data.event.startTime) {
          wx.showToast({
            title: '签到未开始',
            image: '/resource/error.png',
          })
          return;
        }
        if (timeNow > this.data.event.endTime) {
          wx.showToast({
            title: '签到时间已过',
            image: '/resource/error.png',
          })
          return;
        }
        let clazzNo = this.data.event.clazzNo;
        if (clazzNo != null && clazzNo != app.globalData.user.clazzNo) {
          wx.showToast({
            title: '您无权限签到',
            image: '/resource/error.png',
          })
          return;
        }
        let toast = wx.showToast({
          title: '签到中',
          icon: 'loading'
        })
        console.log(result)
        wx.request({
          url: app.globalData.apiUrl + 'signrecord',
          method: 'PUT',
          data: {
            eventId: result.eventId,
          },
          header: {
            'token': app.globalData.token,
          },
          success: (res) => {
            console.log(res)
            if (res.data.code == 0) {
              wx.showToast({
                title: res.data.msg,
                icon: 'success',
                duration:1500,
              })
              setTimeout(function () {
                wx.navigateTo({
                  url: '/pages/signevent/signevent?eventId=' + result.eventId + '&owner=userIn',
                })
              }, 1100)
             
            } else {
              wx.showToast({
                title: res.data.msg,
                image: '/resource/error.png'
              })
            }

          }
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
    event: null,
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