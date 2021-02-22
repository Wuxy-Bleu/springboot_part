package icu.bleuweb.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

    @Autowired
    CollectMapper collectMapper;

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Collect> getCollectSongs(int userId) {
        return collectMapper.getCollectSongs(userId);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<SongListBean> getPlayListByStyle(String style) {
        return songListMapper.getPlayListByStyle(style);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Singer> getSingerBySex(int sex) {
        return singerMapper.getSingerBySex(sex);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public boolean addFavor(Collect collect) {
        return collectMapper.addFavor(collect);
    }

    //没用到
    @DataSource(DataSourceNames.TP_MUSIC)
    public int addFavor2(Collect collect) {
        return collectMapper.insert(collect);
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public boolean isExistCollect(Collect collect) {
        List<Collect> collects = collectMapper.selectList(new QueryWrapper<Collect>()
                .exists("select * from collect where user_id = "
                        + collect.getUserId() + " and song_id = " + collect.getSongId()));
        return collects == null;
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public List<Song> getSongsBySingerId(int singerId) {
        return songMapper.selectList(new QueryWrapper<Song>().eq("singer_id", singerId));
    }

    @Override
    @DataSource(DataSourceNames.TP_MUSIC)
    public boolean likeComment(Comment comment) {
        return commentMapper.updateById(comment) == 1;
    }
}
