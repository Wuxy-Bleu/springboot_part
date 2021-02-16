package icu.bleuweb.mapper;

import com.baomidou.entity.ListSong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
public interface ListSongMapper extends BaseMapper<ListSong> {

    @Select("select song_id\n" +
            "from list_song\n" +
            "where song_list_id = #{songListId};")
    int[] songInPlayList(Integer songListId);
}
