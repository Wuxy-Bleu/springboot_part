/* eslint-disable*/
import axios from "axios";
import { get, post } from "./http";

// =======================> 用户 API
// 登录
// export const loginIn = params => post(`user/login/status`, params);
// 注册
export const SignUp = params => post(`user/add`, params);
// 更新用户信息
export const updateUserMsg = params => post(`user/update`, params);
// 返回指定ID的用户
// export const getUserOfId = id => get(`user/detail?id=${id}`);

// =======================> 歌单 API
// 获取全部歌单
// export const getSongList = () => get("songList");
// 获取歌单类型
// export const getSongListOfStyle = style =>
//   get(`songList/style/detail?style=${style}`);
// 返回标题包含文字的歌单
export const getSongListOfLikeTitle = keywords =>
  get(`songList/likeTitle/detail?title=${keywords}`);
// 返回歌单里指定歌单ID的歌曲
// export const getListSongOfSongId = songListId =>
//   get(`listSong/detail?songListId=${songListId}`);

// =======================> 歌手 API
// 返回所有歌手
// export const getAllSinger = () => get("singer");
// 通过性别对歌手分类
// export const getSingerOfSex = sex => get(`singer/sex/detail?sex=${sex}`);

// =======================> 收藏 API
// 返回的指定用户ID的收藏列表
// export const getCollectionOfUser = userId =>
//   get(`collection/detail?userId=${userId}`);
// 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
// export const setCollection = params => post(`collection/add`, params);

// =======================> 评分 API
// 提交评分
// export const setRank = params => post(`rank/add`, params);
// 获取指定歌单的评分
// export const getRankOfSongListId = songListId =>
//   get(`rank?songListId=${songListId}`);

// =======================> 评论 API
// 添加评论
// export const setComment = params => post(`comment/add`, params);
// 点赞
// export const setLike = params => post(`comment/like`, params);
// 返回所有评论
// export const getAllComment = (type, id) => {
//   let url = "";
//   if (type === 1) {
//     url = `comment/songList/detail?songListId=${id}`;
//   } else if (type === 0) {
//     url = `comment/song/detail?songId=${id}`;
//   }
//   return get(url);
// };

// =======================> 歌曲 API
// 返回指定歌曲ID的歌曲
// export const getSongOfId = id => get(`song/detail?id=${id}`);
// 返回指定歌手ID的歌曲
// export const getSongOfSingerId = id => get(`song/singer/detail?singerId=${id}`);
// 返回指定歌手名的歌曲   這玩意沒用到啊
export const getSongOfSingerName = keywords =>
  get(`song/singerName/detail?name=${keywords}`);
// 下载音乐
export const download = url =>
  axios({
    method: "get",
    url: url,
    responseType: "blob"
  });


// =========================================================>
//封装我自己的axios对象和http请求

const http = axios.create({
  baseURL: 'http://139.224.135.120:81/',
  timeout: 5000,
  withCredentials: false
})

//是否登录
export const isLogin = token => http('/auth/redis/isLogin',
  {
    params: {
      token: token
    },
  })

//获取用户的基本信息的
export const getInfo = token => http({
  url: '/auth/user/info',
  method: 'post',
  params: { token }
})

export const loginIn = params => http(
  {
    url: `/auth/user/login`,
    method: 'post',
    params
  });

//select * by user id
export const getUserOfId = id => http({
  url: "/auth/user/detail/" + id,
  method: 'get'
});

// 获取所有的歌单和歌手，就直接select * 返回的
export const getSongList = () => http({
  url: "/auth/yin/songList",
  method: 'get'
});
export const getAllSinger = () => http({
  url: "/auth/yin/singer",
  method: 'get'
});
//获取一个歌单下的所有的歌曲的id
export const getListSongOfSongId = songListId =>
  http(`/auth/yin/listSong/detail?songListId=${songListId}`);
//获取一个歌单的评分
export const getRankOfSongListId = songListId =>
  http(`/auth/yin/rank?songListId=${songListId}`);
//通过某一个song id 就是getById了
export const getSongOfId = id => http(`/auth/yin/song/detail?id=${id}`);

//两个功能，返回歌单的评论和歌曲的评论 
export const getAllComment = (type, id) => {
  let url = "";
  if (type === 1) {
    url = `/auth/yin/comment/songList/detail?songListId=${id}`;
  } else if (type === 0) {
    url = `/auth/yin/comment/song/detail?songId=${id}`;
  }
  return http(url);
};
//增加评分 歌单
export const setRank = params => http({
  url: `/auth/yin/rank/add`,
  method: 'post',
  data: params,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  },
});
//给歌单或者歌曲添加评论，歌单还是歌曲的判断在后端进行
export const setComment = params => http({
  url: `/auth/yin/comment/add`,
  method: 'post',
  data: params
});
//获取某个用户收藏的所有歌曲
export const getCollectionOfUser = userId =>
  http(`/auth/yin/collection/detail?userId=${userId}`);
//根据type获取所有的歌单 type是字符串
export const getSongListOfStyle = style =>
  http(`/auth/yin/songList/style/detail?style=${style}`);
//根据sex获取所有的歌手 sex是数字
export const getSingerOfSex = sex => http(`/auth/yin/singer/sex/detail?sex=${sex}`);
// 添加收藏的歌曲 type: 0 代表歌曲， 1 代表歌单
export const setCollection = params => http({
  url: `/auth/yin/collection/add`,
  method: 'post',
  data: params
});
// 给评论点赞
export const setLike = params => http({
  url: `/auth/yin/comment/like`,
  method: 'post',
  data: params
});
//查询指定歌手的所有歌曲 
export const getSongOfSingerId = id => http(`/auth/yin/song/singer/detail?singerId=${id}`);