<template>
   <div style="background-color:#FCFCFC;font-family:'宋体';height:400px;">
        <div>
            <my-bread level1='product list'></my-bread>
        </div>
        <div style="margin-top:5px;margin-left:300px;">
            <el-row :gutter="15">
                <el-col :span="2">
                     <div style="background-color:#FFEBCD;width:100px;height:100px;display:inline-block;border-radius:60%;overflow:hidden;">
                     <img src="" class="image">
                     </div>    
                    <div style="margin-top:20px;margin-left:15px;font-size:18px;"><span>maimai </span></div>
                    <div style="margin-top:20px;margin-left:15px;font-size:18px;"><span>maimai19@student.xjtlu.edu.cn </span></div>
                      <el-button type="product list" @click="productList()" round style="font-size:18px;color:#fff;background-color: #FEF189;margin-top:50px;margin-left:1px;">MY PRODUCT</el-button>
                       </el-col>
                <el-col :span="21">
                    <el-row :gutter="10" style="margin-top:30px;">
                        </el-row>
                </el-col>
            </el-row>
        </div>     
  </div>
</template>

<script>

export default {
 methods: {
     productList() {
         this.$router.push({path:'/productlist'})
     }
 }
}
</script>
<style>
  .el-button{
    float: certer;
    color: #0d591d;
    background-color: #FEF189;
    
  }
    .name{ text-align: center; }
    .value{ text-align: left; }
</style>