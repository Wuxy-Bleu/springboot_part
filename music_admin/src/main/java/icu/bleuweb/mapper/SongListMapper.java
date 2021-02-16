package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.SongListBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SongListMapper extends BaseMapper<SongListBean> {

    @Select("select * from song_list")
    List<SongListBean> allSongList();
}
