package com.byzh.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Leader {
    private String userId;
    private String teamId;
}
