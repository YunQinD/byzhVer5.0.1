package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Applyer extends Message{
    private int flag = 1;
    private String LeaderId;
    private String nickName;
    private Integer teamId;
    private Integer state;
    private String heading;
}
