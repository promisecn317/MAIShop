<template>
<div>
<div style="display:flex; margin-bottom: 20px;">
        <input type="text" class="search-input"  />
        <!-- <el-input  placeholder="please input"></el-input> -->
        <el-button type="primary" icon="el-icon-search" height="50px">search</el-button>
 </div>
  <div class="goods-box">
    <div class="goods-item" :span="6"  v-for="item in list" :key="item.productId"  >
      <el-card :body-style="{ padding: '1px' }">
        <img :src="item.productImg" class="image">
        <div style="padding: 0px;">
          <span style="display: block;">{{item.productName}}</span>
          <span style="display: block;">{{item.productPrice}}</span>
          <div class="info-bottom">
            <el-button type="text">Details</el-button>
            <div class="bottom">
              <el-button class="info-btn" type="text"  icon="el-icon-circle-plus" @click="addCart(item)"></el-button>
              <el-button class="info-btn" type="text"  icon="el-icon-more"></el-button>
            </div>
          </div>
        </div>
      </el-card>
    </div>
  </div>
</div>
 
</template>
   

<script>
export default {
   data() {
    return {
      list: [],
      // 左侧菜单数据
      menulist: [],
      // 是否折叠
      isCollapse: false,
      // 被激活的链接地址
      activePath: '',
      goodData:[]
    }
  },
  created () {
    this.goodList()
    // this.$http.post('/api/xxxxxx',{ a: 1  }).then(res => {

    // }).catch(err => {

    // })
  },
  methods:{
    data() {
    return {
      showEmpty: true
    };
  },
   goodList(){
     this.$https.get('http://localhost:8088/product_shelve',{
        params:{
          product_id:this.$route.query.product_id
        }
      }).then(res=>{
        var result=res.data;
        console.log(res.data,'============')
          this.list=res.data;
      })
   },
    addCart(item) {
      let userId = localStorage.getItem("userId")
      this.$https.get('/add',{
        params: {
          productId: item.productId,
          userId: Number(userId)
        }
      }).then(res=>{
        var result=res.data;
        // this.$router.push('/cart')
        if(result.code==0){
          this.productArr=result.data;
        }
      })
   }
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
        position:relative;
        height:100%;
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
    float: left;
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
    width: 650px;
    margin-left:80px;
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

  .goods-box {
    display: flex;
    flex-wrap: wrap;
    
  }
  .goods-box .goods-item {
      width: 31%;
      margin-right: 2%;
      margin-bottom: 15px;
   }
  goods-box .goods-item:nth-child(3n) {
     margin-right: 0;
  }
  </style>
 
    
