<template>
  <div class="editPro">
    <el-container>
      <el-container>
        <el-header></el-header>
        <el-main style="margin:0 auto;">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class=" el-icon-error" @click="close()"></i>
              <h1
                style=" color: #07D89E;
              text-align: center;
              font-size: 40px ;
              margin-top: -25px;margin-bottom:-10px "
              >
                "edit麦"
              </h1>
            </div>
            <!--            主体部分-->
            <div class="txt item">
              <el-form ref="form" :model="form" :rules="rules">
                <el-form-item label="">
                  <el-input
                    v-model="form.title"
                    placeholder="Title"
                    maxlength="10"
                    prop="mtitle"
                    style="width:365px"
                  >{{ form.title }}</el-input
                  >
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    v-model="form.price"
                    placeholder="Price"
                    maxlength="10"
                    prop="mprice"
                    style="width:365px"
                  >{{ form.price }}</el-input
                  >
                </el-form-item>
                <el-form-item label="">
                  <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4 }"
                    v-model="form.desc"
                    prop="mdesc"
                    placeholder="Describe your product"
                    maxlength="50"
                    style="width:365px"
                  >{{ form.desc }}</el-input
                  >
                </el-form-item>
                <el-form-item
                ><!--action 如果是手动上传 action随便填一下就好了，自动上传填接口地址就行-->
                  <el-upload
                    class="upload-demo"
                    action="#"
                    ref="upload"
                    list-type="picture-card"
                    :on-preview="handlePictureCardPreview"
                    :before-upload="beforeUpload"
                    :on-remove="handleRemove"
                    :limit="2"
                    accept=".png, .jpg"
                    :auto-upload="false"
                    :multiple="true"
                  >
                    <i class="el-icon-upload"></i>
                    <div class="el-upload__text"><em>click to modify</em></div>
                    <div class="el-upload__tip" slot="tip">
                      Just receive jpg/png，and less than 500kb
                    </div>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="" />
                  </el-dialog>
                </el-form-item>
                <el-form-item>
                  <el-button
                    type="primary"
                    @click.native="onSubmit"
                    class="cbt"
                    style="background-color:#07D89E;
                           border: none;
                           height:45px;
                           width:300px;
                           margin-top:30px;
                           font-size:20px"
                  >Save now!</el-button
                  >
                </el-form-item>
              </el-form>
            </div>
          </el-card>
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      form: {
        title: "",
        price: "",
        desc: ""
      },
      rules: {
        title: [
          {
            required: true,
            message: "The title cannot be empty",
            trigger: "blur"
          }
        ],
        price: [
          {
            required: true,
            message: "The price cannot be empty",
            trigger: "blur"
          }
        ],
        desc: [
          {
            required: true,
            message: "The description cannot be empty",
            trigger: "blur"
          }
        ]
      },
      param: "",
      fileList: [],
      proInfo: [],
      productId: ""
    };
  },
  created() {
    // 接收goods.vue传过来的proId
    this.proId = this.$route.query.proId;
    // console.log(this.proId)
    //调用请求数据的方法
    this.getProInfo();
  },

  methods: {
    //获取商品信息
    getProInfo() {
      let that = this;
      this.form = { title: "", price: "", desc: "" };
      this.$https.get(`/api/my_mai/itemlist/edit?productId=+ ${productId}`)
        .then(res => {
          //根据proId获取对应的商品详情信息
          that.proInfo = res.data.data;
        })
        .catch(error => {

        });
    },
    //保存修改信息
    onSubmit() {
      let _this = this;
      this.$refs.upload.submit();

      this.param.append("productName", this.form.title);
      this.param.append("productPrice", this.form.price);
      this.param.append("productDescription", this.form.desc);
      // console.log(this.param);
      this.$https
        .post("/api/my_mai/itemlist/edit/save", {
          userId: this.userId,
          productId: this.productId,
          proInfo: this.form
        })
        .then(res => {
          this.$message({
            showClose: true,
            message: " Modified successfully！",
            type: "success"
          });
          setTimeout(() => {
            this.$router.push("/productlist"); //edit成功后自动跳转到myproduct页面
          }, 2000);
        });
    },
    close() {
      this.$confirm(
        "Unsaved content detected. Do you want to continue editing?",
        "Confirmation ",
        {
          distinguishCancelAndClose: true,
          confirmButtonText: "Continue",
          cancelButtonText: "Discard changes"
        }
      )
        .then(() => {
          this.$message({
            type: "info",
            message: "Stay on the current page"
          });
        })
        .catch(action => {
          this.$message({
            type: "info",
            message:
              action === "cancel"
                ? "Discard changes and leave"
                : "Stay on the current page"
          });
        });
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    beforeUpload(file) {
      this.param = new FormData();
      this.fileList.push(file);
      let images = [...this.fileList];
      console.log("images", images);

      images.forEach((img, index) => {
        this.param.append(`productImage_${index}`, img); // 把单个图片重命名，存储起来（给后台）
      });
      // 图片上传需要为true
      return true;
    }
  }
};
</script>
<style lang="scss">
.editPro {
  background-color: #07d89e;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
  text-align: center;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  width: 550px;
  height: 650px;
  background: rgba(255, 255, 255, 0.4);
  border: none;
}

.cbt:hover {
  background-color: #96fbc4;
  color: #96fbc4;
}

.el-icon-error {
  margin-left: 450px;
  font-size: 35px;
}

.el-icon-error:hover {
  color: #6e6e6e;
}

.el-icon-delete {
  margin-right: 450px;
  font-size: 20px;
}
</style>
