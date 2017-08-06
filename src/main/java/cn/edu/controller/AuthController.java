package cn.edu.controller;

import cn.edu.entity.Permission;
import cn.edu.entity.User;
import cn.edu.service.PermissionService;
import cn.edu.service.RoleService;
import cn.edu.service.UserService;
import cn.edu.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
public class AuthController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @ResponseBody
    @RequestMapping("auth")
    public List<Permission>auth(HttpServletRequest request){
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        List<Integer>roleids=userService.getRoleids(loginUser.getId());
        List<Integer>permissionids=new ArrayList<Integer>();
        List<Permission>permissionList=new ArrayList<Permission>();
        List<Permission>permissionResult=new ArrayList<Permission>();
        List<Permission>permissionByIsPublic=permissionService.getPermissionByIsPublic("1");
        if(permissionByIsPublic.size()>0){
            for (int i = 0; i <permissionByIsPublic.size() ; i++) {
                permissionids.add(permissionByIsPublic.get(i).getId());
            }
        }
        if(roleids.size()>0) {
            for (int i = 0; i < roleids.size(); i++) {
                if (roleService.getPermissionids(roleids.get(i)).size() > 0) {
                    permissionids.addAll(roleService.getPermissionids(roleids.get(i)));
                }
            }
        }
        if(permissionids.size()>0){
            permissionids=new CollectionUtil<Integer>().Ulist(permissionids);
            permissionList=permissionService.findAllById(permissionids);
        }
        if(permissionList.size()>0){
            for (int i = 0; i <permissionList.size(); i++) {
                if(permissionList.get(i).getIsEnable().equals("1")||permissionList.get(i).getIsPublic().equals("1")){
                    permissionResult.add(permissionList.get(i));
                }
            }
        }
        request.getSession().setAttribute("permissions",permissionResult);
        return permissionResult;
    }

    @RequestMapping("unAuth")
    public String unAuth(){
        return "unAuth";
    }
}
