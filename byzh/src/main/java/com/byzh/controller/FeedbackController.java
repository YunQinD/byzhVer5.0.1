package com.byzh.controller;

import com.byzh.pojo.Feedback;
import com.byzh.service.FeedbackService;
import com.byzh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @PostMapping
    public R submit(@RequestBody Feedback feedback) {
        feedback.setTime(new Date(System.currentTimeMillis()));
        return new R(feedbackService.save(feedback));
    }
    @GetMapping
    public R getAll() {
        return new R(true,feedbackService.list());
    }
}
