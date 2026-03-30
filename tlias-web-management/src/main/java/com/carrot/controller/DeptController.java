package com.carrot.controller;

import com.carrot.anno.Log;
import com.carrot.pojo.Dept;
import com.carrot.pojo.Result;
import com.carrot.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询所有部门信息
     */
    @GetMapping
    public Result getAllDept() {
        log.info("【查询所有部门】操作开始");
        List<Dept> allDept = deptService.getAllDept();
        log.info("【查询所有部门】操作结束，共查询到 {} 条数据", allDept.size());
        return Result.success(allDept);
    }

    /**
     * 根据ID查询部门详情
     */
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        log.info("【根据ID查询部门】操作开始，ID: {}", id);
        Dept dept = deptService.getDeptById(id);
        log.info("【根据ID查询部门】操作结束，结果: {}", dept != null ? dept.getName() : "未找到");
        return Result.success(dept);
    }

    /**
     * 删除指定ID的部门
     */
    @DeleteMapping
    @Log
    public Result deleteDeptById(@RequestParam Integer id) {
        log.info("【删除部门】操作开始，ID: {}", id);
        deptService.deleteDeptById(id);
        log.info("【删除部门】操作结束，ID: {} 删除成功", id);
        return Result.success();
    }

    /**
     * 新增部门信息
     */
    @PostMapping
    @Log
    public Result addDept(@RequestBody Dept dept) {
        log.info("【新增部门】操作开始，请求数据: {}", dept);
        deptService.addDept(dept);
        log.info("【新增部门】操作结束，新增部门ID: {}", dept.getId());
        return Result.success();
    }

    /**
     * 更新部门信息
     */
    @PutMapping
    @Log
    public Result updateDept(@RequestBody Dept dept) {
        log.info("【更新部门】操作开始，更新数据: {}", dept);
        deptService.updateDept(dept);
        log.info("【更新部门】操作结束，部门ID: {} 更新成功", dept.getId());
        return Result.success();
    }
}
