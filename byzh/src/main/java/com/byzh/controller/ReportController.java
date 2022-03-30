package com.byzh.controller;

import com.byzh.pojo.Report;
import com.byzh.service.ReportService;
import com.byzh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reports")
public class ReportController {
    @Autowired
    private ReportService reportService;
    @PostMapping
    public R reportAction(@RequestBody Report report) {
        return new R(reportService.save(report));
    }

}
