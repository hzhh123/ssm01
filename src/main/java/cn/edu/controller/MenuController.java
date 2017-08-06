package cn.edu.controller;

import cn.edu.entity.Menu;
import cn.edu.entity.User;
import cn.edu.service.MenuService;
import cn.edu.service.RoleService;
import cn.edu.service.UserService;
import cn.edu.util.CollectionUtil;
import cn.edu.util.StringUtil;
import cn.edu.util.TreeNode;
import cn.edu.util.TreeNodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/1.
 */
@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @RequestMapping
    public String index(){
        return "common/menu/index";
    }

    @RequestMapping("save")
    public @ResponseBody
    Map<String,Object> save(Menu menu){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            menuService.save(menu);
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
            menuService.delete(id);
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
            menuService.deleteBatch(_ids);
            map.put("message","删除成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("update")
    public @ResponseBody
    Map<String,Object>update(Menu menu){
        Map<String,Object>map=new HashMap<String,Object>();
        try{
            menuService.update(menu);
            map.put("message","修改成功！");
            map.put("state","success");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("list")
    @ResponseBody
    public Page<Menu> list(Integer pageIndex, Integer pageSize, String keyword){
        if(!keyword.trim().equals("")){
            return menuService.page(pageIndex,pageSize,keyword);
        }
        return menuService.page(pageIndex,pageSize);
    }
    @ResponseBody
    @RequestMapping("get")
    public Menu get(Integer id){
        return menuService.get(id);
    }

    @ResponseBody
    @RequestMapping("findAll")
    public List<Menu>findAll(){
        return menuService.findAll();
    }

    @ResponseBody
    @RequestMapping("getMenus")
    public List<TreeNode> getMenus(HttpServletRequest request){
        User loginUser=(User)request.getSession().getAttribute("loginUser");
        List<Integer>roleids=userService.getRoleids(loginUser.getId());
        List<Integer>menuids=new ArrayList<Integer>();
        List<Menu>menus=new ArrayList<Menu>();
        if(roleids.size()>0){
            for(Integer roeid:roleids){
                menuids.addAll(roleService.getMenuids(roeid));
            }
        }
        if(menuids.size()>0){
            menuids=new CollectionUtil<Integer>().Ulist(menuids);
            menus=menuService.findMenusByStateAndIdIn("1",menuids);
            List<TreeNode>nodes=TreeNodeUtil.toListNode(menus);
            return TreeNodeUtil.tree(nodes,"0");
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("getForlist")
    public  List<Map<String,Object>> getForlist(@RequestParam(value="q",defaultValue ="" )String q
    )throws Exception{
        //转换编码,get请求参数中包含中文，处理中文
        q = new String(q.getBytes("iso-8859-1"), "utf-8");
        //在前台输入空格，去空格处理后为空字符串
        q=q.trim();
        List<Map<String,Object>>list=new ArrayList<Map<String, Object>>();
        //selectByProvinceName函数是根据模糊查询得到的
        List<Menu>menus=new ArrayList<Menu>();
        if(q.equals("")){
            menus=menuService.findAll();
        }else{
            menus= menuService.findAllByName(q);
        }
        Map<String,Object>map=null;
        //返回的json格式必须是如{id:1，text:"北京市"}这样的格式select2才能解析
        for (int i=0;i<menus.size();i++){
            map=new HashMap<String, Object>();
            map.put("id",menus.get(i).getId());
            map.put("text",menus.get(i).getName());
            list.add(map);
        }
        return list;
    }

}