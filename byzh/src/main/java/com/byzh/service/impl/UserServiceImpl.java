package com.byzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.UserMapper;
import com.byzh.pojo.User;
import com.byzh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Boolean modify(User users) {
        return userMapper.updateById(users) > 0;
    }

    @Override
    public Boolean delete(String id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public User login(String id, String pwd) {
        return userMapper.getByIdAndPwd(id,pwd);
    }

    @Override
    public boolean register(User user) {
        User user1 = userMapper.selectById(user.getId());
        if(user1!=null) {
            return false;
        }
        return userMapper.register(user);
    }
}

