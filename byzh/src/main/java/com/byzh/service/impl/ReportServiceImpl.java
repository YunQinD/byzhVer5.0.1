package com.byzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.byzh.Mapper.ReportMapper;
import com.byzh.pojo.Report;
import com.byzh.service.ReportService;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper,Report> implements ReportService {
}
