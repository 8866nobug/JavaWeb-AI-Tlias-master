package com.carrot.mapper;

/*
 * 员工信息
 * */

import com.carrot.pojo.Emp;
import com.carrot.pojo.Param.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {


//    ---------- 原始分页查询实现 ---------------
//    /**
//     * 获取员工总记录数
//     */
//    @Select("select count(*) from emp e left join dept d on e.dept_id = d.id")
//    public Long count();
//
//    /**
//     * 获取员工列表
//     */
//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{pageSize}")
//    public List<Emp> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize);

    public List<Emp> list(EmpQueryParam empQueryParam);

    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getInfo(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    Emp selectByUsernameAndPassword(Emp emp);
}
