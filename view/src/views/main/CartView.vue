<template>
  <div>
    <el-container style="height:100vh">
     

        <!--        购物车部分实现-->
        <el-main>
          <el-header>
            My Shopping Cart<i class="el-icon-shopping-cart-full"></i>
          </el-header>
          <!-- table -->
          <div class="table">
            <el-table element-loading-text="正在为您拼命加载中..." :data="tableData" ref="multipleTable" style="width: 100%"
                      @selection-change="handleSelectionChange" :close-on-click-modal="false" :close-on-press-escape="false"
                      :header-cell-style="{background:'#f8f8f8',color:'#999'}">
              <el-table-column type="selection" width="75" align="center" />
              <el-table-column prop="shopImg" align="center" width="150" label="Products">
                <template slot-scope="scope">
                  <img :src="scope.row.shopImg" class="shopImg" alt="">
                </template>
              </el-table-column>
              <el-table-column prop="shop" align="center">
                <template slot-scope="scope">
                  <span class="shop">{{scope.row.name}}</span>
                </template>
              </el-table-column>
              <el-table-column prop="price" label="Price" align="center">
                <template slot-scope="scope">
                  <span class="price">¥{{scope.row.price}}</span>
                </template>
              </el-table-column>
              <el-table-column label="Operation" align="center">
                <template slot-scope="scope">
                  <i class="el-icon-delete" style="font-size:18px;cursor: pointer;" @click="handleDelete(scope.$index, scope.row)"></i>
                </template>
              </el-table-column>
            </el-table>
          </div>

          <el-footer>
            <el-button class="el-icon-s-goods" style="background-color:#FEF189;float: right" @click="handleSubmit()">Submit</el-button>
          </el-footer>
        </el-main>
      </el-container>
    
  </div>
</template>

<script>

export default {
  name: "scart",
  data() {
    return {
      imgUrl: require("../../assets/logo.jpg"),
      tableData:[],
      multipleSelection: [],
    }
  },
  created() {
    this.cartList()
  },

  methods:{
    cartList(){
         let userId = localStorage.getItem("userId")
        let that=this;
         this.$https.get('http://localhost:8088/show',{
              params:{
                userId: Number(userId)
              }
          }).then(res=>{
            var result=res.data;
            console.log(result)
            that.tableData=result;
          })
     },
    // 删除选中单个商品
    handleDelete(index, row) {
      this.$confirm('确定删除该商品，不再看看吗？', '提示', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'No',
        type: 'warning'
      }).then(() => {
        this.$https.post('http://192.168.10.105/cart/delete',{userId:this.userId,productId:this.productId}).then(res=>{
          this.tableData.splice(index, 1);
          this.$message({
            showClose:'false',
            duration:3000,
            type: 'success',
            message: 'Delete succeeded!'
          });
          this.tableData=this.tableData
        })
        //删除数组中指定的元素
        // this.tableData.splice(index, 1);
        // this.$message({
        //   type: 'success',
        //   message: '删除成功啦!'
        // });
        // this.tableData=this.tableData
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Delete cancel！'
        });
      });
    },
//选择和全选功能
    handleSelectionChange(selection) {
      this.multipleSelection = selection;
    },
    //提交购物车功能
    handleSubmit(){
      this.$https.post('/api/cart_index/submit',{userId:this.userId,productId:this.productId}).then(res=>{
        if(res.data.code===200){
          this.$message({
            showClose:'true',
            type: 'success',
            message: 'The goods have been submitted successfully. Please contact the seller~'
          })
        }else{
          this.$message({
            showClose: 'true',
            type: 'error',
            message: 'The good has been sold, submission failed'
          })
        }
      })
    }
  }



  }

</script>

<style lang="scss" scoped>
.el-main{
  width:100%;
}
.el-header{
  background-color: #07D89E;
  color: #FEF189;
  text-align: center;
  font-size: 23px;
  line-height: 60px;
}
.el-footer{
  background-color:  #07D89E;
  line-height: 60px;
}
.table {
  margin-top: 50px;
  position: relative;
  padding-bottom: 100px;

  .price,
  .shop {
    color: #000733;
  }
  .shopImg{
    border: 1px solid #DDDDDD;
    float: left;
    height: 100px;
    width: 100px;
    margin: 0px 20px 0px 0px;
  
  }
}

</style>

