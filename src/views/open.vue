<template>
  <div id="app">
    <el-container style="height:100vh">
      <el-aside width="600px" style="background-color: #07D89E">
        <div class="title">
          <img :src="defaultImg2" alt=" "
               style="width:460px;margin-top:50px;margin-left:50px"/>
        </div>
        <div class="slogan">
          <img :src="defaultImg1" alt=" " style="width:280px;margin-left:160px;margin-top: 30px"/>
        </div>
        <el-button class="btn" @click="goTo()" style="background-color:#FEF189;
                           border-color: #333333;
                           height:50px;
                           width:250px;
                           font-size: 25px;
                           border-radius: 25px;
                           margin-left: 150px;
                           margin-top:70px">Get Start!</el-button>
      </el-aside>
      <el-main style="background-color: #07D89E">
        <img :src="defaultImg" alt=" " style="width:520px;margin-top: 5px"/>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import deImg  from '../assets/ov.jpg'
import deImg1  from '../assets/slogan.jpg'
import deImg2  from '../assets/title2.jpg'
export default {
  name: "App",
  data () {
    return {
      defaultImg:deImg,
      defaultImg1:deImg1,
      defaultImg2:deImg2
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
