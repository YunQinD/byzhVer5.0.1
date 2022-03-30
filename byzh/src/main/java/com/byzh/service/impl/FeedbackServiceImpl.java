package com.byzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.FeedbackMapper;
import com.byzh.pojo.Feedback;
import com.byzh.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {

}
