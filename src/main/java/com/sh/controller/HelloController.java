package com.sh.controller;

import com.sh.model.Users;
import com.sh.service.UserService;
import com.sh.utils.JsonUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

//@RestController
//暂时不用controller
@Controller
@RequestMapping("/hello")
public class HelloController {
    private List<Users> users = new ArrayList<Users>();
    @Resource
    private UserService userService;

    {
        users.add(new Users(1L, "张三", "北京", new Date()));
        users.add(new Users(2L, "张三", new Date()));

    }

    @RequestMapping("/test")
    public String hello() {

        System.out.println("进入页面");
        return "/hello/test";
    }

    @RequestMapping("/test1/id/{id}/u/{userName}")
    public void test1(@PathVariable Long id, @PathVariable String userName) {
        System.out.println("id=sdd" + id + "name=" + userName);

    }

    @RequestMapping("/json1")
    public String showJasonDemoPage1() {
        System.out.println("进入jsonDemoPage");
        return "jsonDemo1";
    }

    @RequestMapping("/add")
    public
    @ResponseBody
    Integer sum(Integer inputNumber1, Integer inputNumber2) {
        System.out.println("进入jsonDemoPage" + (inputNumber1 + inputNumber2));
        return (inputNumber1 + inputNumber2);
    }

    @RequestMapping("/getUser/{id}")
    public
    @ResponseBody
    Users getUserById(@PathVariable Integer id) {
        Map<Integer, Users> u = new HashedMap();
        for (int i = 0; i < users.size(); i++) {
            u.put(i, users.get(i));
        }
        System.out.println("#id==" + id);

        return users.get(id);
    }

    @RequestMapping("/userList")
    public
    @ResponseBody
    List<Users> getUserList() {
        System.out.println(JsonUtil.toJson(users));
        return users;
    }

    @RequestMapping("/getUserMap")
    public
    @ResponseBody
    Map<Integer, Users> getUserMap() {
        Map<Integer, Users> u = new HashedMap();
        for (int i = 0; i < users.size(); i++) {
            u.put(i, users.get(i));
        }

        return u;

    }

    //保存json对象
    //注意Users如果有构造方法使用，必须还要有默认的构造方法
    @RequestMapping("/saveUser")
    public
    @ResponseBody
    String saveUser(Users u) {

        System.out.println("user" + u.getName());
        return "success";
    }

    @RequestMapping("/saveJsonUser")
    public
    @ResponseBody
    String saveJsonUser(Users u) {

        System.out.println("user" + u.getName());
        return "success";
    }

    @RequestMapping("/saveJsonUserList")
    public
    @ResponseBody
    String saveJsonUserList(List<Users> uList) {
        for (Users u : uList) {
            System.out.println("user" + u.getName());
        }

        return "success";
    }


    @RequestMapping("/taobao")
    public
    @ResponseBody
    String testTaobao() {

        System.out.println("可以回调");
        return "success";
    }

}
