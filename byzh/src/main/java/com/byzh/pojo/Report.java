package com.byzh.pojo;

import lombok.Data;

@Data
public class Report {
    private Integer id;//不用传
    private String reporterId;
    private Integer postId;
    private String content;
}
