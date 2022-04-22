<template>
  <div id="app">
    <el-container style="height:100vh">
     
  
        <el-main>
          <el-page-header @back="goBack" title="Back"></el-page-header>
          <div>
            <Goods
              v-for="item in list"
              :key="item.productId"
              :title="item.productName"
              :price="item.productPrice"
              :description="item.productDescription"
              :pic="item.productImage"
              :deleteGoods="deleteGoods"
              :id="item.productId"
            >
              <!--动态绑定，根据后端list参数来-->
            </Goods>
            <el-empty
              v-show="showEmpty"
              description="You haven't post any product yet!"
            >
            </el-empty>
          </div>
        </el-main>
      </el-container>
  
  </div>
</template>

<script>
import Goods from "@/components/Goods/Goods.vue";
//import deImg from "../assets/logo.jpg";
export default {
  name: "MyView",
  components: {
    Goods
  },
  data() {
    return {
      list: [],
      //defaultImg: deImg,
      showEmpty: true //设置该图片显示还是隐藏
    };
  },
  created() {
   this.initProductlist();
  },
  methods: {
    deleteGoods(id) {
      this.list = this.list.filter(item => {
        console.log("item", item, id);

        return item.productId !== id;
      });
    },
    openNP() {},
    async initProductlist() {
      // 这里是写的假数据
      //this.list = [
        //{ title: "", productId: "", price: "" },
        //{ title: "", productId: "", price: "" },
        //{ title: "", productId: "", price: "" }
      //];
      const { data: res } = await this.$axios.get("/api/my_mai/itemlist");
      if (res.status === 200) {
        this.list = res.list;
        console.log(this.list)
      }
    },
    goBack() {
      console.log("go back");
    }
  },
  watch: {
    list(newVal, oldVal) {
      //监测list数值数据变化，一旦发生改变就会调用该函数
      if (this.list.length > 0) {
        //判断数组不为空就隐藏图片
        this.showEmpty = false;
      } else {
        //为空就显示图片
        this.showEmpty = true;
      }
    }
  }
};
</script>

<style scoped></style>
