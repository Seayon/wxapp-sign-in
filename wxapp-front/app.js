//app.js
App({

  getUser:function(){
    wx.request({
      url: this.globalData.apiUrl+'user',
      header: {
        'token': this.globalData.token,
      },
      success:res=>{
        this.globalData.user = res.data;
      },
    })
  },
  
  onLaunch: function () {
    // 展示本地存储能力
    // var logs = wx.getStorageSync('logs') || []
    // logs.unshift(Date.now())
    // wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        console.log(res)
        wx.request({
          url: this.globalData.apiUrl+'login',
          data:res,
          method:'POST',
          success:res=>{
            console.log(res)
            this.globalData.token = res.data.token;
            this.getUser();
          },
          fail:res=>{
            
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
              this.globalData.userInfo = res.userInfo
              
              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
    
  },
  globalData: {
    user:null,
    userInfo: null,
    apiUrl:"http://we.t.inlit.cn/",
    token:null,
  }
})