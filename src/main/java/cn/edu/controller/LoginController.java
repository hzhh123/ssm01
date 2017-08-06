package cn.edu.controller;

import cn.edu.entity.User;
import cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
            List<User> users=userService.getUserByUsername(user.getUsername());
            if(users.size()>0) {
                User loginUser=users.get(0);
                if (user.getPassword().trim().equals(loginUser.getPassword())) {
                    request.getSession().setAttribute("loginUser", loginUser);
                    return "1";
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("loginUser");
        request.getSession().removeAttribute("permissions");
        return "login";
    }


}
