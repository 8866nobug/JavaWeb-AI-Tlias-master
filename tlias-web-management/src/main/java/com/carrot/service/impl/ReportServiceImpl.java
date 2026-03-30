package com.carrot.service.impl;

import com.carrot.mapper.EmpMapper;
import com.carrot.mapper.StudentMapper;
import com.carrot.pojo.JobOption;
import com.carrot.pojo.StudentClazzOption;
import com.carrot.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption empJobData() {
        List<Map<String, Object>> maps = empMapper.countEmpJobData();
        List<Object> jobList = maps.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = maps.stream().map(map -> map.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> empGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> studentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }


    @Override
    public StudentClazzOption countStudentClazzData() {
        List<Map<String, Object>> maps = studentMapper.countStudentClazzData();
        List<Object> clazzList = maps.stream().map(map -> map.get("clazzList")).toList();
        List<Object> dataList = maps.stream().map(map -> map.get("dataList")).toList();
        return new StudentClazzOption(clazzList, dataList);
    }
}
