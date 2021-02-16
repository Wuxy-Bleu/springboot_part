<template>
  <div>
    <el-button type="primary" @click="sendMsg">{{ name }}</el-button>
    <el-input
      type="textarea"
      :rows="2"
      placeholder="请输入内容"
      v-model="textarea"
    >
    </el-input>
  </div>
</template>

<script>
export default {
  name: "chat",
  data() {
    return {
      name: "send message",
      textarea: "asf"
    };
  },
  created() {
    this.initWebSocket();
  },
  methods: {
    sendMsg() {
      console.log(this.name);
    },
    initWebSocket() {
      //初始化weosocket
      const wsuri = "ws://127.0.0.1:8084/ws/chatRoom/wuxy";
      this.websock = new WebSocket(wsuri);
      this.websock.onmessage = this.websocketonmessage;
      this.websock.onopen = this.websocketonopen;
      this.websock.onerror = this.websocketonerror;
      this.websock.onclose = this.websocketclose;
    },
    websocketonopen() {
      //连接建立之后执行send方法发送数据
      let actions = { test: "12345" };
      this.websocketsend(JSON.stringify(actions));
    },
    websocketonerror() {
      //连接建立失败重连
      this.initWebSocket();
    },
    websocketonmessage(e) {
      console.log(e);
      //数据接收
      // const redata = JSON.parse(e.data);
    },
    websocketsend(Data) {
      //数据发送
      this.websock.send(Data);
    },
    websocketclose(e) {
      //关闭
      console.log("断开连接", e);
    }
  },
  destroyed() {
    this.websock.close(); //离开路由之后断开websocket连接
  }
};
</script>
