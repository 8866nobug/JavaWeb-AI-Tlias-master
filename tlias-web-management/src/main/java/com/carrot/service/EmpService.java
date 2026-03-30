package com.carrot.service;

import com.carrot.pojo.Emp;
import com.carrot.pojo.Param.EmpQueryParam;
import com.carrot.pojo.LoginInfo;
import com.carrot.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
