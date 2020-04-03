package com.chd.test;

import com.chd.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * 使用spring提供的测试模块提供的测试请求功能，测试crud的准确性
 * @Auther:xue
 * @Date:2020/4/1 16:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration//用与在spring中测试springmvc，引入soringmvc的ioc
@ContextConfiguration(locations = {"classpath:applicationContext.xml","file:D:\\javaprogram\\ssm\\crud_ssm\\src\\main\\webapp\\WEB-INF\\dispatcherServlet-servlet.xml"})
public class MvcTest {
    //传入springMvc的ioc
    @Autowired
    WebApplicationContext context;
    //虚拟mvc请求，获取处理结果
    MockMvc mockMvc;

    @Before
    public void initMockMvc(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void testPage() throws Exception {
        //模拟请求拿到返回值
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
        //请求成功以后，请求域中会有pageinfo,我们乐意取出pageInfo
        MockHttpServletRequest request = result.getRequest();
        PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
        System.out.println("当前页码"+pageInfo.getPageNum());
        System.out.println("总页码"+pageInfo.getPages());
        System.out.println("总记录数"+pageInfo.getTotal());
        System.out.println("页面需要连续显示的页码");
        int[] navigatepageNums = pageInfo.getNavigatepageNums();
        for(int i :navigatepageNums){
            System.out.println(" "+i);
        }
        //获取员工数据
        List<Employee> list =pageInfo.getList();
        for(Employee employee:list){
            System.out.println(employee);
        }

    }

}
