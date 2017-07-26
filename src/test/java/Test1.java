import cn.edu.dao.UserRepository;
import cn.edu.entity.User;
import cn.edu.service.UserService;
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
}
