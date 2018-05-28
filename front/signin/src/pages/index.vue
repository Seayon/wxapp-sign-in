<template>
  <div class="container">
    <h2>羽田签到用户管理</h2>
    <div class="table-responsive">
      <table class="table table-hover">
        <tr>
          <th>学号</th>
          <th>姓名</th>
          <th>性别</th>
          <th>班级编号</th>
          <th>类型</th>
          <th>ID</th>
          <th>绑定OpenID</th>
          <th>操作</th>
        </tr>
        <tr class="success" v-for="(item,index) in items" v-bind:key="index">
          <td>{{item.sID}}</td>
          <td>{{item.name}}</td>
          <td>{{item.sex}}</td>
          <td>{{item.clazzNo}}</td>
          <td>{{item.type == 2 ? '学生用户' : '普通用户'}}</td>
          <td>{{item.id}}</td>
          <td>{{item.openId}}</td>
          <td class="deleteIcon">
            <i class="iconfont icon-shanchu" v-on:click="doDeleteUser(item.id)"></i>
          </td>
        </tr>
      </table>
    </div>
    <input type="file" name="file" @change="getFile($event)"/>
    <button type="button" v-on:click="doPostFile" class="btn btn-default">上传</button>
    <div class="alert alert-success" role="alert" v-show="resultText!=null&&resultText.length>0">{{resultText}}</div>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    methods: {
      getFile(event) {
        this.file = event.target.files[0];
        console.log(this.file);
      },
      doPostFile: function () {
        let filePattern = new RegExp('(.xls|.xlsx)$');
        if (this.file == null || !filePattern.test(this.file.name)) {
          alert("只能上传Excel文件！")
          return;
        }
        let formdata = new FormData();
        let that = this;
        formdata.append('file', this.file);
        axios({
          url: 'admin/fileUpload',
          method: 'POST',
          header: {
            'Content-Type': 'multipart/form-data'
          },
          data: formdata,
        }).then(res => {
          if (res.data.code == 0) {
            this.loadUser();
            that.resultText = res.data.msg;
          } else {
            that.resultText = res.data.msg;
          }
        }).catch(res => {
          that.resultText = "服务器出错"
        })
      },
      doDeleteUser: function (Id) {
        let c = confirm("确认删除？");
        if (c) {
          let that = this;
          axios({
            url: 'admin/user?Id=' + Id,
            method: 'DELETE',
          }).then(res => {
            if (res.data.code == 0) {
              this.loadUser();
            } else {
              that.resultText = res.data.msg;
            }
          }).catch({})
        }
      }
      ,
      loadUser: function () {
        let that = this;
        axios({
          url: 'admin/user?page=' + that.page,
          method: 'GET',
        }).then(res => {
          if (res.data.code == null) {
            that.items = res.data;
          } else {
            that.resultText = res.data.msg;
          }
        }).catch(res => {
          that.resultText = res.data.msg;
        })
      }
    }
    ,
    data: function () {
      return {
        file: null,
        page: 1,
        resultText: "",
        items: [
          {
            id: null,
            name: null,
            sex: null,
            openId: null,
            type: null,
            clazzNo: null,
            sID: null,
          }
        ]
      }
    }
    ,
    created: function () {
      if (this.$route.params.page) {
        this.page = this.$route.params.page;
      }
      this.loadUser();
    }
  }
</script>
<style scoped>
  .container {
    margin-top: 20px;
    margin-bottom: 20px;
  }
  .alert-success{
    margin-top: 20px;
  }
  .icon-shanchu:hover{
    color: red;
  }
</style>
