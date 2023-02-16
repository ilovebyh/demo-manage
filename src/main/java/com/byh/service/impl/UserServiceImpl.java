package com.byh.service.impl;

import com.byh.common.UUIDGenerator;
import com.byh.entity.User;
import com.byh.mapper.UserMapper;
import com.byh.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> listByName(String name, Integer curPage, Integer pageSize) {
        PageHelper.startPage(curPage,pageSize);
        List<User> userList =  userMapper.listByName(name);
        PageInfo<User> page = new PageInfo<>(userList);
        return page;
    }

    @Override
    public void add(User user) {
        user.setId(UUIDGenerator.getUUID());
        userMapper.add(user);
    }

    @Override
    public void update(User user) {

        userMapper.update(user);
    }

    @Override
    public void del(String id) {

        userMapper.del(id);
    }

}
