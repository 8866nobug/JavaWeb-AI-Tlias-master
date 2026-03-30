package com.carrot.controller;

import com.carrot.pojo.Emp;
import com.carrot.pojo.LoginInfo;
import com.carrot.pojo.Result;
import com.carrot.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp) {
        log.info("【登录】开始，参数：{}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo != null) {
            log.info("【登录】成功，结果：{}", loginInfo);
            return Result.success(loginInfo);
        }
        log.info("【登录】失败");
        return Result.error("用户名或密码错误");
    }
}
