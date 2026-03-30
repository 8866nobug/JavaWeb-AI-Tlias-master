package com.carrot.service.impl;

import com.carrot.mapper.EmpLogMapper;
import com.carrot.pojo.EmpLog;
import com.carrot.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;
    
    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
