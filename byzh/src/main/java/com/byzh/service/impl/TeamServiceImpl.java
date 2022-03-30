package com.byzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.TeamMapper;
import com.byzh.pojo.Post;
import com.byzh.pojo.Team;
import com.byzh.pojo.User;
import com.byzh.service.TeamMemberService;
import com.byzh.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private TeamMemberService teamMemberService;

    @Override
    public void createNewTeam(Post post,Integer teamNumber) {
        Team team = new Team();
        team.setPostId(post.getId());
        team.setTeamNumber(teamNumber);
        teamMapper.insert(team);
        post.setTeamId(team.getId());
        teamMemberService.insert(post.getPosterId(),post.getTeamId());
    }

    @Override
    public List<User> getTeamMembersByTeamId(String teamId) {
        return teamMemberService.getTeamMembers(teamId);
    }

    @Override
    public boolean deleteTeam(Integer teamId) {
        teamMemberService.deleteTeamMembersByTeamId(teamId);
        return teamMapper.deleteById(teamId)>0;
    }

    @Override
    public Integer countMembers(Integer id) {
        return teamMemberService.countMembers(id);
    }

}
