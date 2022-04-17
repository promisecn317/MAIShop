<template>

  <div class="login-container">

    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="ms-content" auto-complete="on" label-width="0px">
      <div class="ms-login">
        <h3 class="ms-title">LOG IN</h3></div>

      <el-form-item prop="username">
        <el-input ref="username" v-model="loginForm.username" placeholder="Email" name="username" type="text"
                  tabindex="1" auto-complete="on">
          <i slot="prefix" class="el-input__icon el-icon-user-solid"></i>
        </el-input>
      </el-form-item>

      <el-form-item prop="password">
        <el-input :key="passwordType" ref="password" v-model="loginForm.password" :type="passwordType" placeholder="Password"
                  name="password" tabindex="2" auto-complete="on" @keyup.enter.native="handleLogin" show-password>
          <i slot="prefix" class="el-input__icon el-icon-lock"></i>
        </el-input>
      </el-form-item>


      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;background-color: #25c4b0"
                 @click.native.prevent="handleLogin">Log in</el-button>

      <p class="tips">Have no account?
          <el-button type="info" size="mini" style="background-color: #25c4b0" @click="goTo()">Register</el-button>
      </p>
    </el-form>
  </div>

</template>

<script>
import {login} from '../api/login';

export default {

  data() {
    return {
      loginForm: {
        username: '',
        password: '',
      },
      loginRules: {
        username: [{type: 'email', required: true, trigger: 'blur', message: '请输入邮箱'},
          {pattern:/^[a-zA-Z0-9_.-]{2,30}@(student.xjtlu.edu.cn)$/, message:'请使用西浦校园邮箱'}
        ],
        password: [{
          required: true,
          message: '创建密码',
          trigger: 'blur'
        }, {pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/, message: '密码必须同时包含数字与字母,且长度为 6-16位'}]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    goTo() {
      this.$router.push('/register');
    },


    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          const loginFormData=this.loginForm;
          login(loginFormData)
            .then((res) => {
              if (res.msg) {
                this.loading = false;
                this.$router.push('/home');
              } else {
                this.$message.error(res.msg);
                this.loading = false;
              }
            })
            .catch((e) => {
              console.log(e);
              this.loading = false;
            });
        }
      });
    }
  }
}
</script>

<style scoped>

.login-container {
  position: relative;
  width: 100%;
  height: 110vh;
  background-size: cover;
  background-repeat: no-repeat;
  background-image: url(../assets/new.jpg);
}

.ms-title {
  line-height: 30px;
  text-align: center;
  font-size: 25px;
  color: #fff;
  border-bottom: 1px solid #ddd;
  border-radius: 6px;
  padding: 5px 25px 25px 5px;
}

.ms-title .el-input input {
  height: 38px;
}


.ms-content {
  padding: 30px 30px;
  background: rgba(255, 255, 255, 0.3);
  width:300px;
  height:350px;
  border-radius: 5px;
  position:absolute;
  left:450px;
  top:160px;
}

.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}

.tips {
  float: right;
  font-size: 15px;
  color: #fff;
  margin-bottom: 10px;
}
</style>
<!--<style>-->
<!--.ms-login .el-input__inner {-->
<!--  height: 40px !important;-->
<!--}-->
<!--</style>-->
