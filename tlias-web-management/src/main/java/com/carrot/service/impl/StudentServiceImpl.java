package com.carrot.service.impl;

import com.carrot.mapper.StudentMapper;
import com.carrot.pojo.PageResult;
import com.carrot.pojo.Student;
import com.carrot.pojo.Param.StudentQueryParam;
import com.carrot.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Student> pageQuery(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        List<Student> students = studentMapper.pageQuery(studentQueryParam);
        Page<Student> studentPage = (Page<Student>) students;
        return new PageResult<Student>(studentPage.getTotal(), studentPage.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(new Date());
        student.setUpdateTime(new Date());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfoById(Integer id) {
        return studentMapper.getInfoById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(new Date());
        studentMapper.update(student);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void updateViolation(Integer id, Short score) {
        Student student = studentMapper.getInfoById(id);
        student.setUpdateTime(new Date());
        student.setViolationCount((short) (student.getViolationCount() + score));
        studentMapper.update(student);

    }


}
