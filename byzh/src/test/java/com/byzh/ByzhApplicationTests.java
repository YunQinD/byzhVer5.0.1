package com.byzh;

import com.byzh.Mapper.TeamMapper;
import com.byzh.pojo.Post;
import com.byzh.pojo.Report;
import com.byzh.pojo.Team;
import com.byzh.pojo.User;
import com.byzh.service.PostService;
import com.byzh.service.ReportService;
import com.byzh.service.UserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class ByzhApplicationTests {


    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private PostService postService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private UserService userService;

    void testSave() {
        Team team = new Team();
        team.setPostId(123);
        teamMapper.insert(team);
    }

    @Test
    void testPost() {
        Post post = new Post();
        post.setPosterId("3");
        post.setContent("1234");
        post.setYear(2020);
        post.setMonth(2);
        post.setDay(28);
        post.setHour(15);
        post.setMinute(30);
        post.setFlag(1);
        post.setPosterName("yr");
        post.setDestination("haaehe");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setYear(post.getYear() - 1900);
        timestamp.setMonth(post.getMonth() -1);
        timestamp.setDate(post.getDay());
        timestamp.setHours(post.getHour() );
        timestamp.setMinutes(post.getMinute());
        post.setDeadTime(timestamp);
        System.out.println(timestamp);
        //postService.insertPost(post);
    }

    void testReport() {
        Report report = new Report();
        report.setContent("hhh");
        report.setPostId(1);
        report.setReporterId("123456");
        reportService.save(report);
    }

    void testUser() {
        User user = new User();
        user.setCampus("X");
        user.setGender("M");
        user.setId("3023131230");
        user.setMajor("fsda");
        user.setNickname("yryry");
        user.setPwd("12345");
        user.setQq("12331");
        user.setWechat("12321");
        userService.register(user);
    }

}
