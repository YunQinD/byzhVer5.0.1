package com.byzh.Service;

import com.byzh.service.impl.TeamMemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamMemberServiceTest {
    @Autowired
    private TeamMemberServiceImpl teamMemberService;

    @Test
    public void test1(){
        String uid = "3021001462";
        System.out.println(teamMemberService.getMessageList(uid));
    }

    @Test
    public void getApplyListTest(){
        String tid = "11";
        System.out.println(teamMemberService.getApplyList(tid));
    }
}
