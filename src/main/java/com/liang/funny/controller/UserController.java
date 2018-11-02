package com.liang.funny.controller;

import com.liang.funny.model.User;
import com.liang.funny.service.UserService;
import com.liang.funny.util.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 新增用户
     * @param map
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public JsonResult addUser(@RequestBody Map<String, Object> map){
        userService.addUser(map);
        return new JsonResult(200, true,"新增用户成功");
    }

    //这里体现了restful风格的请求，按照请求的类型，来进行增删查改。
    //设计restful api（其实也就是URL），不要有冗余，例如不要写成getUsers，URL中
    //最好不要有动词。
    //这里用的是路径变量，就是{}括起来的，会当做变量读进来
    //RequestBody这个注解可以接收json数据
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET, produces = "application/json")
    public User getUserBy(@PathVariable("id")int userId){
        User user = userService.getUser(userId);
        return  user;

    }

    @RequestMapping(value = "/get",method = RequestMethod.GET, produces = "application/json")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.DELETE)
    public boolean delUserBy(@PathVariable("id")int userId){
        userService.delUser(userId);
        return true;
    }

    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public boolean updateUser(@PathVariable("id") Integer id, @RequestBody Map<String, Object> map){
        userService.updateUser(id, map);
        return true;
    }
}
