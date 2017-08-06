package cn.edu.service.impl;

import cn.edu.dao.RoleRepository;
import cn.edu.entity.Permission;
import cn.edu.entity.Role;
import cn.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Transactional(readOnly = true)
    public Role get(Integer id) {
        return roleRepository.get(id);
    }
    @Transactional(readOnly = true)
    public Role findOne(Integer id) {
        return roleRepository.findOne(id);
    }
    @Transactional
    public void delete(Integer id) {
        roleRepository.delete(id);
    }
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }
    @Transactional
    public void insert(Integer roleid,Integer permissionid){
     roleRepository.insert(roleid,permissionid);
    }
    @Transactional(readOnly = true)
    public List<Integer> getPermissionids(Integer roleid) {
        return roleRepository.getPermissionIds(roleid);
    }
    @Transactional
    public void update(Role role) {
        roleRepository.update(role.getRolename(),role.getDesc(),role.getState(),role.getId());
    }
    @Transactional
    public void deleteBatch(List<Integer> ids) {
        roleRepository.deleteBatch(ids);
    }
    @Transactional(readOnly = true)
    public Page<Role> page(Integer pageIndex, Integer pageSize) {
        PageRequest pageable=new PageRequest(pageIndex-1, pageSize);
        Page<Role>page=roleRepository.findAll(pageable);
        return page;
    }
    @Transactional(readOnly = true)
    public Page<Role> page(Integer pageIndex, Integer pageSize, final String keyword) {
       Specification<Role>specification=new Specification<Role>() {
           public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               Path<String>rolename=root.get("rolename");
               Predicate  k1=criteriaBuilder.like(rolename,"%"+keyword+"%");
               return criteriaBuilder.and(k1);
           }
       };
        PageRequest pageable=new PageRequest(pageIndex-1, pageSize);
        Page<Role> page=roleRepository.findAll(specification,pageable);
        return page;
    }
    @Transactional(readOnly = true)
    public List<Integer> getMenuids(Integer roleid) {
        return roleRepository.getMenuids(roleid);
    }
}
