package com.carrot.service;

import com.carrot.pojo.JobOption;
import com.carrot.pojo.StudentClazzOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    JobOption empJobData();

    List<Map<String,Object>> empGenderData();

    List<Map<String,Object>> studentDegreeData();

    StudentClazzOption countStudentClazzData();
}
