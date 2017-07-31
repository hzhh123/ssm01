package cn.edu.service;

import cn.edu.entity.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface PermissionService {
    void save(Permission permission);
    void delete(Integer id);
    void deleteBatch(List<Integer>ids);
    void update(Permission permission);
    Permission get(Integer id);
    Page<Permission>page(Integer pageIndex,Integer pageSize);
    Page<Permission>page(Integer pageIndex,Integer pageSize,String keyword);
}
