package com.byh.mapper;


import com.byh.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> listByName(String name);

    User queryEntityById(String id);

    void add(User user);

    void update(User user);

    void del(String id);

}
