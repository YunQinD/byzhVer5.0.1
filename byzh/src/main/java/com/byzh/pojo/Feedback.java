package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class Feedback {
    private Integer id;//不用传
    private Date time;//不用传
    private String feedbackPersonId;
    private String content;
}
