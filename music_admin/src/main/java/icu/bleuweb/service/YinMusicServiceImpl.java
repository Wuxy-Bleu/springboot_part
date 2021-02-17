package icu.bleuweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.bleuweb.bean.*;
import icu.bleuweb.dataSource.DataSource;
import icu.bleuweb.dataSource.DataSourceNames;
import icu.bleuweb.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@Service
public class YinMusicServiceImpl extends ServiceImpl<SongListMapper, SongListBean> implements YinMusicService {

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public SongListBean getById(Serializable id) {
        return super.getById(id);
    }

    @Autowired
    SongListMapper songListMapper;
    @Autowired
    SongMapper songMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<SongListBean> allSongList() {
        return songListMapper.allSongList();
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    @Autowired
    SingerMapper singerMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Singer> allSingers() {
        return singerMapper.allSingers();
    }

    @Autowired
    ListSongMapper listSongMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public int[] songInPlayList(Integer songListId) {
        return listSongMapper.songInPlayList(songListId);
    }

    @Autowired
    RankMapper rankMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public double rankOfPlayList(int songListId) {
        return rankMapper.rankOfPlayList(songListId) / rankMapper.numOfRatings(songListId);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public Song getSongById(int id) {
        return songMapper.selectById(id);
    }

    @Autowired
    CommentMapper commentMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Comment> getPlayListComment(int songListId) {
        return commentMapper.getPlayListComment(songListId);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Comment> getSongComment(int songId) {
        return commentMapper.getSongComment(songId);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public boolean setRankForPlaylist(HashMap<String, Integer> map) {
        Rank rank = new Rank();
        rank.setSonglistId(map.get("songListId").longValue());
        rank.setUserId(map.get("userId").longValue());
        rank.setScore(map.get("score"));

        return rankMapper.setRankForPlaylist(rank);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public boolean addComment(Comment comment) {
        if (comment.getType() == 1)
            return commentMapper.addPlayListComment(comment);
        else
            return commentMapper.addSongComment(comment);
    }
}
