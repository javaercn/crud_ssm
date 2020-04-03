package com.chd.controller;

import com.chd.bean.Employee;
import com.chd.bean.EmployeeExample;
import com.chd.bean.Msg;
import com.chd.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @Auther:xue
 * @Date:2020/4/2 10:20
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;


    /**
     * 单个批量二合一
     * 批量删除；1-2-3
     * 单个删除 2
     * @param ids
     * @return
     */
    @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteEmp(@PathVariable("ids")String ids){
        if(ids.contains("-")){
            List<Integer> del_ids = new ArrayList<>();
            String[] str_ids = ids.split("-");
            for(String string : str_ids){
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
        }else {
            Integer id = Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }
    /**
     * 员工更新
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{emplId}",method = RequestMethod.PUT)
    public Msg SaveEmp(Employee employee){
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /*
    Rest风格
    /emp/{id} get 查询员工
    /emp      post保存员工
    /emp/{id} put修改员工
    /emp/{id} delete删除员工
     */
    @RequestMapping(value ="/emp",method = RequestMethod.POST)//URI风格
    @ResponseBody
    public Msg saveEmp(Employee employee){
        employeeService.saveEmp(employee);
        return Msg.success();
    }


    /**
     * 需要倒入json的包
     * @param pn
     * @return
     */
    @RequestMapping("/emps")
    @ResponseBody//将对象转换成json形式的数据
    public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        //这不是一个分页查询
        //引入PageHelper分页插件
        //在查询之前，只需要调用,传入页码和分页
        PageHelper.startPage(pn,5);
        //startpage后面紧跟的就是分页查询
        List<Employee> emps=employeeService.getAll();
        //使用pageInfo包装查询后的结果，值需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括我们查出来的数据,连续显示的页数
        PageInfo pageInfo = new PageInfo(emps,5);
        return Msg.success().add("pageInfo", pageInfo);

    }


    /**
     * 按照员工id查询
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }



    /**
     * 查询员工数据，分页查询
     * @return
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn, Model model){
        //这不是一个分页查询
        //引入PageHelper分页插件
        //在查询之前，只需要调用,传入页码和分页
        PageHelper.startPage(pn,5);
        //startpage后面紧跟的就是分页查询
        List<Employee> emps=employeeService.getAll();
        //使用pageInfo包装查询后的结果，值需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括我们查出来的数据,连续显示的页数
        PageInfo pageInfo = new PageInfo(emps,5);
        model.addAttribute("pageInfo",pageInfo);
        return "list";
    }
}
