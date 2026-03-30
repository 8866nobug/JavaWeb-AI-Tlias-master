package com.carrot.service.impl;

import com.carrot.mapper.LogMapper;
import com.carrot.pojo.OperateLog;
import com.carrot.pojo.PageResult;
import com.carrot.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public PageResult<OperateLog> pageQuery(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<OperateLog> operateLogs = logMapper.pageQuery();
        Page<OperateLog> operateLogPage = (Page<OperateLog>) operateLogs;
        return new PageResult<OperateLog>(operateLogPage.getTotal(), operateLogPage.getResult());
    }
}
