package com.byh.controller;

import com.byh.entity.User;
import com.byh.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public PageInfo<User> list(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "1")Integer curPage, @RequestParam(defaultValue = "2")Integer pageSize){
        PageInfo<User> page =    userService.listByName(name,curPage,pageSize);
        return page;
    }

    @RequestMapping("/add")
    public void add(User user){
        userService.add(user);
    }

    @RequestMapping("/update")
    public void update(User user){
        userService.update(user);
    }

    @RequestMapping("/del")
    public void del(String id){
        userService.del(id);
    }

}
