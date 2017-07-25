package cn.edu.controller;

import cn.edu.entity.User;
import cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/7/21.
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("get")
    public User get(Integer id){
        return userService.get(id);
    }

    @ResponseBody
    @RequestMapping("list")
    public Page<User>list(Integer pageNo,Integer pageSize){
        return userService.page(pageNo,pageSize);
    }
    @RequestMapping("index")
    public String index(){
        return "common/user/userlist2";
    }
}
