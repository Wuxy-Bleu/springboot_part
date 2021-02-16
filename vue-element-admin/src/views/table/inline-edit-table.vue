<!--
TODO
 记录开发过程中得多个问题 
 1. vue js 开发中的注释  和   vscode中相关的注释的快捷键 和 snippt
    （修改vscode snippet 我现在敲  / * @ .  跳出来的都是 不知道什么插件提供的模板  我人都傻了   而且console.log都没有模板）
 2. 组件化 做的不是很好，所有的代码都在一个文件中很乱，组件和相关的方法 api 应该单独搞个文件
 3. vscode 中 vue对象 data函数的大段注释竟然不可以折叠起来？？？！！！
 4. axios应该自己封装下   
 5. creaated中 发送了多条请求，难道axios不提供直接发送多个请求的方法吗
 
 6. 有关vue组件的生命周期和渲染原理
    这是个非常大的主题了
    - 在created函数中就可以输出   data中的数据了，所以data数据在组件create之前就初始化好了

 7. 我各种后端请求  应该在哪里发送   比较好
    - 获取所有音乐信息的在create没啥问题吧
    - 获取签名和所有的专辑和歌手信息的axios  并不是页面一加载就需要用的  但是我还是放在create了

  8. 在填写新增一个歌曲的信息的时候   可以从数据库中已有的专辑和歌手中选择 
      但是服务端的数据 是在create的时候拿到的  
      所以如果数据库中有更新，需要重新刷新页面才能看到，而不是重新获取选择框的焦点时  自动像后端获取数据的
      其实这个逻辑也没啥问题 
      就先这样吧
      - 还有一点，每次create都会  查询数据库  太蠢了  最少设置一个缓存吧  这个缓存的设置  先利用  本地缓存  手动实现算法的方式吧

  9. 各种数据的存放及其的混乱，既有存放专辑名的请求和数据，又有存放专辑所有数据的请求和数据，好乱啊

  10. 需要写一个判断签名是否过期的逻辑
  11. 需要搞一个oss上传成功的回调，获取到url并更新数据库
  12. 表格需要设置一个排序
  13. 表格不同的行  点击提交的时候  需要附带一些数据，才知道是哪条记录上传了封面。
  14. insert 暂时逻辑有问题，只有不存在才insert 存在则update，而且insert专辑之前必须先insert singer 
      有办法搞一个先后顺序码
      因为insert 专辑的时候 必须从singer中查到相关歌手的id
  15. 如果一个功能，axios发送请求，如果成功再发送另一个请求，嵌套的写法太蠢了吧 


-->

<template>
  <div class="app-container wuxy">
    <el-button
      type="primary"
      @click="test02"
      style="margin-left: 10px; margin-top: 10px; margin-bottom: 10px;"
      >upload<i class="el-icon-upload el-icon--right"></i>
    </el-button>
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
      @row-click="rowClickTest"
    >
      <el-table-column align="center" label="song" width="80" prop="songName">
        <!-- <span>{{ songName }}</span> -->
        <!-- <template slot-scope="{ row }">
          <span @click="testClick(row)">{{ row.songName }}</span>
        </template> -->
      </el-table-column>

      <el-table-column
        width="180px"
        align="center"
        label="singer"
        prop="singerName"
      >
        <!-- <template slot-scope="{ row }">
          <i class="el-icon-time"></i>
          <span>{{ row.singerName | parseTime("{y}-{m}-{d} {h}:{i}") }}</span>
        </template> -->
      </el-table-column>

      <el-table-column
        width="120px"
        align="center"
        label="album"
        prop="albumName"
      >
        <!-- <template slot-scope="{ row }">
          <span>{{ row.albumName }}</span>
        </template> -->
      </el-table-column>

      <!-- <el-table-column width="100px" label="Importance">
        <template slot-scope="{ row }">
          <svg-icon
            v-for="n in +row.importance"
            :key="n"
            icon-class="star"
            class="meta-item__icon"
          />
        </template>
      </el-table-column> -->

      <!-- <el-table-column class-name="status-col" label="Status" width="110">
        <template slot-scope="{ row }">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column> -->

      <el-table-column min-width="100px" label="desc" prop="albumDesc">
        <template slot-scope="scope">
          <span>{{ scope.row.albumDesc }}</span>
        </template>
      </el-table-column>

      <!-- <el-table-column align="center" label="upload cover" width="400px">
        <template slot-scope="scope">
          <el-upload
            class="upload-demo"
            action
            multiple
            :before-upload="handleBeforeUpload"
            accept="image/jpeg,image/gif,image/png"
            :http-request="file => Upload(file, scope.row.albumName)"
          >
            <el-button type="success" round>
              <i class="el-icon-upload"></i
            ></el-button>
          </el-upload>
        </template>
      </el-table-column> -->

      <el-table-column
        width="260px"
        align="center"
        label="cover"
        prop="coverURL"
      >
        <template slot-scope="scope">
          <el-row align="middle">
            <el-col :span="18">
              <el-image
                style="width: 100px; height: 100px"
                :src="scope.row.coverURL"
                fit="cover"
              ></el-image>
            </el-col>
            <el-col :span="6">
              <el-upload
                class="upload-demo"
                action
                multiple
                :before-upload="handleBeforeUpload"
                accept="image/jpeg,image/gif,image/png"
                :http-request="file => Upload(file, scope.row.albumName)"
              >
                <el-button type="success" round>
                  <i class="el-icon-upload"></i
                ></el-button>
              </el-upload>
            </el-col>
          </el-row>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" prop="coverURL">
        <template slot-scope="scope">
          <el-button
            type="primary"
            style="margin-left: 10px; margin-top: 10px; margin-bottom: 10px;"
            size="mini"
            >update
          </el-button>
          <el-button
            type="primary"
            size="mini"
            @click="handleDelete(scope.row, scope.$index)"
          >
            delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="ADD" :visible.sync="dialogFormVisible">
      <el-form
        :model="musicFormData"
        status-icon
        ref="musicFormData"
        label-width="100px"
      >
        <el-form-item label="歌曲" prop="songName">
          <el-input
            v-model="musicFormData.songName"
            autocomplete="off"
          ></el-input>
        </el-form-item>
        <el-form-item label="歌手">
          <!-- <el-input
            v-model="musicFormData.singerName"
            autocomplete="off"
          ></el-input> -->
          <el-select
            v-model="musicFormData.singerName"
            filterable
            allow-create
            default-first-option
            placeholder="请选择歌手"
          >
            <el-option
              v-for="item in singers"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述" prop="desc">
          <el-input v-model.number="musicFormData.desc"></el-input>
        </el-form-item>
        <el-form-item label="专辑">
          <el-select
            v-model="musicFormData.albumName"
            filterable
            allow-create
            default-first-option
            placeholder="请选择专辑"
          >
            <el-option
              v-for="item in albums"
              :key="item"
              :label="item"
              :value="item"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <!-- <el-button type="primary" @click="getDialogData">test</el-button> -->

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="getDialogData">确 定</el-button>
      </div>
    </el-dialog>

    <!-- <el-upload
      class="upload-demo"
      action=""
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :limit="3"
      :on-exceed="handleExceed"
      :file-list="fileList"
      :before-upload="handleBeforeUpload"
    >
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">
        只能上传jpg/png文件，且不超过500kb
      </div>
    </el-upload> -->
    <br />
    <el-button
      type="primary"
      plain
      disabled
      style="position:relative;
			left:50%;"
      >album表格</el-button
    >
    <el-table
      :data="albumList"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
      :default-sort="{ prop: 'id', order: 'descending' }"
    >
      <el-table-column align="center" label="id" prop="id"> </el-table-column>
      <el-table-column align="center" label="专辑" prop="name">
      </el-table-column>
      <el-table-column align="center" label="封面url" prop="coverimage">
      </el-table-column>
      <el-table-column align="center" label="歌手id" prop="singerid">
      </el-table-column>
      <el-table-column align="center" label="desc" prop="desp">
      </el-table-column>
    </el-table>

    <br />
    <el-button
      type="primary"
      plain
      disabled
      style="position:relative;
			left:50%;"
      >singer表格</el-button
    >
    <el-table
      :data="singerList"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
      :default-sort="{ prop: 'id', order: 'descending' }"
    >
      <el-table-column align="center" label="id" prop="id"> </el-table-column>
      <el-table-column align="center" label="歌手" prop="name">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { fetchList } from "@/api/article";
import axios from "axios";
import request from "@/utils/request";
import OSS from "ali-oss";
import { param } from "@/utils";
import http from "@/wuxyUtils";
import { wuxyHttp } from "@/wuxyUtils";

/**
 * 生成随机文件名称
 * 规则八位随机字符，加下划线连接时间戳
 */
const getFileNameUUID = () => {
  function rx() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
  }
  return `${+new Date()}_${rx()}${rx()}`;
};

export default {
  name: "InlineEditTable",
  filters: {
    statusFilter(status) {
      const statusMap = {
        published: "success",
        draft: "info",
        deleted: "danger"
      };
      return statusMap[status];
    }
  },
  data() {
    return {
      list: null, //保存   多表联查获取得所有数据
      albums: null, //保存  所有得专辑名
      singers: null, //保存  所有得歌手名
      albumList: null, //保存 专辑的所有信息
      singerList: null,

      listLoading: false, //貌似是table的加载动画

      ossSignature: null, //保存 获取的OSS签名
      dialogFormVisible: false,
      musicFormData: {
        //双向绑定   ADD表单的数据
        songName: "",
        singerName: "",
        desc: "",
        albumName: ""
      },

      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
    };
  },
  created() {
    // console.log(this.$router.options.routes);

    http
      .get("/backend/musics")
      .then(res => {
        // console.log("asdjglfdskgjalksfa");
        // console.log(res.data);
        this.list = res.data;
      })
      .catch(err => {
        console.log(err);
      });

    // wuxyHttp("test").then(res => {
    //   console.log(res);
    // });

    /**
     *   获得所有的音乐信息的axios请求，部署的版本，后端已经配置好了跨域了
     *    下面那个被注释掉的是利用vue跨域请求的版本
     */
    // axios
    //   .get("http://localhost:81/backend/musics")
    //   .then(response => {
    //     this.list = JSON.parse(JSON.stringify(response.data));
    //     // console.log(this)
    //   })
    //   .catch(function(error) {
    //     console.log(error);
    //   });

    // axios //获取所有得音乐信息
    //   .get("/wuxy/musics")
    //   .then(response => {
    //     this.list = JSON.parse(JSON.stringify(response.data));
    //     // console.log(this)
    //   })
    //   .catch(function(error) {
    //     console.log(error);
    //   });

    http //获取oss 签名
      .get("/upload/test")
      .then(response => {
        // console.log(response);
        this.ossSignature = response.data;
        // console.log(this.ossSignature);
      })
      .catch(function(error) {
        console.log(error);
      });

    http //获取 专辑名
      .get("/backend/albums")
      .then(response => {
        this.albums = response.data;
      })
      .catch(error => {
        console.log(err);
      });

    http //获取 歌手名
      .get("/backend/singers")
      .then(response => {
        this.singers = response.data;
      })
      .catch(error => {
        console.log(err);
      });

    http
      .get("/backend/allAlbums")
      .then(response => {
        this.albumList = response.data;
      })
      .catch(error => {
        console.log(err);
      });

    http
      .get("/backend/allSingers")
      .then(response => {
        this.singerList = response.data;
      })
      .catch(error => {
        console.log(err);
      });

    //测试下前端axios请求带上authorization
    http
      .get("/auth/testAuth", {
        headers: {
          authorization:
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDk5MTU0NDksInN1YiI6Ind1eHkiLCJpc3MiOiIxZjIxY2U2Ny1hNmFhLTQ4ZjAtODdjMi02NTA2OTBjOTQwZWQiLCJqdGkiOiJ3dXh5IiwiYXVkIjoiZXZlcnlvbmUiLCJleHAiOjE2MDk5MTgwNDEsIm5iZiI6MTYwOTkxNTQ0OX0.1ixDpHwXHYO7fLOnOipybJDhdud5gqizDBHoatLyPlY"
        }
      })
      .then(res => {
        console.log(res);
      });
  },
  mounted() {},
  methods: {
    rowClickTest(row) {
      console.log(row);
    },
    handleDelete(row, index) {
      console.log(row);
      console.log(index);

      var url = "/backend/deleteSong";

      this.list.splice(index, 1);

      http
        .delete(url, {
          params: {
            id: this.list[index].id
          }
        })
        .then(resp => {
          console.log(resp);
        })
        .catch(error => {
          console.log(err);
        });

      this.$notify({
        title: "Success",
        message: "Delete Successfully",
        type: "success",
        duration: 2000
      });
    },

    getDialogData() {
      // this.dialogFormVisible = false;
      console.log(this.musicFormData);
      console.log(typeof this.musicFormData);

      http
        .post("/backend/addMusic", JSON.stringify(this.musicFormData), {
          headers: {
            "Content-Type": "application/json;"
          }
        })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        });
    },
    cancelEdit(row) {
      console.log(typeof row);
      console.log("=====================");

      row.title = row.originalTitle;
      row.edit = false;
      this.$message({
        message: "The title has been restored to the original value",
        type: "warning"
      });
    },
    confirmEdit(row) {
      row.edit = false;
      row.originalTitle = row.title;
      this.$message({
        message: "The title has been edited",
        type: "success"
      });
    },
    testClick() {
      console.log(row);
    },
    test02() {
      this.dialogFormVisible = true;
    },

    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      // this.$message.warning(
      //   `当前限制选择 3 个文件，本次选择了 ${
      //     files.length
      //   } 个文件，共选择了 ${files.length + fileList.length} 个文件`
      // );
    },
    beforeRemove(file, fileList) {
      return this.$confirm(`确定移除 ${file.name}？`);
    },
    handleBeforeUpload(file) {
      // console.log("before upload");
      // console.log(file);
    },
    Upload(file, name) {
      console.log("这个是最关键的了，点击上传会做什么");
      console.log(file);
      console.log(name);

      let temporary = file.file.name.lastIndexOf(".");
      let fileNameLength = file.file.name.length;
      let fileFormat = file.file.name.substring(temporary + 1, fileNameLength);
      let fileName = getFileNameUUID() + "." + fileFormat;

      //图片的实际地址
      let url =
        this.ossSignature.host + "/" + this.ossSignature.dir + "/" + fileName;

      let formData = new FormData();
      formData.append("key", this.ossSignature.dir + "/" + fileName); //存储在oss的文件路径
      formData.append("OSSAccessKeyId", this.ossSignature.accessid); //accessKeyId
      formData.append("policy", this.ossSignature.policy); //policy
      formData.append("Signature", this.ossSignature.signature); //签名
      /**
       *  这里本地测试的时候，内网不支持回调，所以先注释掉，部署的时候再加上去
       */
      // formData.append("callback", this.ossSignature.callback); //回调
      formData.append("success_action_status", 200); //成功后返回的操作码
      formData.append("file", file.file);
      // console.log("+++++++++++++++++++++++++++++++++++");
      // console.log(formData.getAll('OSSAccessKeyId'));
      // console.log("+++++++++++++++++++++++++++++++++++");

      let jsonObj = { albumName: name, coverURL: url };
      console.log(jsonObj);

      http
        .post(this.ossSignature.host, formData, {
          "Content-Type": "multipart/form-data"
        })
        .then(res => {
          console.log(res);
          console.log(url);
          if (res.status == 200) {
            //如果上传图片到oss成功，将图片oss url更新到数据库中
            http
              .post("/backend/updateCoverUrl", jsonObj, {
                headers: {
                  "Content-Type": "application/json;"
                }
              })
              .then(res => {
                console.log(res);
              });
          }
        });
    }
  }
};
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}

/* .wuxy {
  background-color: chartreuse;
} */
</style>
