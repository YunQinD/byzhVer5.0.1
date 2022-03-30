package com.byzh.Mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamMemberMapperTest {
    @Autowired
    private TeamMemberMapper teamMemberMapper;

    @Test
    public void getMembersIdByTeamIdTest(){
        String i = "1";
        System.out.println(teamMemberMapper.getMembersIdByTeamId(i));
    }

    @Test
    public void test2(){
        String tid = "10";
        System.out.println(teamMemberMapper.getFalseList(tid));
    }
}

