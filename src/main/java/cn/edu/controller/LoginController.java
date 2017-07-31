package cn.edu.controller;

import cn.edu.entity.User;
import cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/7/28.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("login")
    public @ResponseBody String login(HttpServletRequest request,User user){
        try {
            System.out.println(user.getUsername());
            User loginUser=userService.getUserByUsername(user.getUsername()).get(0);
            if (user.getPassword().trim().equals(loginUser.getPassword())) {
                request.getSession().setAttribute("loginUser",loginUser);
                return "success";
            }
            return "fail";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        return "login";
    }


}
