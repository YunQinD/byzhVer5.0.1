package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Sentences {
    private Integer id;//后端自增
    private String content;
    public Integer flag;
}
