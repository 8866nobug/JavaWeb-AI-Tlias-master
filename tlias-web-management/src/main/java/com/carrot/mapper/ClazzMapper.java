package com.carrot.mapper;

import com.carrot.pojo.Clazz;
import com.carrot.pojo.Param.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void deleteById(Integer id);

    void insert(Clazz clazz);

    Clazz getInfoById(Integer id);

    void update(Clazz clazz);

    List<Clazz> allList();
}
