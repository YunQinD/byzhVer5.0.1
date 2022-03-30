package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BufferList extends Message{
    private int flag = 0;
    private int id;
    private String userId;
    private String nickName;
    private String teamId;
    private int state;
    private String heading;
}
