package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.Song;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wuxy
 * @since 2021-02-15
 */
@Mapper
@Repository
public interface SongMapper extends BaseMapper<Song> {

    @Select("select * from song")
    List<Song> allSong();
}
