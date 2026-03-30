package com.carrot.service;

import com.carrot.pojo.OperateLog;
import com.carrot.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> pageQuery(Integer page, Integer pageSize);
}
