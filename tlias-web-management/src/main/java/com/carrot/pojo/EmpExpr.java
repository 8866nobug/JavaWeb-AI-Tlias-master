package com.carrot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

/**
 * 工作经历
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmpExpr {
    private Integer id; //ID
    private Integer empId; //员工ID
    private Date begin; //开始时间
    private Date end; //结束时间
    private String company; //公司名称
    private String job; //职位
}
