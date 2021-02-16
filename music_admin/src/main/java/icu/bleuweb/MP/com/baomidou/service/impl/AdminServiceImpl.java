package com.baomidou.service.impl;

import com.baomidou.entity.Admin;
import com.baomidou.mapper.AdminMapper;
import com.baomidou.service.IAdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
