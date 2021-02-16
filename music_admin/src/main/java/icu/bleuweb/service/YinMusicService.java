package icu.bleuweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.bleuweb.bean.Comment;
import icu.bleuweb.bean.Singer;
import icu.bleuweb.bean.Song;
import icu.bleuweb.bean.SongListBean;

import java.util.HashMap;
import java.util.List;

public interface YinMusicService extends IService<SongListBean> {

    List<SongListBean> allSongList();

    List<Song> allSong();

    List<Singer> allSingers();

    int[] songInPlayList(Integer songListId);

    double rankOfPlayList(int songListId);

    Song getSongById(int id);

    List<Comment> getPlayListComment(int songListId);

    List<Comment> getSongComment(int songId);

    boolean setRankForPlaylist(HashMap<String, Integer> map);
}
