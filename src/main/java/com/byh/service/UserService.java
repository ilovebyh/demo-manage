package com.byh.service;

import com.byh.entity.User;
import com.github.pagehelper.PageInfo;

public interface UserService {

    PageInfo<User> listByName(String name, Integer curPage, Integer pageSize);

    User queryEntityById(String id);

    void add(User user);

    void update(User user);

    void del(String id);

}
