package com.carrot.service;


import com.carrot.pojo.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> getAllDept();

    public int deleteDeptById(Integer id);

    public int addDept(Dept dept);

    public int updateDept(Dept dept);

    public Dept getDeptById(Integer id);
}
