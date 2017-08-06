import cn.edu.dao.MenuRepository;
import cn.edu.dao.PermissionRepository;
import cn.edu.dao.UserRepository;
import cn.edu.entity.Menu;
import cn.edu.entity.Permission;
import cn.edu.entity.Role;
import cn.edu.entity.User;
import cn.edu.service.MenuService;
import cn.edu.service.PermissionService;
import cn.edu.service.RoleService;
import cn.edu.service.UserService;
import cn.edu.util.CollectionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Test1 {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuRepository menuRepository;
    @Test
    public void test1()throws SQLException{
        System.out.println(dataSource.getConnection());
    }
    @Test
    public void test2(){
        int pageNum=1-1;//当前页
        int pageSize=5;//分页大小
        PageRequest pageable=new PageRequest(pageNum, pageSize);
        Page<User> page=userRepository.findAll(pageable);
        System.out.println("当前页"+(page.getNumber()+1));
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("当前页记录数"+page.getNumberOfElements());
        System.out.println("总记录数"+page.getTotalElements());
        System.out.println("当前页元素："+page.getContent());
    }
    @Test
    public void test3(){
        List<Integer>ids=new ArrayList<Integer>();
        ids.add(3);
        ids.add(4);
        userService.deleteBatch(ids);
    }
    @Test
    public void test4(){
       User user=userService.get(1);
       user.setUsername("dddxxx");
       userService.update(user);
    }
    @Test
    public void test5(){
        User user=userService.getUserByUsername("aa").get(0);
        System.out.println(user);
    }
    @Test
    public void test6(){
       Page<User>page=userService.page(1,5,"a");
        System.out.println(page.getTotalElements());
    }
    @Test
    public void test7(){
       Permission permission=permissionService.get(1);
       permission.setIcon("fa fa-user");
        permissionService.update(permission);
    }
    @Test
    public void test8(){
        int pageNum=1;//当前页
        int pageSize=5;//分页大小
        Page<Permission> page=permissionService.page(pageNum,pageSize);
        System.out.println("当前页"+(page.getNumber()+1));
        System.out.println("总页数"+page.getTotalPages());
        System.out.println("当前页记录数"+page.getNumberOfElements());
        System.out.println("总记录数"+page.getTotalElements());
        System.out.println("当前页元素："+page.getContent());
    }

    @Test
    public void test9(){
        User user=userService.get(1);
        System.out.println(user);
    }
    @Test
    public void test10(){
       userService.delete(1);
    }

    //分配角色
    @Test
    public void test11(){
      userService.insert(1,1);
    }
    @Test
    public void test12(){
        List<Integer>roleids=userService.getRoleids(1);
        List<Integer>permissionids=new ArrayList<Integer>();
        if(roleids.size()>0){
            for (int i=0;i<roleids.size();i++){
                permissionids.addAll(roleService.getPermissionids(roleids.get(i)));
            }

            List<Integer>allpermissionids=new CollectionUtil<Integer>().Ulist(permissionids);
            for (Integer id:allpermissionids){
                System.out.println(id);
            }
            List<Permission>permissions=permissionService.getPermissions(allpermissionids);
            System.out.println(permissions.size());
        }
    }

    @Test
    public void test13(){
        List<Integer>ids=new ArrayList<Integer>();
        ids.add(1);
        List<Menu>menus=menuRepository.findMenusByStateAndIdIn("1",ids);
        System.out.println(menus.size());
    }
    @Test
    public void test14(){
        Menu menu=menuRepository.findOne(1);
        System.out.println(menu);
    }
}
