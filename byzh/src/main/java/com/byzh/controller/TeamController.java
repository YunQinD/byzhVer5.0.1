package com.byzh.controller;

import com.byzh.pojo.BufferList;
import com.byzh.pojo.Message;
import com.byzh.pojo.TeamMember;
import com.byzh.pojo.User;
import com.byzh.service.TeamMemberService;
import com.byzh.service.TeamService;
import com.byzh.utils.R;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 君子协定：
 * 写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注写备注
 */

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    private TeamMemberService teamMemberService;

    //获取预期人数
    @GetMapping("/tm/{id}")
    public R getTeamById(@PathVariable Integer id) {
        return new R(true,teamService.getById(id));
    }

    //获取队伍成员列表
    @GetMapping("{id}")
    public R getTeamMembersByTeamId(@PathVariable String id) {
        return new R(true,teamService.getTeamMembersByTeamId(id));
    }

    //通过ID删除队列
    @DeleteMapping("{id}")
    public R deleteTeamById(@PathVariable Integer id) {
        return new R(teamService.deleteTeam(id));
    }

    /**
     * 申请管理：申请人进入缓冲区 & 队长管理
     *
     * @param teamMember
     * @return
     */
    //申请人进入缓冲区
    @PostMapping("apply")
    public R JoinInBuffer(@RequestBody TeamMember teamMember) {
        Integer tid = teamMember.getTeamId();
        String uid = teamMember.getMemberId();
        if(teamMemberService.judgeTeamMemberExit(tid,uid) == null){
            return new R(teamMemberService.JoinInBuffer(tid, uid));}
        if(teamMemberService.judgeTeamMemberExit(tid,uid) == true){
            return new R(false,null);}
        else {
            return new R(teamMemberService.JoinInBuffer(tid, uid));
        }
    }

    //队长获取申请队列：审核+获取
    @GetMapping("GAL/{tid}")
    public R getApplyList(/*@PathVariable String uid,*/ @PathVariable String tid) {
//        if (teamMemberService.verifyLeader(uid, tid)) {
            return new R(true, teamMemberService.getApplyList(tid));
//        } else {
//            return new R(false);//没有访问权限
//        }
    }

    //队长同意后成员加入队列,传入申请人对象
    @PostMapping("join")
    public R JoinTeam(@RequestBody TeamMember teamMember) {
        String tid = teamMember.getTeamId().toString();
        String uid = teamMember.getMemberId();
        teamMemberService.updateMemberInBuffer(1,tid,uid);
        return new R(teamMemberService.addApplyIntoTeam(tid, uid));
    }

    //拒绝后更改Buffer申请为refuse:4
    @PostMapping("refuse")
    public R refuseApply(@RequestBody BufferList bufferList){
        String uid = bufferList.getUserId();
        String tid = bufferList.getTeamId();
        return new R(teamMemberService.updateMemberInBuffer(4,tid,uid));
    }

    //获取状态码
    @GetMapping("getSta/{tid}/{uid}")
    public R getState(@PathVariable String tid,String uid){
        return new R(true,teamMemberService.getState(tid,uid));
    }

    //根据队伍id获得实际人数
    @GetMapping("/count/{id}")
    public R countById(@PathVariable Integer id) {
        return new R(true,teamService.countMembers(id));
    }

    //申请人取消申请
    @PostMapping("cancel/{tid}/{uid}")
    public R cancelApply(@PathVariable String tid,@PathVariable String uid){
        return new R(teamMemberService.cancelApply(tid,uid));
    }

    //获取自己加入的队伍
    @GetMapping("addedTeam/{uid}")
    public R addedTeam(@PathVariable String uid){
        return new R(true,teamMemberService.addedTeam(uid));
    }

    //获取自己创建的队伍
    @GetMapping("myTeam/{uid}")
    public R myTeam(@PathVariable String uid){
        return new R(true,teamMemberService.myTeam(uid));
    }

    //获取自己申请的队伍
    @GetMapping("myApply/{uid}")
    public R myApply(@PathVariable String uid){
        return new R(true,teamMemberService.myApplyTeam(uid));
    }

    //获取自己有关的队伍
    @GetMapping("aboutMe/{uid}")
    public R aboutMe(@PathVariable String uid){
        List<R> aboutMeList = new ArrayList<>();
        aboutMeList.add(myTeam(uid));
        aboutMeList.add(myApply(uid));
        return new R(true,aboutMeList);
    }

    //获取消息列表flag内置于实体类内，0为申请加入自己的队伍，1为自己申请加入的队伍
    @GetMapping("message/{uid}")
    public R getMessageList(@PathVariable String uid) {
        return new R(true,teamMemberService.getMessageList(uid));
    }
}
