package com.baomidou.service.impl;

import com.baomidou.entity.Song;
import com.baomidou.mapper.SongMapper;
import com.baomidou.service.ISongService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuxy
 * @since 2021-02-15
 */
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements ISongService {

}
