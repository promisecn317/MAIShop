<template>
  <div id="app">
    <el-container>
      <!-- <el-aside width="400px"></el-aside> -->
      <el-container>
        <el-header ></el-header>
        <el-main style="margin:0 auto;">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class=" el-icon-error" @click="open"></i>
              <h1
                style=" color: #07D89E;
              text-align: center;
              font-size: 40px ;
              margin-top: -25px;margin-bottom:-10px ">"new麦"</h1>
            </div>
            <div  class="txt item">
              <el-form ref="form" :model="form" :rules="rules">
                <el-form-item label="" prop="title">
                  <el-input v-model="form.title" placeholder="Title (≤ 10 digits)" maxlength="10" style="width:365px"></el-input>
                </el-form-item>
                <el-form-item label="" prop="price">
                  <el-input v-model="form.price" placeholder="Price (≤ 8 digits, accept decimal points to the last two)" maxlength="6" style="width:365px"></el-input>
                </el-form-item>
                <el-form-item label="" prop="desc">
                  <el-input type="textarea"  :autosize="{ minRows: 2, maxRows: 4}" v-model="form.desc" placeholder="Describe your product (≤ 50 digits)" maxlength="50"
                            style="width:365px"></el-input>
                </el-form-item >
                <el-form-item ><!--action 如果是手动上传 action随便填一下就好了，自动上传填接口地址就行-->
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
                    :multiple="true">
                    <i class="el-icon-upload" ></i>
                    <div class="el-upload__text"><em>click to upload</em></div>
                    <div class="el-upload__tip" slot="tip">Just receive jpg/png，and less than 500kb</div>
                  </el-upload>
                  <el-dialog :visible.sync="dialogVisible">
                    <img width="100%" :src="dialogImageUrl" alt="" />
                  </el-dialog>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="onSubmit"
                             class="cbt"
                             style="background-color:#07D89E;
                           border: none;
                           height:45px;
                           width:300px;
                           margin-top:30px"
                  >Post now!</el-button>
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
  // name: 'App',
  data() {
    return {
      dialogImageUrl: "",
      dialogVisible: false,
      form: {
        title: "",
        price: "",
        desc: "",
      },
      rules: {
        title: [{required: true, message: 'The title cannot be empty', trigger: 'blur'}
        ],
        price: [{required: true, message: 'The price cannot be empty', trigger: 'blur'}
        ],
        desc: [{required: true, message: 'The description cannot be empty', trigger: 'blur'}
        ],
      },
      param: "",
      fileList: [],
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
      let formData = new FormData()
      formData.append("productName", this.form.title);
      formData.append("productPrice", this.form.price);
      formData.append("productDescription", this.form.desc);
      console.log(this.param);
      this.$axios.post("http://localhost:8088/productInformation", {product:formData}).then(res => {
        this.$message({
          showClose: true,
          message: " post successfully！",
          type: "success"
        });
        setTimeout(() => {
          this.$router.push("/home"); //edit成功后自动跳转到home页面
        }, 2000);
      });
    },
    open() {
      this.$confirm('Unsaved content detected, Do you want to continue editing?', 'Confirmation ', {
       
       distinguishCancelAndClose: true,
        confirmButtonText: 'Continue',
        cancelButtonText: 'Discard changes'
      })
        .then(() => {
          this.$message({
            type: 'info',
            message: 'Stay on the current page'
          });
        })
        .catch(action => {
          this.$message({
            type: 'info',
            message: action === 'cancel'
              ? 'Discard changes and leave'
              : 'Stay on the current page'
          })
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

      return true;
    },
  },
};
</script>
<style lang="scss">
#app {
  background-color: #07D89E;
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
  clear: both
}

.box-card {
  width: 550px;
  height: 650px;
  background: rgba(255,255,255,0.4);
  border: none;
}
.cbt:hover{
  background-color: #96fbc4;
  color: #96fbc4;
}
.el-icon-error{
  margin-left: 450px;
  font-size: 35px;
}
.el-icon-error:hover{
  color: #6E6E6E;
}
</style>
