package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.Comment;
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
public interface CommentMapper extends BaseMapper<Comment> {


    @Select("select * from comment where song_list_id=#{songListId}")
    List<Comment> getPlayListComment(int songListId);

    @Select("select * from comment where song_id=#{songId}")
    List<Comment> getSongComment(int songId);

    @Insert("insert into comment (user_id,\n" +
            "                     song_list_id, content, create_time,\n" +
            "                     type)\n" +
            "values (\n" +
            "#{userId},\n" +
            "#{songListId}, #{content}, #{createTime},\n" +
            "#{type}\n" +
            ")")
    boolean addPlayListComment(Comment comment);

    @Insert("insert into comment (user_id,\n" +
            "                     song_id, content, create_time,\n" +
            "                     type)\n" +
            "values (\n" +
            "#{userId},\n" +
            "#{songId}, #{content}, #{createTime},\n" +
            "#{type}\n" +
            ")")
    boolean addSongComment(Comment comment);
}
