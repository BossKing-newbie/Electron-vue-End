package com.fosu.software.delivery.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.dao
 * @Author: Boss_king
 * @CreateTime: 2020-07-16 22:23
 * @Description: 员工管理Mapper
 */
@Repository
public interface EmployeeMapper {
    // 返回员工列表
    public List<Map> selectedEmployee();
    // 查询工号是否存在
    public int isExitNum(String num);
    // 插入employee表
    public int insertEmployee(Map<String,String> map);
}
