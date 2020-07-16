package com.fosu.software.delivery.controller;

import com.fosu.software.delivery.service.impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @BelongsProject: delivery
 * @BelongsPackage: com.fosu.software.delivery.controller
 * @Author: Boss_king
 * @CreateTime: 2020-07-16 22:31
 * @Description: 员工管理controller
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeServiceImpl employeeService;
    @Autowired
    public void setEmployeeService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/select")
    public Object selectedEmployee(){
        return employeeService.selectedEmployee();
    }
    @GetMapping("/exit/{num}")
    public Object isExitNum(@PathVariable("num") String num){
        return employeeService.isExitNum(num);
    }
    @PostMapping("/insert")
    public Object insertEmployee(@RequestBody Map<String,String> map){
        return employeeService.insertEmployee(map);
    }
}
