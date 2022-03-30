package com.byzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byzh.pojo.User;

public interface UserService extends IService<User> {
    Boolean modify(User users);
    Boolean delete(String id);
    User login(String id,String pwd);
    boolean register(User user);
}
