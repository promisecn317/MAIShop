<template>

  <div class="register-container">

    <section>
      <el-form ref="ruleForm" :model="ruleForm" :rules="rules" label-width="100px" class="ms-content" autocomplete="off"
               :hide-required-asterisk="true" size="medium">
        <div class="ms-register">
          <h3 class="ms-title">REGISTATION</h3>
        </div>
        <div style="padding-top: 10px">
          <el-form-item prop="email">
            <el-col :span="10">
              <el-input ref="email" v-model="ruleForm.email" placeholder="输入邮箱并点击获取验证码" name="username" type="text"
                        tabindex="1" auto-complete="on">
                <i slot="prefix" class="el-input__icon el-icon-user-solid"></i></el-input>
            </el-col>
            <el-button :loading="codeLoading" :disabled="isDisable" type="info" size="mini"
                       style="background-color: #25c4b0" @click="sendMsg()">Get Code
            </el-button>

            <span class="status">{{ statusMsg }}</span>
          </el-form-item>

          <el-form-item prop="code">
            <el-col :span="10">
              <el-input v-model="ruleForm.code" maxlength="6" placeholder="请登录邮箱接收验证码"/>
            </el-col>
          </el-form-item>

          <el-form-item prop="pwd">
            <el-col :span="10">
              <el-input :key="passwordType" ref="password" v-model="ruleForm.password" :type="passwordType"
                        placeholder="请输入密码" name="password"
                        tabindex="2" auto-complete="on">
                <i slot="prefix" class="el-input__icon el-icon-lock"></i>
              </el-input>
            </el-col>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" style="width: 42%; background-color: #25c4b0" @click="register()">Registration
            </el-button>
          </el-form-item>
        </div>

        <p class="tips">Have account?
          <el-button type="info" size="mini" style="background-color: #25c4b0" @click="goTo()">Log in</el-button>
        </p>
      </el-form>
    </section>

    <div class="error">{{ error }}</div>
  </div>

</template>

<script>
// import {getEmailCode, register} from '../api/register';


export default {
  name: 'Register',
  data() {
    return {
      statusMsg: '',
      error: '',
      isDisable: false,
      codeLoading: false,
      passwordType: 'password',
      redirect: undefined,
      ruleForm: {
        email: '',
        code: '',
        password: '',
      },
      rules: {
        email: [{
          required: true,
          type: 'email',
          message: 'Input Email box',
          trigger: 'blur'
        }, {pattern: /^[a-zA-Z0-9_.-]{2,30}@(student.xjtlu.edu.cn)$/, message: 'Please use XITLU campus email'}],
        code: [{
          required: true,
          type: 'string',
          message: 'Input verification code',
          trigger: 'blur'
        }, {pattern: /^\d{6}$/, message: 'Must be a 6-digit integer'}],
        password: [{
          required: true,
          message: 'Input password',
          trigger: 'blur'
        }, {pattern: /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,16}$/, message: 'Please contain both numbers and letters and be 6-16 characters in length'},]
      }
    }
  },

  methods: {
    goTo() {
      this.$router.push('/login');
    },
    sendMsg() {
      const self = this
      let email=''
      email=this.ruleForm.email
      let emailPass
      let timerid
      console.log(timerid)
      if (timerid) {
        return false
      }
      self.statusMsg = ''
      this.$refs['ruleForm'].validateField('email', (valid) => {
        emailPass = valid
      })
      // 向后端API验证码发送
      if (!emailPass) {
        self.codeLoading = true
        self.statusMsg = '验证码发送中...'
        //修改post的api接口路径
        // this.$https.post('http://localhost:8080/api/getVerifyEmail',{email:self.ruleForm.email}).then(res=>{
        // Json字符串形式请求
        this.$https.post('/user/verify_email',{email:this.ruleForm.email}).then(res => {
          if (res.status === 200) {
            this.$message({
              showClose: true,
              message: 'Verification code sent successfully!',
              type: 'success'
            })
            let count = 60
            self.ruleForm.code = ''
            self.codeLoading = false
            self.isDisable = true
            self.statusMsg = `验证码已发送,${count--}秒后重新发送`
            //使用延迟函数，显示剩余秒数
            timerid = window.setInterval(function () {
              self.statusMsg = `验证码已发送,${count--}秒后重新发送`
              if (count <= 0) {
                console.log('clear' + timerid)
                window.clearInterval(timerid)
                self.isDisable = false
                self.statusMsg = ''
              }
            }, 1000)
          } else if(res.status === 409){
            this.$message({
              showClose: true,
              message: 'Already registered, log in!',
              type: 'error'
            })
          }
        }).catch(err => {
          this.isDisable = false
          this.statusMsg = ''
          this.codeLoading = false
          console.log(err)
        })
      }
    },


    // 用户注册
    register() {
      let code = ''
      code = this.ruleForm.code
      const user = {
        email: this.ruleForm.email,
        // code: this.ruleForm.code,
        password: this.ruleForm.password
      }
      this.$https.post(`/user/userRegister/${code}`, user).then(res => {
        if (res.status === 200) {
          this.$message({
            showClose: true,
            message: 'Registration succeeded, skipping to login page...',
            type: 'success'
          })
          setTimeout(() => {
            this.$router.push('/login')
          }, 2000)
        } else if(res.status === 400){
          this.$message({
            showClose: true,
            message: 'Failed. Please try again.',
            type: 'error'
          })
        }
      })
    }
  }


}
</script>


<style scoped>
.register-container {
  position: relative;
  width: 100%;
  height: 110vh;
  background-size: cover;
  background-repeat: no-repeat;
  background-image: url(../assets/new.jpg);
}

.ms-register {
  line-height: 30px;
  text-align: center;
  font-size: 25px;
  color: #fff;
  border-bottom: 1px solid #ddd;
  border-radius: 6px;
  padding: 5px 15px 15px 5px;
}

.ms-content {
  background: rgba(255, 255, 255, 0.3);
  padding: 10px 10px;
  position: absolute;
  left: 320px;
  top: 150px;
  width: 650px;
  height: 400px;
}

.tips {
  float: right;
  font-size: 15px;
  color: #fff;
  margin-bottom: 10px;
  position: relative;
  left: -250px;
}

</style>
