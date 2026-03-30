package com.carrot.service.impl;

import com.carrot.mapper.DeptMapper;
import com.carrot.pojo.Dept;
import com.carrot.service.DeptService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.getAllDept();
    }

    @Override
    public int deleteDeptById(Integer id) {
        return deptMapper.deleteDeptById(id);
    }

    @Override
    public int addDept(Dept dept) {
        dept.setCreateTime(new Date());
        dept.setUpdateTime(new Date());
        return deptMapper.addDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        dept.setUpdateTime(new Date());

        return deptMapper.updateDept(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {
        return deptMapper.getDeptById(id);
    }

}
