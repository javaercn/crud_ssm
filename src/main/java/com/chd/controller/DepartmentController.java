package com.chd.controller;

/**
 * @Auther:xue
 * @Date:2020/4/3 9:41
 */

import com.chd.bean.Department;
import com.chd.bean.Msg;
import com.chd.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门相关的请求
 */
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /**
     * 返回所有部门信息
     * @return
     */
    @RequestMapping("/depts")
    @ResponseBody//以json的形式
    public Msg getDepts(){
        //查出的所有奴们信息
        List<Department> list = departmentService.getDepts();
        return Msg.success().add("depts", list);
    }
}
