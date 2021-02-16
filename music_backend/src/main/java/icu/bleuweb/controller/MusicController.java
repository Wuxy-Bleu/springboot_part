package icu.bleuweb.controller;

import icu.bleuweb.bean.AlbumBean;
import icu.bleuweb.bean.MusicBean;
import icu.bleuweb.bean.SingerBean;
import icu.bleuweb.dao.MusicDao;
import icu.bleuweb.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//TODO 这里直接调用 dao接口的需要 重构为调用service  并在service中增加相关的方法。

@CrossOrigin
@RestController
public class MusicController {

    @Autowired
    private MusicDao musicDao;

    @Autowired
    private MusicService musicService;

    @GetMapping("/musics")
    public List<MusicBean> getMusics() {
        System.out.println("获取所有的歌曲");
        return musicDao.getAllMusics();
    }

    @GetMapping("/albums")
    public List<String> getAlbums() {
        return musicDao.getAllAlbumNames();
    }

    @GetMapping("/singers")
    public List<String> getSingers() {
        return musicDao.getAllSingerName();
    }

    @GetMapping("/allAlbums")
    public List<AlbumBean> getAllAlbums() {
        return musicService.getAllAlbums();
    }

    @GetMapping("/allSingers")
    public List<SingerBean> getAllSingers() {
        return musicService.getAllSingers();
    }

    @PostMapping("/addMusic")
    public String ADDMusic(@RequestBody MusicBean musicInfo) {

        System.out.println(musicInfo);


//        try {
//            musicService.insertMusic(musicInfo);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        //insert这里因为我定义了全局的错误处理，所以我尝试着不写try catch
        //而是试着给 GlobalControllerExceptionHandler 来处理
        musicService.insertMusic(musicInfo);


        return "success";

//        return musicService.insertMusic(musicInfo) == 1 ? "success" : "fail";
    }

    @PostMapping("/updateCoverUrl")
    public String updateCoverUrl(@RequestBody MusicBean musicInfo) {
        System.out.println(musicInfo);
        musicDao.updateCoverUrl(musicInfo);
        return "success";
    }

    //删除一个表比较麻烦，要从三个表中删除相关记录，很烦。
    @DeleteMapping("/deleteSong")
    public void deleteSong(@RequestParam String id) {

        System.out.println(id);
    }

    @GetMapping("/singerNum")
    public int getSingerNum() {
        return musicService.getMusicNum(1);
    }

    @GetMapping("/songNum")
    public int getSongNum() {
        return musicService.getMusicNum(0);
    }

    @GetMapping("/albumNum")
    public int getAlbumNum() {
        return musicService.getMusicNum(2);
    }


}
