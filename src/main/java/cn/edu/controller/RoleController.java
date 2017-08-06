package cn.edu.controller;

import cn.edu.entity.Role;
import cn.edu.service.RoleService;
import cn.edu.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequestMapping
    public String index(){
        return "common/role/index";
    }

    @RequestMapping("save")
    public @ResponseBody
    Map<String,Object> save(Role role){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            roleService.save(role);
            map.put("message","添加成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("delete")
    public @ResponseBody
    Map<String,Object>delete(Integer id){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            roleService.delete(id);
            map.put("message","删除成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("deleteBatch")
    public @ResponseBody
    Map<String,Object>deleteBatch(@RequestParam("ids[]")String ids){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            List<Integer> _ids= StringUtil.toList(ids);
            roleService.deleteBatch(_ids);
            map.put("message","删除成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("update")
    public @ResponseBody
    Map<String,Object>update(Role role){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            roleService.update(role);
            map.put("message","修改成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Role> list(Integer pageIndex, Integer pageSize, String keyword){
        if(!keyword.trim().equals("")){
            return roleService.page(pageIndex,pageSize,keyword);
        }
        return roleService.page(pageIndex,pageSize);
    }
    @ResponseBody
    @RequestMapping("get")
    public Role get(Integer id){
        return roleService.get(id);
    }
}