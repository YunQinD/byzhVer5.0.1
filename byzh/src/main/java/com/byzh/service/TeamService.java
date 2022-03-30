package com.byzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.byzh.pojo.Post;
import com.byzh.pojo.Team;
import com.byzh.pojo.User;

import java.util.List;

public interface TeamService extends IService<Team> {
    void createNewTeam(Post post,Integer teamNumber);
    List<User> getTeamMembersByTeamId(String teamId);
    boolean deleteTeam(Integer teamId);
    Integer countMembers(Integer id);
}
