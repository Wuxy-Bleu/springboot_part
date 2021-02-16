package com.baomidou.service.impl;

import com.baomidou.entity.Comment;
import com.baomidou.mapper.CommentMapper;
import com.baomidou.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
