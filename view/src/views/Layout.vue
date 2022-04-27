<template>
<div>
<el-container class="home-container">
  <el-header height="100px">
      <div>
           <img src="../assets/logo.jpg" width="200px" height="180px" alt=""> 
          </div>
       <router-link :to="{name:'open'}"><el-button type="primary" size="mini">LOG OUT</el-button>
 </router-link>   
      <router-link :to="{name:'Newproduct'}"><el-button type="primary" icon="el-icon-circle-plus" ></el-button>
 </router-link>
  </el-header>
  <el-container>
    <el-aside width="200px" height="100px">
            <el-menu
            router
                :default-active="this.$router.path"
   
      class="el-menu-vertical-demo"
      background-color="#5fdeb8"
      height="100px"
      text-color="#fff"
      active-text-color="#ffd04b">
       
           <template slot="title" >
          <i class="el-icon-s-home"></i>
            <span slot="title">麦home</span>
           </template>
           
      <el-menu-item index="/">
        <i class="el-icon-price-tag"></i>
        <span slot="title">麦home</span>
      </el-menu-item>
       <el-menu-item index="/Cart">
        <i class="el-icon-shopping-cart-full"></i>
       <span slot="title">麦cart</span>
 
      </el-menu-item>
      <el-menu-item index="/Chat" >
        <i class="el-icon-chat-dot-round"></i>
        <span slot="title">麦chat</span>
      </el-menu-item>
      <el-menu-item index="/MyView">
        <i class="el-icon-user"></i>
      <span slot="title">麦my</span>
       
 
      </el-menu-item>
           
    </el-menu>
    </el-aside>
    <el-main>
     
     <router-view/>
    </el-main>
  </el-container>
</el-container>



</div>
 
</template>
   

<script>
	export default {
		mounted(){
			// this.$axios.get('/goods.json').then(res=>{
			// 	console.log(res.data);
			// })
		}
    
	}
</script>




<style lang="scss" scoped>
.search-input {
   border: 1px solid #0ba95d;
   border-radius: 5px;
   margin-right: 10px;
  
}
    .home-container{
        //position:absolute;
        height:100vh;
    }
    .el-header {
      //  margin:-8px;
        background-color: #07D89E;
        color: #fff;
       height:120px;
        padding-left:0;
        align-items:center;
       display: flex;
      justify-content: space-between;
    } 
    .el-aside {
  
    background-color: #07D89E;
    color: rgb(8, 139, 72);
    text-align: center;
    line-height:200px;
   .el-menu-item {
     padding: 0!important;
    }
    
  }
  
  .el-main {
    background-color: #E9EEF3;
    // margin:-8px;
    color: #333;
    text-align: center;
    // line-height:200px;

  } 
  .el-button{
    float: certer;
    color: #0d591d;
    background-color: #FEF189;
    
  }
  .el-input{
    width: 500px;
    height: 20px;
   
  
  }
  
 
  
  .bottom {
    // margin-top: -130px;
    line-height: 10px;
    padding: 0;
    display: flex;
    align-items: center;
  }
  .search-input{
    width: 600px;
  }

.info-btn {
    width: 30px;
}
.info-bottom {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
  .image {
    width: 100%;
    display: block;
  }

  .clearfix:before,
  .clearfix:after {
      display: table;
      content: "";
  }
  
  .clearfix:after {
      clear: both;
  }
  
  </style>
 

