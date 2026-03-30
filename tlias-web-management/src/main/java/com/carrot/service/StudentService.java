package com.carrot.service;

import com.carrot.pojo.PageResult;
import com.carrot.pojo.Student;
import com.carrot.pojo.Param.StudentQueryParam;

import java.util.List;

public interface StudentService {

    PageResult<Student> pageQuery(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void save(Student student);

    Student getInfoById(Integer id);

    void update(Student student);

    void updateViolation(Integer id, Short score);
}
