package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class Team {
    private Integer id;//前端不用
    private Integer postId;
    private Integer teamNumber;
}




