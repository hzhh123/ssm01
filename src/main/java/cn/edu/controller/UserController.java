package cn.edu.controller;

import cn.edu.entity.User;
import cn.edu.service.UserService;
import cn.edu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Page<User>list(Integer pageIndex,Integer pageSize,String keyword){
        if(!keyword.trim().equals("")){
            return userService.page(pageIndex,pageSize,keyword);
        }
        return userService.page(pageIndex,pageSize);
    }
    @RequestMapping("index")
    public String index(){
        return "common/user/userlist";
    }
    @ResponseBody
    @RequestMapping("delete")
    public String delete(@RequestParam("ids[]") String ids){
        List<Integer>_ids= StringUtil.toList(ids);
        try{
            userService.deleteBatch(_ids);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("save")
    public Map<String,Object> save(User user){
        try{
            System.out.println(user.getUsername());
            Map<String,Object>map=new HashMap<String,Object>();
            if(userService.getUserByUsername(user.getUsername()).size()>0){
                map.put("message","用户已存在！");
                map.put("state","warning");
            }else {
                user.setCreatetime(new Timestamp(new Date().getTime()));
                userService.save(user);
                map.put("message","添加成功！");
                map.put("state","success");
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("update")
    public Map<String,Object> update(User user){
        try{
            Map<String,Object>map=new HashMap<String,Object>();
            if(userService.getUserByUsername(user.getUsername()).size()>1){
                map.put("message","用户已存在！");
                map.put("state","warning");
            }else {
                userService.update(user);
                map.put("message", "修改成功！");
                map.put("state", "success");
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    @ResponseBody
    @RequestMapping("updateState")
    public String updateState(Integer id,String state){
        try{
            userService.updateStatue(id,state);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("validUsername")
    public Map<String,Object>validUsername(String username){
        Map<String,Object>map=new HashMap<String,Object>();
        String message="用户名可用!";
        String state="success";
        List<User>uList=userService.getUserByUsername(username);
        if(uList.size()>0){
            message="用户名已存在！";
            state="warning";
        }
        map.put("message",message);
        map.put("state",state);
        return map;
    }

}
