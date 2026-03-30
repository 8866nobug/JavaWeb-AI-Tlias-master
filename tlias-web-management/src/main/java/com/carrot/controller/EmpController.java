package com.carrot.controller;

/*
 * 员工Controller
 * */

import com.carrot.anno.Log;
import com.carrot.pojo.Emp;
import com.carrot.pojo.Param.EmpQueryParam;
import com.carrot.pojo.PageResult;
import com.carrot.pojo.Result;
import com.carrot.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    /**
     * 分页查询员工数据
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("【分页查询员工数据】开始，参数：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp) {
        log.info("【保存员工数据】开始，参数：{}", emp);
        empService.save(emp);
        return Result.success();

    }

    @DeleteMapping
    @Log
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("【删除员工数据】开始，参数：{}", ids);
        empService.delete(ids);
        return Result.success();

    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("【查询员工数据】开始，参数：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);

    }

    @PutMapping()
    @Log
    public Result update(@RequestBody Emp emp) {
        log.info("【更新员工数据】开始，参数：{}", emp);

        empService.update(emp);
        return Result.success();
    }
}






