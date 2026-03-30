package com.carrot.aop;

import com.carrot.mapper.OperateLogMapper;
import com.carrot.pojo.OperateLog;
import com.carrot.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

/**
 * 操作日志切面类，用于记录增删改操作的日志
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知，用于记录操作日志
     * 切入点表达式匹配com.itheima.controller包下所有类的以save、insert、update、delete开头的方法
     */
    @Around("@annotation(com.carrot.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录方法执行开始时间
        long startTime = System.currentTimeMillis();

        // 2. 执行目标方法
        Object result = null;
        try {
            result = joinPoint.proceed();
        } finally {
            // 3. 计算方法执行耗时
            long endTime = System.currentTimeMillis();
            long costTime = endTime - startTime;

            // 4. 收集日志信息
            OperateLog operateLog = new OperateLog();
            operateLog.setOperateEmpId(getCurrentUserId());
            operateLog.setOperateTime(new Date());
            operateLog.setClassName(joinPoint.getTarget().getClass().getName());
            operateLog.setMethodName(joinPoint.getSignature().getName());
            operateLog.setCostTime(costTime);
            operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
            operateLog.setReturnValue(result != null ? result.toString() : "void");

            // 5. 保存日志到数据库
            log.info("【操作日志】开始保存日志，参数：{}", operateLog);
            operateLogMapper.insert(operateLog);
        }

        return result;
    }

    /**
     * 获取当前登录用户ID
     * 实际项目中需要根据你的认证机制来实现
     */
    private Integer getCurrentUserId() {
        Integer currentId = CurrentHolder.getCurrentId();

        return currentId;
    }
}
