<template>
  <el-form :inline="true" :model="formInline" class="demo-form-inline">
    <el-form-item>
      <el-input v-model="formInline.name" placeholder="用户名"></el-input>
    </el-form-item>
    <el-form-item>
      <el-input v-model="formInline.password" placeholder="密码"> </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">register</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { register } from "@/api/user";
export default {
  data() {
    return {
      formInline: {
        name: "",
        password: ""
      }
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
      register(this.formInline).then(response => {
        const { data } = response;
        console.log(data);
      });
    }
  }
};
</script>
