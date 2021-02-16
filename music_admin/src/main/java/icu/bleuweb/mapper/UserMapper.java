package icu.bleuweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.bleuweb.bean.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<Users> {
}
