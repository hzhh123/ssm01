package cn.edu.service;

import cn.edu.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserService {
    User get(Integer id);
    Page<User>page(Integer pageNo,Integer pageSize);
    Page<User>page(Integer pageNo,Integer pageSize,String keyword);
    void delete(Integer id);
    void deleteBatch(List<Integer> ids);
    void save(User user);
    void update(User user);
    void updateStatue(Integer id,String state);
    List<User> getUserByUsername(String username);
    void insert(Integer userid,Integer roleid);
    List<Integer>getRoleids(Integer userid);

}
