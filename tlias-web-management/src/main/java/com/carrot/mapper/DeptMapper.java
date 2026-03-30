package com.carrot.mapper;

import com.carrot.pojo.Dept;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DeptMapper {

    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    public List<Dept> getAllDept();

    @Delete("delete from dept where id = #{id}")
    public int deleteDeptById(Integer id);

    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    int addDept(Dept dept);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    int updateDept(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getDeptById(Integer id);
}
