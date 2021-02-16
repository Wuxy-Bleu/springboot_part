package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.Rank;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

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
public interface RankMapper extends BaseMapper<Rank> {

    @Select("select sum(score)\n" +
            "from `rank`\n" +
            "where songListId = #{songListId};")
    int rankOfPlayList(int songListId);

    @Select("select count(score)\n" +
            "from `rank`\n" +
            "where songListId = #{songListId};")
    int numOfRatings(int songListId);

    //这里的sql逻辑存在问题,应该是该songlistid下该userid有记录 覆盖,没记录insert
    @Insert("insert into `rank`(songlistid, consumerid, score)\n" +
            "VALUES (#{songlistId},#{userId},#{score});")
    boolean setRankForPlaylist(Rank rank);
}
