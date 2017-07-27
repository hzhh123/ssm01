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
    public Page<User>list(Integer pageIndex,Integer pageSize){
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
            userService.save(user);
            map.put("message","添加成功！");
            map.put("state","success");
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
            userService.update(user);
            map.put("message","修改成功！");
            map.put("state","success");
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

}
