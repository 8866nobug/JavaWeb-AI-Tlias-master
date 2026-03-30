package com.carrot.mapper;

import com.carrot.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LogMapper {

    List<OperateLog> pageQuery();
}
