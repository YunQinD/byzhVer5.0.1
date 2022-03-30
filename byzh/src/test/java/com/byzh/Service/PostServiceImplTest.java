package com.byzh.Service;

import com.byzh.service.impl.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceImplTest {
    @Autowired
    private PostServiceImpl postService;


    @Test
    public void sqlInjectDefendTest(){
        String sql = "'1=1'";
        System.out.println(postService.sqlInjectDefend(sql));
    }
}
