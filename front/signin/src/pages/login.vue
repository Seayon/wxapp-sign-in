<template>
  <div class="container-fluid container-wrap">
    <h2>羽田签到后台</h2>
    <form>
      <div class="form-group">
        <label for="userName">用户名</label>
        <input type="text" class="form-control" v-on:keyup.enter="onSubmit" id="userName" placeholder="用户名" v-model="userName" />
      </div>
      <div class="form-group">
        <label for="password">密码</label>
        <input type="password" v-on:keyup.enter="onSubmit" class="form-control" id="password" placeholder="密码" v-model="password" />
      </div>
      <button type="button" v-on:click="onSubmit" class="btn btn-default">登陆</button>
    </form>
    <div class="alert alert-danger" role="alert" v-if="alertText.length>0">{{alertText}}</div>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    methods: {
      onSubmit: function () {
        this.alertText = "";
        let that = this;
        axios({
          url:'admin/login',
          method: "POST",
          data: {
            userName: that.userName,
            password: that.password,
          }
        }).then(res => {
            let data = res.data;
            if (data.code==0){
              that.$router.push('index')
            }else{
              that.alertText = data.msg;
            }
        }).catch(res => {
          that.alertText = "服务器错误";
        })
      }
    },
    data: function () {
      return {
        userName: null,
        password: null,
        alertText:"",
      }
    }
  }
</script>
<style>
  .container-wrap {
    width: 480px;
    overflow: hidden;
    margin-top: 10%;
    padding-top: 15px;
    padding-bottom: 15px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }

  .alert {
    margin-top: 20px;
  }
</style>
