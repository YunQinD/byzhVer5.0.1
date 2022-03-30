package com.byzh.pojo;

import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Post {
    private Integer id;//前端不用
    private Date postTime;//前端不用
    private String content;
    private String posterId;
    private String startLocation; //拼车专用
    private String destination;
    private String posterName;
    private String heading; //poster heading
    private Timestamp deadTime;//前端不要
    //约定的时间
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer teamId;//前端不用
    private int flag;//0 拼车 1 学习 2 玩乐
}
