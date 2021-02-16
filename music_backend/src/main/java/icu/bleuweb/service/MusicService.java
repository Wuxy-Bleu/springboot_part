package icu.bleuweb.service;

import icu.bleuweb.bean.AlbumBean;
import icu.bleuweb.bean.MusicBean;
import icu.bleuweb.bean.SingerBean;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MusicService {

    public int insertMusic(MusicBean musicBean) /*throws DataAccessException*/;

    public List<AlbumBean> getAllAlbums();

    public List<SingerBean> getAllSingers();

    //TODO 这个kind 可以用枚举来代替
    int getMusicNum(int kind);

}
