package com.byzh.Controller;

import com.byzh.controller.TeamController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamControllerTest {
    @Autowired
    private TeamController teamController;

    @Test
    public void getMeaasgeList(){
        String uid = "3021001462";
        System.out.println(teamController.getMessageList(uid));
    }


}
