package com.carrot.controller;

import com.carrot.pojo.JobOption;
import com.carrot.pojo.Result;
import com.carrot.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("【员工职位数据报表】开始生成");
        JobOption jobOption = reportService.empJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("【员工性别数据报表】开始生成");
        return Result.success(reportService.empGenderData());
    }

    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("【学生学历数据报表】开始生成");
        return Result.success(reportService.studentDegreeData());
    }

    @GetMapping("studentCountData")
    public Result studentCountData() {
        log.info("【学生数量数据报表】开始生成");
        return Result.success(reportService.countStudentClazzData());
    }
}
