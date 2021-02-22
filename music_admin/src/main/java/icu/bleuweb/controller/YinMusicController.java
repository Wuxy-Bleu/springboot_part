package icu.bleuweb.controller;

import com.alibaba.fastjson.JSONObject;
import icu.bleuweb.aspect.WebLog;
import icu.bleuweb.bean.*;
import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.dataSource.DataSourceNames;
import icu.bleuweb.feign.MailFeignService;
import icu.bleuweb.service.YinMusicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

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
    //有一个注意点，配置的静态资源的请求不需要加上外部类设置的/yin
    @Configuration
    @Profile("dev")
    public class MyPicConfig_dev implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\songListPic\\");
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:./img/songListPic/");
//            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\singerPic\\");
            registry.addResourceHandler("/img/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\song\\");
        }
    }

    @Configuration
    @Profile("test")
    public class MyPicConfig_test implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\songListPic\\");
//            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:./img/songListPic/");
//            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\Projects\\SpringBoot03\\music_springboot\\music_admin\\img\\singerPic\\");
            registry.addResourceHandler("/img/**").addResourceLocations("file:/apps/img/");
            registry.addResourceHandler("/song/**").addResourceLocations("file:/apps/song/");
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


    @Autowired
    MailFeignService mailFeignService;

    @GetMapping("/test")
    public void testFeign() {
        log.info("admin中调用mail模块的controller");
        mailFeignService.sendMail();
    }

    @GetMapping("collection/detail")
    public List<Collect> getCollectSongs(@RequestParam int userId) {
        return yinMusicService.getCollectSongs(userId);
    }

    @GetMapping("/songList/style/detail")
    public List<SongListBean> getPlayListByStyle(@RequestParam String style) {
        return yinMusicService.getPlayListByStyle('%' + style + '%');
    }

    @GetMapping("/singer/sex/detail")
    @WebLog(description = "获取分類的歌手信息")
    public List<Singer> getSingerBySex(@RequestParam int sex) {
        return yinMusicService.getSingerBySex(sex);
    }

    /*收藏歌曲或者歌单*/
    @PostMapping("/collection/add")
    public JSONObject addFavor(HttpServletRequest request) {
        String user_id = request.getParameter("userId");
        String type = request.getParameter("type");
        String song_id = request.getParameter("songId");

        Collect collect = new Collect();
        JSONObject jsonObject = new JSONObject();
        if (user_id != null && type != null && song_id != null) {
            collect.setSongId(Integer.valueOf(song_id));
            collect.setUserId(Integer.valueOf(user_id));
            collect.setType(Integer.valueOf(type));
            collect.setCreateTime(LocalDateTime.now());

            if (yinMusicService.isExistCollect(collect)) {
                jsonObject.put("code", 2);
                jsonObject.put("msg", "已收藏");
            } else {
                if (yinMusicService.addFavor(collect)) {
                    jsonObject.put("code", 1);
                    jsonObject.put("msg", "收藏成功");
                } else {
                    jsonObject.put("code", 0);
                    jsonObject.put("msg", "收藏失败");
                }
            }
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏歌曲为空");
        }
        return jsonObject;

    }

    @GetMapping("/song/singer/detail")
    @WebLog(description = "select * from song where singerid = xx")
    public List<Song> getSongsBySingerId(@RequestParam int singerId) {
        return yinMusicService.getSongsBySingerId(singerId);
    }

    @PostMapping("/comment/like")
//    @WebLog(description = "给评论点赞")
    public JSONObject likeComment(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        Comment commnet = new Comment();

        String id = request.getParameter("id");
        String up = request.getParameter("up");

        if (id != null && up != null) {
            commnet.setId(Integer.valueOf(id));
            commnet.setUp(Integer.valueOf(up));
            if (yinMusicService.likeComment(commnet)) {
                jsonObject.put("code", 1);
                jsonObject.put("msg", "点赞成功");
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "点赞失败");
            }
        }
        return jsonObject;
    }

}
