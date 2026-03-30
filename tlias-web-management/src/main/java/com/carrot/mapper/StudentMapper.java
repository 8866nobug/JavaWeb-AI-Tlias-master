package com.carrot.mapper;

import com.carrot.pojo.Student;
import com.carrot.pojo.Param.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> pageQuery(StudentQueryParam studentQueryParam);

    void deleteByIds(List<Integer> ids);

    void insert(Student student);

    Student getInfoById(Integer id);

    void update(Student student);

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("clazzList")
    List<Map<String, Object>> countStudentClazzData();
}
