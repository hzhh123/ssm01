package cn.edu.service;

import cn.edu.entity.Role;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface RoleService {
    Role get(Integer id);
    Role findOne(Integer id);
    void delete(Integer id);
    void save(Role role);
    void insert(Integer roleid,Integer permissionid);
    List<Integer>getPermissionids(Integer roleid);
    void update(Role role);
    void deleteBatch(List<Integer>ids);
    Page<Role> page(Integer pageIndex, Integer pageSize);
    Page<Role>page(Integer pageIndex, Integer pageSize, String keyword);
    List<Integer>getMenuids(Integer roleid);
}
