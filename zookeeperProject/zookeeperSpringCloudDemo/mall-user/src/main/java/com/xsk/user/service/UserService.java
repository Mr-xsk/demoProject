package com.xsk.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xsk.common.utils.PageUtils;
import com.xsk.user.entity.UserEntity;

import java.util.Map;


public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

