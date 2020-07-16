package com.fosu.software.delivery.service.impl;

import com.fosu.software.delivery.dao.EmployeeMapper;
import com.fosu.software.delivery.resultFormat.ResultUtils;
import com.fosu.software.delivery.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.service.impl
 * @Author: Boss_king
 * @CreateTime: 2020-07-16 22:29
 * @Description: 员工管理业务层实现类
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {

    private EmployeeMapper employeeMapper;
    @Autowired
    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public Object selectedEmployee() {
        try{
            return ResultUtils.success(employeeMapper.selectedEmployee());
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object isExitNum(String num) {
        try {
            int count = employeeMapper.isExitNum(num);
            // 即不存在工号冲突
            if(count == 0){
                return ResultUtils.success();
            }else{
                return ResultUtils.fail(500,"工号已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }

    @Override
    public Object insertEmployee(Map<String, String> map) {
        try{
            int code = employeeMapper.insertEmployee(map);
            if (code > 0) {
                return ResultUtils.success();
            }else{
                return ResultUtils.fail(500,"插入失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResultUtils.fail(400,e.getLocalizedMessage());
        }
    }
}
