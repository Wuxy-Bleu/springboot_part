package icu.bleuweb.controller;

import com.alibaba.fastjson.JSONObject;
import icu.bleuweb.aspect.WebLog;
import icu.bleuweb.bean.Comment;
import icu.bleuweb.bean.Singer;
import icu.bleuweb.bean.Song;
import icu.bleuweb.bean.SongListBean;
import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.dataSource.DataSourceNames;
import icu.bleuweb.service.YinMusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/yin")
public class YinMusicController {

    @Autowired
    YinMusicService yinMusicService;

    @GetMapping("/songList")
    @WebLog(description = "获取所有的歌单")
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<SongListBean> allSongList() {
//        log.info("ceshi   歌单 -----------------------------------------------------");
//        System.out.println(yinMusicService.allSongList().get(0).getPicUrl());
        List<SongListBean> songListBeans = yinMusicService.allSongList();
//        System.out.println(songListBeans.get(1));
        return songListBeans;
    }

    /*配置静态资源的访问，歌单图片，歌手图片，歌曲图片*/
    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\songListPic\\");
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:./img/songListPic/");
//            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\singerPic\\");
            registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\song\\");
        }
    }

    @GetMapping("/singer")
    @WebLog(description = "获取所有的歌手")
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Singer> allSinger() {
        List<Singer> singers = yinMusicService.allSingers();
        return singers;
    }

    @GetMapping("listSong/detail")
    @WebLog(description = "获取某一张歌单下的所有歌曲")
    public int[] songInPlayList(@RequestParam Integer songListId) {
        int[] songIDs = yinMusicService.songInPlayList(songListId);
        return songIDs;
    }

    @GetMapping("rank")
    @WebLog(description = "获取一个歌单的评分")
    public double rankOfPlayList(@RequestParam int songListId) {
        double rank = yinMusicService.rankOfPlayList(songListId);
        return rank;
    }

    @GetMapping("/song/detail")
    @WebLog(description = "song select * by id")
    public Song getSongById(@RequestParam int id) {
        return yinMusicService.getSongById(id);
    }

    @GetMapping("comment/songList/detail")
    @WebLog(description = "获取歌单的评论")
    public List<Comment> getPlayListComment(@RequestParam int songListId) {
        return yinMusicService.getPlayListComment(songListId);
    }

    @GetMapping("comment/song/detail")
    @WebLog(description = "获取歌曲的评论")
    public List<Comment> getSongComment(@RequestParam int songId) {
        return yinMusicService.getSongComment(songId);
    }

    /*这次我没有用一个实体类来装这个请求体的json数据*/
    @PostMapping("/rank/add")
    @WebLog(description = "给歌单增加评分")
    public JSONObject setRankForPlaylist(@RequestBody HashMap<String, Integer> map) {
        log.info(map.toString());
        log.info(map.get("score").toString());

        boolean success = yinMusicService.setRankForPlaylist(map);
        log.info(String.valueOf(success));
        JSONObject jsonObject = new JSONObject();

        if (success) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
        }
        return jsonObject;
    }

    @PostMapping("/comment/add")
//    @WebLog(description = "给歌单或者歌曲增加评论")
    public JSONObject addComment(HttpServletRequest request) {
        String user_id = request.getParameter("userId");
        String type = request.getParameter("type");
        String song_list_id = request.getParameter("songListId");
        String song_id = request.getParameter("songId");
        String content = request.getParameter("content").trim();
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(user_id));
        if (song_list_id != null)
            comment.setSongListId(Integer.parseInt(song_list_id));
        if (song_id != null)
            comment.setSongId(Integer.parseInt(song_id));
//        comment.setType(new Byte(type) == 0 ? Integer.parseInt(song_id) : Integer.parseInt(song_list_id));
        comment.setType(Integer.parseInt(type));
        comment.setContent(content);
        comment.setCreateTime(LocalDateTime.now());
        log.info(comment.toString());
        boolean res = yinMusicService.addComment(comment);
        JSONObject jsonObject = new JSONObject();
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }


}
