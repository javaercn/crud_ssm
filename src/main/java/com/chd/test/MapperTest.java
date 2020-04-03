package com.chd.test;

/**
 * @Auther:xue
 * @Date:2020/4/1 10:58
 */

import com.chd.bean.Department;
import com.chd.bean.Employee;
import com.chd.dao.DepartmentMapper;
import com.chd.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.UUID;

/**
 * 测试dao层的工作
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MapperTest {
    /**
     * 测试departmentMapper
     * 推荐spring的项目使用spring的单元测试，可以自动注入我们需要的组件
     */
    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper  employeeMapper;

    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){
//        //创建spring容器
//        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
//        //获取对象
//        DepartmentMapper bean = applicationContext.getBean(DepartmentMapper.class);



        //1.插入几个部门
 //       departmentMapper.insertSelective(new Department(null,"开发部"));
  //      departmentMapper.insertSelective(new Department(null,"测试部"));

        //2.生成员工数据，测试员工插入
       // employeeMapper.insertSelective(new Employee(null,"jenny","m","154@qq.com",1));

        //3.批量插入多个员工，使用可以执行批量操作的sqlSession
        /*
        for(){
            employeeMapper.insertSelective(new Employee(null,"jenny","m","154@qq.com",1));
        }
        */
        //获取能执行批量操作的sqlSession
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for(int i=0;i<1000;i++){
            String uid = UUID.randomUUID().toString().substring(0, 5)+" "+i;
            mapper.insertSelective(new Employee(null,uid,"m",uid+"@qq.com",1));
        }
        System.out.println("批量完成");


    }
}
