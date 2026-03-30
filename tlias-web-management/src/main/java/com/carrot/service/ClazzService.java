package com.carrot.service;


import com.carrot.pojo.Clazz;
import com.carrot.pojo.Param.ClazzQueryParam;
import com.carrot.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void save(Clazz clazz);

    Clazz getInfoById(Integer id);

    void update(Clazz clazz);

    List<Clazz> allList();
}
