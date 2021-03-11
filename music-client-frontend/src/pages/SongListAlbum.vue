<template>
  <div class="song-list-album">
    <div class="album-slide">
      <div class="album-img">
        <img :src="attachImageUrl(singers.pic)" alt="" />
      </div>
      <div class="album-info">
        <h2>简介：</h2>
        <span>
          {{ singers.introduction }}
        </span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>{{ singers.title }}</p>
      </div>
      <!--评分-->
      <div class="album-score">
        <div>
          <h3>歌单评分：</h3>
          <div>
            <el-rate v-model="value5" disabled></el-rate>
          </div>
        </div>
        <span>{{ value5 * 2 }}</span>
        <div>
          <h3>评价：</h3>
          <div @click="pushValue()">
            <el-rate v-model="value3" show-text allow-half></el-rate>
          </div>
        </div>
      </div>
      <!--歌曲-->
      <div class="songs-body">
        <album-content :songList="listOfSongs">
          <template slot="title">歌单</template>
        </album-content>
        <comment :playId="songListId" :type="1"></comment>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import mixin from "../mixins";
import AlbumContent from "../components/AlbumContent";
import Comment from "../components/Comment";
import {
  getRankOfSongListId,
  setRank,
  getSongOfId,
  getListSongOfSongId
} from "../api/index";

export default {
  name: "song-list-album",
  mixins: [mixin],
  components: {
    AlbumContent,
    Comment
  },
  data() {
    return {
      songLists: [],
      singers: {},
      count: 0, // 点赞数
      songListId: "", // 歌单ID
      value3: 0,
      value5: 0
    };
  },
  computed: {
    ...mapGetters([
      "loginIn", // 登录标识
      "tempList", // 单个歌单信息
      "listOfSongs", // 存放的音乐
      "userId", // 用户ID
      "avator" // 用户头像
    ])
  },
  created() {
    this.songListId = this.tempList.id; // 给歌单ID赋值
    this.singers = this.tempList; //这个取名很有问题啊 ，应该是歌单的信息啊，他怎么取名给singer啊
    this.getSongId(); // 获取歌单里面的歌曲ID
    this.getRank(this.songListId); // 获取评分
  },
  methods: {
    // 收集歌单里面的歌曲
    getSongId() {
      getListSongOfSongId(this.songListId)
        .then(res => {
          // console.log(res.data);
          // 获取歌单里的歌曲信息   拿到的是一个song的id数组
          for (let item of res.data) {
            this.getSongList(item);
          }
          this.$store.commit("setListOfSongs", this.songLists);
        })
        .catch(err => {
          console.log(err);
        });
    },
    // 获取单里的歌曲
    getSongList(id) {
      getSongOfId(id)
        .then(res => {
          this.songLists.push(res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    // 获取评分
    getRank(id) {
      getRankOfSongListId(id)
        .then(res => {
          this.value5 = res.data / 2; //获取到的评分为啥还除2啊
        })
        .catch(err => {
          console.log(err);
        });
    },
    // 提交评分
    pushValue() {
      if (this.loginIn) {
        // let params = new URLSearchParams();
        // params.append("songListId", this.songListId);
        // params.append("consumerId", this.userId);
        // params.append("score", this.value3 * 2);
        var data = {};
        data.songListId = this.songListId;
        data.userId = this.userId;
        data.score = this.value3 * 2;
        console.log(data);

        setRank(data)
          .then(res => {
            if (res.data.code === 1) {
              this.getRank(this.songListId);
              this.notify("评分成功", "success");
            } else {
              this.notify("评分失败", "error");
            }
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.value3 = null;
        this.notify("请先登录", "warning");
      }
    }
  }
};
</script>

<style lang="scss" scoped>
@import "../assets/css/song-list-album.scss";
</style>
