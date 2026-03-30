package com.carrot.mapper;

import com.carrot.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Carrot
 * @create 2022-05-05-16:05
 * 员工工作经历
 */

@Mapper
public interface EmpExprMapper {

    public void insertBatch(List<EmpExpr> empExprs);

    void deleteByEmpIds(List<Integer> empIds);
}
