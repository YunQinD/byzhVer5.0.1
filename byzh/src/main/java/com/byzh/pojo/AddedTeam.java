package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
/**
 * 自己加入的队列的实例
 */
@Data
@Component
public class AddedTeam {
    private Integer teamId;
    private String leaderName;
}
