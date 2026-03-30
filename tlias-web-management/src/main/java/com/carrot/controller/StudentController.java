package com.carrot.controller;

import com.carrot.anno.Log;
import com.carrot.pojo.PageResult;
import com.carrot.pojo.Result;
import com.carrot.pojo.Student;
import com.carrot.pojo.Param.StudentQueryParam;
import com.carrot.service.StudentService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result pageQuery(StudentQueryParam studentQueryParam) {
        log.info("【学生分页列表查询】开始");
        PageResult<Student> pageResult = studentService.pageQuery(studentQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{ids}")
    @Log
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("【学生删除】开始");
        studentService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result save(@RequestBody Student student) {
        log.info("【学生保存】开始");
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("【学生信息查询】开始");
        Student student = studentService.getInfoById(id);
        return Result.success(student);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Student student) {
        log.info("【学生信息更新】开始");
        studentService.update(student);
        return Result.success();
    }

    @PutMapping("violation/{id}/{score}")
    @Log
    public Result updateViolation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("【学生信息更新】开始");
        studentService.updateViolation(id, score);
        return Result.success();
    }
}

