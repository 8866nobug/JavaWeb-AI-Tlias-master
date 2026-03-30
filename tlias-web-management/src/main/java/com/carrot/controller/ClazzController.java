package com.carrot.controller;

import com.carrot.anno.Log;
import com.carrot.pojo.Clazz;
import com.carrot.pojo.Param.ClazzQueryParam;
import com.carrot.pojo.PageResult;
import com.carrot.pojo.Result;
import com.carrot.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result list(ClazzQueryParam clazzQueryParam) {
        log.info("【班级列表查询】开始");
        PageResult<Clazz> pageResult = clazzService.list(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    @Log
    public Result deleteById(@PathVariable Integer id) {
        log.info("【删除班级信息】开始，删除ID：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result save(@RequestBody Clazz clazz) {
        log.info("【保存班级信息】开始，参数：{}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("【查询班级信息】开始，查询ID：{}", id);
        Clazz clazz = clazzService.getInfoById(id);
        return Result.success(clazz);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Clazz clazz) {
        log.info("【更新班级信息】开始，参数{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("list")
    public Result allList() {
        log.info("【查询所有班级信息】开始");
        List<Clazz> allList = clazzService.allList();
        return Result.success(allList);
    }
}
