package icu.bleuweb.dao;

import icu.bleuweb.bean.AlbumBean;
import icu.bleuweb.bean.MusicBean;
import icu.bleuweb.bean.SingerBean;
import org.apache.ibatis.annotations.*;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MusicDao {

    @Select("select songs.id as id, songs.name as songName,singers.name as singerName," +
            "album.name as albumName,album.desp as albumDesc," +
            "album.coverimage as coverURL\n" +
            "from songs,\n" +
            "album, singers\n" +
            "where songs.album_id = album.id and album.singerid = singers.id;")
    public List<MusicBean> getAllMusics();

    @Select("select name from album;")
    public List<String> getAllAlbumNames();

    @Select("select name from singers;")
    public List<String> getAllSingerName();

    @Select("select * from album;")
    public List<AlbumBean> getAllAlbums();

    @Select("select * from singers;")
    public List<SingerBean> getAllSingers();

    @Insert("insert into album(name, desp) values(#{albumName}, #{albumDesc});")
    public int insertAlbum(MusicBean musicBean) throws DataAccessException;

    @Insert("insert into album(name, singerid, desp)\n" +
            "select #{albumName}, id as singerid, #{albumDesc}\n" +
            "from singers\n" +
            "where name = #{singerName};")
    public int insertAlbumWithID(MusicBean musicBean);

    @Insert("insert into singers(name) values(#{singerName});")
    public int insertSinger(MusicBean musicBean) throws DataAccessException;

    @Insert("insert into songs(name, album_id)\n" +
            "select #{songName}, id as album_id\n" +
            "from album\n" +
            "where name = #{albumName};")
    public int insertSong(MusicBean musicBean);

    @Update("update album\n" +
            "set coverimage = #{coverURL}\n" +
            "where name = #{albumName};")
    public int updateCoverUrl(MusicBean musicBean);

    @Select("SELECT \n" +
            "    COUNT(*)\n" +
            "FROM songs;")
    public int getSongNum();

    @Select("SELECT \n" +
            "    COUNT(*)\n" +
            "FROM singers;")
    public int getSingerNum();

    @Select("SELECT \n" +
            "    COUNT(*)\n" +
            "FROM album;")
    public int getAlbumNum();

//    @Delete("")
//    public int deleteSong(String id);

}