package icu.bleuweb.service.impl;

import icu.bleuweb.bean.AlbumBean;
import icu.bleuweb.bean.MusicBean;
import icu.bleuweb.bean.SingerBean;
import icu.bleuweb.dao.MusicDao;
import icu.bleuweb.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicDao musicDao;

    @Override
    @Transactional
    public int insertMusic(MusicBean musicBean) {

        try {
            musicDao.insertSinger(musicBean);
            musicDao.insertAlbumWithID(musicBean);
            musicDao.insertSong(musicBean);
        } catch (Exception exception) {
            System.out.println("插入出问题了");
            throw exception;
        } finally {
            System.out.println("这里会执行吗");
        }

//在这里成功返回1 失败返回0
        return 1;

//        return musicDao.insertAlbum(musicBean) == 1 && musicDao.insertSinger(musicBean) == 1 ? 1 : 0;
    }

    @Override
    public List<AlbumBean> getAllAlbums() {
        return musicDao.getAllAlbums();
    }

    @Override
    public List<SingerBean> getAllSingers() {
        return musicDao.getAllSingers();
    }

    @Override
    public int getMusicNum(int kind) {
        if (kind == 0)
            return musicDao.getSongNum();
        else if (kind == 1)
            return musicDao.getSingerNum();
        else
            return musicDao.getAlbumNum();
    }
}
