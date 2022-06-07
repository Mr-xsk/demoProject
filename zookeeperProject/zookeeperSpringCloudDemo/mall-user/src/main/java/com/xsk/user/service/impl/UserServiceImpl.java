package com.xsk.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xsk.common.utils.PageUtils;
import com.xsk.common.utils.Query;
import com.xsk.user.dao.UserDao;
import com.xsk.user.entity.UserEntity;
import com.xsk.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UserEntity> page = this.page(
                new Query<UserEntity>().getPage(params),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page);
    }

}