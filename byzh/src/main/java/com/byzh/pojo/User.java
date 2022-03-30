package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {
    private String id;
    private String nickname;
    private String gender;
    private String qq;
    private String wechat;
    private String pwd;
    private String major;
    private String campus;
    private String heading;
}
