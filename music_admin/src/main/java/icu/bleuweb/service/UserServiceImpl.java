package icu.bleuweb.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.bleuweb.bean.Users;
import icu.bleuweb.mapper.UserMapper;
import org.springframework.stereotype.Service;

//为了继承了这个类之后，那么多的方法就不需要实现了，这是通过什么语法，因为继承类中全部实现了嘛
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService {

}
