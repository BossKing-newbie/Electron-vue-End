package com.fosu.software.delivery.service;

import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service
 * @Author: Boss_king
 * @CreateTime: 2020-07-16 22:28
 * @Description: 员工管理业务层
 */
public interface IEmployeeService {
    // 返回员工信息
    public Object selectedEmployee();
    // 工号是否存在
    public Object isExitNum(String num);
    // 插入employee表
    public Object insertEmployee(Map<String,String> map);
}
