package com.byzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.PostMapper;
import com.byzh.Mapper.TeamMapper;
import com.byzh.Mapper.TeamMemberMapper;
import com.byzh.Mapper.UserMapper;
import com.byzh.pojo.*;
import com.byzh.service.TeamMemberService;
import com.byzh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamMemberServiceImpl extends ServiceImpl<TeamMemberMapper, TeamMember> implements TeamMemberService {

    @Autowired
    private TeamMemberMapper teamMemberMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Autowired
    private PostMapper postMapper;


    @Override
    public void insert(String userId, Integer teamId) {

        TeamMember teamMember = new TeamMember();
        teamMember.setMemberId(userId);
        teamMember.setTeamId(teamId);
        teamMemberMapper.insert(teamMember);

    }

    @Override
    public List<User> getTeamMembers(String teamId) {
        List<String> membersIds = teamMemberMapper.getMembersIdByTeamId(teamId);
        List<User> userList = new ArrayList<>();
        for (String id : membersIds) {
            userList.add(userMapper.selectById(id));
        }
        return userList;
    }

    @Override
    public List<Integer> getTeamIdsByMemberId(String memberId) {
        List<Integer> teamIds = teamMemberMapper.getTeamIdsByMemberId(memberId);
        return teamIds;
    }

    @Override
    public int deleteTeamMembersByTeamId(Integer teamId) {
        return teamMemberMapper.deleteTeamMembersByTeamId(teamId);
    }

    //@Override
    /*public boolean JoinTeam(Integer tid, String uid) {
        return teamMemberMapper.addMemberByTeamId(tid,uid);
    }*/

    /**
     * 申请管理
     *
     * @param tid
     * @param uid
     * @return
     */
    //申请人进缓冲
    @Override
    public boolean JoinInBuffer(Integer tid, String uid) {
        return teamMemberMapper.addMemberInToBuffer(tid, uid);
    }

    //队长身份验证
    @Override
    public Boolean verifyLeader(String uid, Integer tid) {
        Integer pid = teamMemberMapper.getPostIdByTid(tid);
        String userid = teamMemberMapper.getPosterIdByPid(pid);
        if (uid.equals(userid)) {
            return true;
        } else {
            return false;
        }
    }

    //队长获取队列
    @Override
    public List<BufferList> getApplyList(String tid) {
        List<BufferList> bufferLists = teamMemberMapper.getFalseList(tid);
        List<BufferList> bufferLists1 = new ArrayList<>();
        for (BufferList bl:bufferLists) {
            bl.setNickName(userMapper.getNickNameByUid(bl.getUserId()));
            bl.setHeading(userMapper.getHeadingByUid(bl.getUserId()));
            bufferLists1.add(bl);
        }
        return bufferLists1;
    }

    //队长将申请人加入队列
    @Override
    public Boolean addApplyIntoTeam(String tid, String uid) {
        int count = countMembers(Integer.parseInt(tid));//获取实际队伍人数
        int number = teamMapper.selectById(tid).getTeamNumber();//获取队伍需要的人数
        if (number > count) {
            return teamMemberMapper.addMemberByTeamId(tid, uid);
        } else {
            return false;
        }
    }

    //更新Buffer区状态码
    @Override
    public Boolean updateMemberInBuffer(Integer sta, String tid, String uid) {
        return teamMemberMapper.changeBufferMemberState(sta, tid, uid) && teamMemberMapper.deleteTeamMembersByTeamIdAndMemberId(tid, uid);
    }
    @Override
    public Integer getState(String tid, String uid) {
        return teamMemberMapper.getStateByTidAndUid(tid, uid);
    }

    //根据队伍id查队伍人数
    @Override
    public Integer countMembers(Integer id) {
        return teamMemberMapper.getTeamMembersByTeamId(id).size();
    }

    //申请人取消申请
    @Override
    public Boolean cancelApply(String tid, String uid) {
        return teamMemberMapper.changeBufferMemberState(3, tid, uid);
    }

    //获取自己加入的队列
    @Override
    public List<AddedTeam> addedTeam(String uid) {
        //获得自己加入的teamId
        List<Integer> allTid = teamMemberMapper.getTeamIdsByMemberId(uid);
        List<Integer> List1 = teamMemberMapper.getPostIdByUid(uid);
        List<Integer> List2 = new ArrayList<>();
        for (Integer pid : List1) {
            List2.addAll(teamMemberMapper.getTeamIdByPid(pid));
        }
        allTid.removeAll(List2);
        //获得自己加入的team的Leader
        AddedTeam addedTeam = new AddedTeam();
        List<AddedTeam> addedTeams = new ArrayList<>();
        for (Integer tid : List1) {
            addedTeam.setTeamId(tid);
            addedTeam.setLeaderName(teamMemberMapper.getPosterNameIdByPid(teamMemberMapper.getPostIdByTid((tid)).toString()));
            addedTeams.add(addedTeam);
        }
        return addedTeams;
    }

    //获取自己创建的队伍
    @Override
    public List<Integer> myTeam(String uid) {
        List<Integer> List1 = teamMemberMapper.getPostIdByUid(uid);
        List<Integer> List2 = new ArrayList<>();
        for (Integer pid : List1) {
            List2.addAll(teamMemberMapper.getTeamIdByPid(pid));
        }
        return List2;
    }

    //获取自己申请的队列和状态码和队长昵称
    @Override
    public List<Applyer> myApplyTeam(String uid) {
        //获取我申请的所有队伍ID
        List<Integer> teamList = teamMemberMapper.getApplyListFromBufferByUid(uid);
        //新建Applyer列表
        List<Applyer> applyers = new ArrayList<>();
        //新建applyer对象
        //Applyer applyer = new Applyer();
        //循环新建appler对象
        for (Integer tid : teamList) {
            //判断数据库是否存在该队伍
            if(postMapper.getPostById(teamMemberMapper.getPostIdByTid((tid))) != null) {
                Applyer applyer = new Applyer();
                applyer.setTeamId(tid);
                applyer.setLeaderId(teamMemberMapper.getPosterIdByPid(teamMemberMapper.getPostIdByTid(tid)));
                applyer.setState(teamMemberMapper.getStateByTidAndUid(tid.toString(), uid));
                applyer.setNickName(teamMemberMapper.getPosterNameIdByPid(teamMemberMapper.getPostIdByTid((tid)).toString()));
                applyer.setHeading(userMapper.getHeadingByUid(teamMemberMapper.getPosterIdByPid(teamMemberMapper.getPostIdByTid(tid))));
                applyers.add(applyer);
            }

        }
        return applyers;
    }

    //获取消息列表flag内置于实体类内，0为申请加入自己的队伍，1为自己申请加入的队伍
    @Override
    public List<Message> getMessageList(String uid) {
        //获取用户id后先获取自己的队列 flag:0
        List<Integer> myTeamList = myTeam(uid);
        List<BufferList> bufferLists = new ArrayList<>();
        //获取申请加入自己的列表 flag:1
        for (Integer tid : myTeamList) {
            if(getApplyList(tid.toString()) != null){
            bufferLists.addAll(getApplyList(tid.toString()));}
        }
        //获取自己申请加入的队列
        List<Message> messageLists = new ArrayList<>();
        messageLists.addAll(bufferLists);
        messageLists.addAll(myApplyTeam(uid));
        return messageLists;
    }

    @Override
    public Boolean judgeTeamMemberExit(Integer tid, String uid) {
        return (teamMemberMapper.judgeTeamMemberExit(tid,uid));
    }
}
