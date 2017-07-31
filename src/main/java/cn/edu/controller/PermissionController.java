package cn.edu.controller;

import cn.edu.entity.Permission;
import cn.edu.service.PermissionService;
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
 * Created by Administrator on 2017/7/31.
 */
@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @RequestMapping
    public String index(){
        return "common/permission/index";
    }

    @RequestMapping("save")
    public @ResponseBody
    Map<String,Object>save(Permission permission){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            permissionService.save(permission);
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
           permissionService.delete(id);
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
            List<Integer>_ids= StringUtil.toList(ids);
            permissionService.deleteBatch(_ids);
            map.put("message","删除成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("update")
    public @ResponseBody
    Map<String,Object>update(Permission permission){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            permissionService.update(permission);
            map.put("message","修改成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Permission>list(Integer pageIndex,Integer pageSize,String keyword){
        if(!keyword.trim().equals("")){
            return permissionService.page(pageIndex,pageSize,keyword);
        }
        return permissionService.page(pageIndex,pageSize);
    }
    @ResponseBody
    @RequestMapping("get")
    public Permission get(Integer id){
        return permissionService.get(id);
    }
}
