package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TeamMember {
    private Integer id;//前端不用
    private Integer teamId;
    private String memberId;
}
