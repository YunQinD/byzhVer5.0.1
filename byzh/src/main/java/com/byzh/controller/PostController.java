package com.byzh.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.byzh.pojo.Post;
import com.byzh.service.PostService;
import com.byzh.service.TeamService;
import com.byzh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.jar.JarEntry;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private TeamService teamService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public R getAll() {
        return new R(true,postService.list());
    }

    @GetMapping("{id}")
    public R getPostById(@PathVariable Integer id) {
        return new R(true,postService.getPostByPid(id));
    }

    @GetMapping("/flag/{flag}")
    public R getPostByFlag(@PathVariable int flag) {
        return new R(true,postService.listPostByFlag(flag));
    }

    @GetMapping("/id/{id}/{flag}")
    public R getPostById(@PathVariable Integer id,@PathVariable int flag) {
        return new R(true,postService.getPostByIdAndFlag(id,flag));
    }

    @GetMapping("/{currentPage}/{pageSize}")
    public R getAllByPage(@PathVariable Integer currentPage,@PathVariable Integer pageSize) {
        IPage<Post> page = postService.getPage(currentPage,pageSize);
        return new R(true,page);
    }

    //teamNumber指队伍的人数
    @PostMapping
    public R save(@RequestBody Post post,@RequestParam("teamNumber") Integer teamNumber) {
        if (post.getStartLocation()!="" && post.getDestination()!=""&&post.getContent()!=""){
            return new R(postService.insertPost(post,teamNumber));
        }else{
            return new R(false,"帖子内容为空");
        }

    }

    @PutMapping
    public R update(@RequestBody Post post) {
        return new R(postService.updateById(post));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable Integer id) {
        return new R(postService.deleteById(id));
    }

    @GetMapping("/search/content/{content}")
    public R searchByContent(@PathVariable String content) {
        return new R(true,postService.searchByContent(content));
    }

    @GetMapping("/search/content/{content}/{flag}")
    public R searchByContent(@PathVariable String content,@PathVariable int flag) {
        return new R(true,postService.searchByContentAndFlag(content,flag));
    }

    @GetMapping("/search/startlocation/{startLocation}")
    public R searchByStartLocation(@PathVariable String startLocation) {
        if(postService.sqlInjectDefend(startLocation)){
            return new R(true,postService.searchByStartLocation(startLocation));
        }else {
            return new R(false, null);
        }

    }

    @GetMapping("/search/destination/{destination}")
    public R searchByDestination(@PathVariable String destination) {
        if(postService.sqlInjectDefend(destination)){
            return new R(true,postService.searchByDestination(destination));
        }else {
            return new R(false, null);
        }
    }

    @GetMapping("/search/destination/{destination}/{flag}")
    public R searchByDestination(@PathVariable String destination,@PathVariable int flag) {
        if(postService.sqlInjectDefend(destination)){
            return new R(true,postService.searchByDestinationAndFlag(destination,flag));
        }else {
            return new R(false, null);
        }
    }

    @GetMapping("/search/sad/{startLocation}/{destination}")
    public R searchByStartLocationAndDestination(@PathVariable String startLocation,@PathVariable String destination) {
        if(postService.sqlInjectDefend(destination)){
            return new R(true,postService.searchByStartLocationAndDestination(startLocation,destination));
        }else {
            return new R(false, null);
        }
    }

    @GetMapping("/search/poster/{id}")
    public R searchByPosterId(@PathVariable String id) {
        return new R(true,postService.searchByPosterId(id));
    }

    @GetMapping("/search/poster/{id}/{flag}")
    public R searchByPosterId(@PathVariable String id,@PathVariable int flag) {
        return new R(true,postService.searchByPosterIdAndFlag(id,flag));
    }

    @GetMapping("/search/time/{year}/{month}/{day}/{hour}/{minute}")
    public R searchByTime(@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day,@PathVariable Integer hour,@PathVariable Integer minute) {
        return new R(true,postService.searchByTime(year,month,day,hour,minute));
    }

    @GetMapping("/search/time/{year}/{month}/{day}/{hour}/{minute}/{flag}")
    public R searchByTime(@PathVariable Integer year,@PathVariable Integer month,@PathVariable Integer day,@PathVariable Integer hour,@PathVariable Integer minute,@PathVariable int flag) {
        return new R(true,postService.searchByTimeAndFlag(year,month,day,hour,minute,flag));
    }


}
