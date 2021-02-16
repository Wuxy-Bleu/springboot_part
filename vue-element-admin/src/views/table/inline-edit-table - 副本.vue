<template>
  <div class="app-container wuxy">
    <el-table
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      stripe
      style="width: 100%"
    >
      <el-table-column align="center" label="song" width="80">
        <template slot-scope="{ row }">
          <span @click="testClick(row)">{{ row.songName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="singer">
        <template slot-scope="{ row }">
          <i class="el-icon-time"></i>
          <span>{{ row.singerName | parseTime("{y}-{m}-{d} {h}:{i}") }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="album">
        <template slot-scope="{ row }">
          <span>{{ row.albumName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="100px" label="Importance">
        <template slot-scope="{ row }">
          <svg-icon
            v-for="n in +row.importance"
            :key="n"
            icon-class="star"
            class="meta-item__icon"
          />
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="Status" width="110">
        <template slot-scope="{ row }">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>

      <el-table-column min-width="300px" label="desc">
        <template slot-scope="{ row }">
          <template v-if="row.edit">
            <el-input v-model="row.title" class="edit-input" size="small" />
            <el-button
              class="cancel-btn"
              size="small"
              icon="el-icon-refresh"
              type="warning"
              @click="cancelEdit(row)"
            >
              cancel
            </el-button>
          </template>
          <span v-else>{{ row.title }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="upload cover " width="120">
        <template slot-scope="{ row }">
          <el-button
            v-if="row.edit"
            type="success"
            size="small"
            icon="el-icon-circle-check-outline"
            @click="confirmEdit(row)"
          >
            Ok
          </el-button>
          <el-button
            v-else
            type="primary"
            size="small"
            icon="el-icon-edit"
            @click="row.edit = !row.edit"
          >
            upload
          </el-button>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="cover">
      </el-table-column>
    </el-table>

    <el-button type="primary" @click="test02"
      >test<i class="el-icon-upload el-icon--right"></i>
    </el-button>
  </div>
</template>

<script>
import { fetchList } from "@/api/article";
import axios from "axios";
import request from "@/utils/request";

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
      list: null,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 2
      }
    };
  },
  created() {
    // this.getList();

    axios.get("/wuxy/musics").then(response => {
      console.log(Array.from(response.data));
      console.log(Array.from(response.data).length);
      this.list = Array.from(response.data);
      // console.log(this)
    });
    // .catch(function(error) {
    //   console.log(error);
    // });

    // console.log('+++++++++++++++++++++++++++++++++++++++++');
    // const { data } = await fetchList(this.listQuery);

    // console.log(data.items)
    // console.log('+++++++++++++++++++++++++++++++++++++++++')
  },
  methods: {
    // async getList() {
    //   this.listLoading = true;
    //   const { data } = await fetchList(this.listQuery);
    //   console.log(data.items);
    //   const items = data.items;
    //   this.list = items.map(v => {
    //     this.$set(v, "edit", false); // https://vuejs.org/v2/guide/reactivity.html
    //     v.originalTitle = v.title; //  will be used when users click the cancel botton
    //     return v;
    //   });
    //   // console.log(this.list);
    //   this.listLoading = false;
    // },
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
    test02(){
      console.log(this.list)
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

.wuxy {
  background-color: chartreuse;
}
</style>
