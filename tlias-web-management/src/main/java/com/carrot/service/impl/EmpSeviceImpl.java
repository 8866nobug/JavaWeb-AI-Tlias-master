package com.carrot.service.impl;

import com.carrot.mapper.EmpExprMapper;
import com.carrot.mapper.EmpMapper;
import com.carrot.pojo.*;
import com.carrot.pojo.Param.EmpQueryParam;
import com.carrot.service.EmpLogService;
import com.carrot.service.EmpService;
import com.carrot.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpSeviceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

//    /**
//     * 原始分页查询员工数据
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        Long total = empMapper.count();
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<>(total, rows);
//    }

    /*
     * 基于pagehelper的分页查询员工数据
     * */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> empPage = (Page<Emp>) empList;
        return new PageResult<Emp>(empPage.getTotal(), empPage.getResult());
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void save(Emp emp) {
        try {
            emp.setCreateTime(new Date());
            emp.setUpdateTime(new Date());
            empMapper.insert(emp);

            List<EmpExpr> empExprs = emp.getExprList();
            if (!CollectionUtils.isEmpty(empExprs)) {
                empExprs.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });

                empExprMapper.insertBatch(empExprs);
            }
        } finally {
            // 无论成功还是失败都需要记录日志
            EmpLog empLog = new EmpLog(null, new Date(), "新增员工：" + emp);
            empLogService.insertLog(empLog);
        }


    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void delete(List<Integer> ids) {
        try {
            empExprMapper.deleteByEmpIds(ids);
            empMapper.deleteByIds(ids);
        } finally {
            EmpLog empLog = new EmpLog(null, new Date(), "删除员工：" + ids);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public Emp getInfo(Integer id) {
        try {
            Emp emp = empMapper.getInfo(id);
            return emp;
        } finally {
            EmpLog empLog = new EmpLog(null, new Date(), "查询员工：" + id);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public void update(Emp emp) {
        emp.setUpdateTime(new Date());
        empMapper.updateById(emp);
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp e = empMapper.selectByUsernameAndPassword(emp);
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String token = JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(), e.getUsername(), e.getName(), token);
        }
        return null;
    }

}
