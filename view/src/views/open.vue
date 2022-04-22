<template>
  <div id="app">
    <el-container style="height:100vh">
      <div style="background-color: #07D89E">
        <div class="title">
          <img :src="defaultImg2" alt=" "
               style="width:460px;margin-top:10px;margin-left:50px"/>
        </div>
        <div class="slogan" style="margin-top:-170px;">
          <img :src="defaultImg1" alt=" " style="width:280px;margin-left:160px;margin-top: 10"/>
        </div>
        <el-button class="btn" @click="goTo()" style="background-color:#FEF189;
                           border-color: #333333;
                           height:50px;
                           width:250px;
                           font-size: 25px;
                           border-radius: 25px;
                           margin-left: 150px;
                           margin-top:10px">Get Start!</el-button>
      </div>
      <div style="background-color: #07D89E">
        <img :src="defaultImg" alt=" " style="width:520px;margin-top: 5px"/>
      </div>
    </el-container>
  </div>
</template>

<script>
// import deImg  from '../assets/ov.jpg'
// import deImg1  from '../assets/slogan.jpg'
// import deImg2  from '../assets/logo.jpg'
export default {
  name: "App",
  data () {
    return {
      defaultImg:require('../assets/ov.jpg'),
      defaultImg1:require('../assets/slogan.jpg'),
      defaultImg2:require('../assets/logo.jpg')
    }
  },
  methods: {
    goTo(){
      this.$router.push('/login')
    }
  }
}
</script>
<style scoped>
#app {
  background-color: #07D89E ;
}
.btn:hover{
  color: darkkhaki;
}
</style>
