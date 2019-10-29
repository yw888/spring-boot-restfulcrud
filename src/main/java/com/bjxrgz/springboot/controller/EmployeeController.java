package com.bjxrgz.springboot.controller;

import com.bjxrgz.springboot.dao.DepartmentDao;
import com.bjxrgz.springboot.dao.EmployeeDao;
import com.bjxrgz.springboot.entities.Department;
import com.bjxrgz.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    DepartmentDao departmentDao;
    
    /**
     * 查询所欲员工列表
     * @return
     */
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> all = employeeDao.getAll();
        model.addAttribute("emps", all);
        return "emp/list";
    }

    /**
     * 来到员工添加页面
     * @return
     */
    @GetMapping(value = "/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    /**
     * 员工添加
     * @return
     */
    @PostMapping(value = "/emp")
    public String addEmp(Employee employee){
        System.out.println("保存的员工信息:" + employee);
        employeeDao.save(employee);
        //redirect:表示重定向到页面 /代表当前项目路径
        //forward: 表示转发到页面
        return "redirect:/emps";
    }

    @GetMapping(value = "emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);
        return "emp/add";
    }
}
