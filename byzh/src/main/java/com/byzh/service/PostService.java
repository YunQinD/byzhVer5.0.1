package com.byzh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.byzh.pojo.Post;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService extends IService<Post> {
    IPage<Post> getPage(Integer currentPage, Integer pageSize);
    List<Post> searchByContent(String content);
    List<Post> searchByContentAndFlag(String content,int flag);
    List<Post> searchByPosterId(String id);
    List<Post> searchByPosterIdAndFlag(String id,int flag);
    List<Post> searchByStartLocation(String startLocation);
    List<Post> searchByDestination(String destination);
    List<Post> searchByDestinationAndFlag(String destination,int flag);
    List<Post> searchByTime(Integer year,Integer month,Integer day,Integer hour,Integer minute);
    List<Post> searchByTimeAndFlag(Integer year,Integer month,Integer day,Integer hour,Integer minute,int flag);
    List<Post> searchByStartLocationAndDestination(String startLocation,String destination);
    List<Post> listPostByFlag(int flag);
    //通过postid获取post
    Post getPostByPid (Integer pid);
    Post getPostByIdAndFlag(Integer id,int flag);
    boolean deleteById(Integer id);
    boolean insertPost(Post post,Integer teamNumber);

    //防止sql注入
    boolean sqlInjectDefend(String str);
}
