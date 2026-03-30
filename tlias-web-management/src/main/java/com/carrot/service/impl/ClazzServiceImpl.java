package com.carrot.service.impl;

import com.carrot.mapper.ClazzMapper;
import com.carrot.pojo.Clazz;
import com.carrot.pojo.Param.ClazzQueryParam;
import com.carrot.pojo.PageResult;
import com.carrot.service.ClazzService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRED)
    public PageResult<Clazz> list(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> clazzes = clazzMapper.list(clazzQueryParam);
        Page<Clazz> clazzPage = (Page<Clazz>) clazzes;
        return new PageResult<Clazz>(clazzPage.getTotal(), clazzPage.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override

    public void save(Clazz clazz) {
        clazz.setCreateTime(new Date());
        clazz.setUpdateTime(new Date());
        if (clazz.getMasterId() == null) {
            clazz.setMasterId(0);
        }
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getInfoById(Integer id) {
        return clazzMapper.getInfoById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(new Date());
        clazzMapper.update(clazz);
    }

    @Override
    public List<Clazz> allList() {
        return clazzMapper.allList();
    }
}
