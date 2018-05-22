// pages/setting/setting.js
const app = getApp();
Page({
  doSubmit: function (e) {
    let loading = wx.showLoading({
      title: '提交中',
      duration:15000,
    })
    let submitUrl = 'user/student';
    if (!this.data.studentTag){
      submitUrl = 'user/info';
      this.setData({
        sID: null,
      })
    }
    wx.request({
      url: app.globalData.apiUrl + submitUrl,
      method: 'PUT',
      data: {
          name:this.data.user.name,
          sex:this.data.user.sex,
          sID: this.data.user.sID,
      },
      header: {
        'token': app.globalData.token,
      },
      success:res=>{
        wx.hideLoading();
        if(res.data.code==0){
          wx.showToast({
            title: '绑定成功',
            duration:2500,
          })
          
          app.getUser();

          setTimeout(function(){
            wx.navigateBack({
              
            })
          },1100)
          
        }else{
          wx.showToast({
            title: res.data.msg,
            image:'/resource/error.png',
          })
        }
      },
      complete:res=>{
        
      }
    });
  },
  radioChange: function (e) {
    let userSex = 'user.sex';
    this.setData({
      [userSex]: e.detail.value,
    })
  },
  bindNameInput: function (e) {
    let userName = 'user.name';
    this.setData({
      [userName]: e.detail.value,
    })
  },
  bindsIDInput: function (e) {
    let sID = 'user.sID';
    this.setData({
      [sID]: e.detail.value,
    })
  },
  bindDepartInput: function (e) {
    let departName = 'user.departName';
    this.setData({
      [departName]: e.detail.value,
    })
    console.log(this.data.user)
  },
  switchBindsID:function(e){
    this.setData({
      studentTag: !this.data.studentTag,
    })
  },
  /**
   * 页面的初始数据
   */
  data: {
    user: null,
    studentTag:false,
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