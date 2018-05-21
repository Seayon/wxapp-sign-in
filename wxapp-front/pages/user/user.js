//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
  },
  onGotUserInfo: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        },
        fail: res => {
          let that = this;
          wx.showModal({
            title: '',
            content: '授权后才可以更好的使用本应用，请您点击确定进行授权',
            success: function (res) {
              if (res.confirm) {
                wx.openSetting({
                  success: (res) => {
                    if (res.authSetting["scope.userInfo"]) {
                      wx.getUserInfo({
                        success: res => {
                          app.globalData.userInfo = res.userInfo;
                          that.setData({
                            userInfo: res.userInfo,
                            hasUserInfo: true
                          })
                        }
                      })
                    }
                  },
                  fail: function (res) {

                  }
                })
              }
            }
          })
        }
      })
    }
  },

  
  onShow: function () {

  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
