package icu.bleuweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.bleuweb.bean.*;

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

    boolean addComment(Comment comment);

    List<Collect> getCollectSongs(int userId);

    List<SongListBean> getPlayListByStyle(String style);

    List<Singer> getSingerBySex(int sex);

    boolean addFavor(Collect collect);

    boolean isExistCollect(Collect collect);

    List<Song> getSongsBySingerId(int singerId);

    boolean likeComment(Comment comment);
}
