package com.byzh.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void getNickNameByUid (){
        String uid = "3021001462";
        System.out.println(userMapper.getNickNameByUid(uid));
    }
}
