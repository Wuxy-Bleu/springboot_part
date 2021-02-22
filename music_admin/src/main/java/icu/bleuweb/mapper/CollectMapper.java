package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.Collect;
import org.apache.ibatis.annotations.Insert;
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
public interface CollectMapper extends BaseMapper<Collect> {

    @Select("select * from collect where user_id = #{userId}")
    List<Collect> getCollectSongs(int userId);

    @Insert("insert into collect(user_id, type, song_id, create_time)\n" +
            "values (#{userId}, #{type}, #{songId}, #{createTime});")
    boolean addFavor(Collect collect);

}
