<template>
  <div>
    <el-row>
      <el-card :body-style="{ padding: '0px' }">
        <el-container>
          <el-aside width="200px">
            <img :src="pic" alt="" class="image" />
          </el-aside>
          <el-main>
            <div style="padding: 20px;">
              <span style="font-size: 20px ;margin-top:5px;line-height: 50px">{{title }}</span>
              <span style="font-size: 17px ;margin-top:5px;line-height: 50px">{{description }}</span>
              <div class="bottom clearfix">
                <span class="goods-price" style="margin-top:20px"
                >¥{{ price }}</span
                >
                <el-button
                  type="primary"
                  class="button"
                  style="background-color:#07D89E;
                         border: none;
                         height:45px;
                         margin-left:50px;
                         width:100px;"
                >Details</el-button
                >
                <el-button
                  type="primary"
                  class="button"
                  style="background-color:#07D89E;
                         border: none;
                         height:45px;
                         width:100px"
                  @click="goEditPage()"
                >Edit</el-button
                >
                <i
                  class="el-icon-delete"
                  style="font-size: 30px;margin-left:400px"
                  @click="delPro(id)"
                ></i>
              </div>
            </div>
          </el-main>
        </el-container>
      </el-card>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    title: {
      default: "",
      type: String
    },
    price: {
      default: 0,
      type: Number
    },
    pic: {
      default: "",
      type: String
    },
    description: {
      default: "",
      type: String
    },
    // 删除列表商品的函数
    deleteGoods: {
      type: Function,
      default: null
    },
    id: {
      default: ""
    }
  },
  data() {
    return {};
  },
  methods: {
    goEditPage() {
      // 跳转到editPro.vue商品详情页面,name为editPro.vue页面路由配置里的的name属性
      this.$router.push({
        name: "edit",
        query: { productId: this.productId }
      });
    },
    //删除商品
    delPro(id) {
      // 如果接口ok了，就把这个放在下面接口里面
      // this.deleteGoods(id);

        this.$https
          .post("/api/my_mai/itemlist/delete", {
            userId: this.userId,
            productId: this.productId
          })
          .then(res => {
            this.deleteGoods(id);
            this.$message({
              showClose: "false",
              duration: 3000,
              type: "success",
              message: "Deleted successfully!"
            });
          });
    }
  }
};
</script>

<style>
.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}
.button:hover {
  color: #96fbc4;
}

.image {
  width: 90%;
  display: block;
  margin-left: 10px;
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
