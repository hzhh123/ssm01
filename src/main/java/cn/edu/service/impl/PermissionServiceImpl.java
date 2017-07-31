package cn.edu.service.impl;

import cn.edu.dao.PermissionRepository;
import cn.edu.entity.Permission;
import cn.edu.service.PermissionService;
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
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    @Transactional
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }
    @Transactional
    public void delete(Integer id) {
        permissionRepository.delete(id);
    }
    @Transactional
    public void update(Permission permission) {
        permissionRepository.update(permission.getId(),permission.getParentId(),permission.getCode(),permission.getName(),
                permission.getUrl(),permission.getType(),permission.getJsEvent(),permission.getIcon(),permission.getSortCode()
        ,permission.getIsPublic(),permission.getIsEnable(),permission.getIsEdit(),permission.getRemark(),permission.getCreatetime(),permission.getModifytime());
    }
    @Transactional(readOnly = true)
    public Permission get(Integer id) {
        return null;
    }
    @Transactional(readOnly = true)
    public Page<Permission> page(Integer pageIndex, Integer pageSize) {
        PageRequest pageable=new PageRequest(pageIndex-1, pageSize);
        Page<Permission>page=permissionRepository.findAll(pageable);
        return page;
    }
    @Transactional(readOnly = true)
    public Page<Permission> page(Integer pageIndex, Integer pageSize, final String keyword) {
        Specification<Permission> specification=new Specification<Permission>() {
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String>name=root.get("name");
                Path<String>code=root.get("code");
                Predicate  k1=criteriaBuilder.like(name,"%"+keyword+"%");
                Predicate  k2=criteriaBuilder.like(code,"%"+keyword+"%");
                return criteriaBuilder.or(k1, criteriaBuilder.and(k2));
            }
        };
        PageRequest pageable=new PageRequest(pageIndex-1, pageSize);
        Page<Permission> page=permissionRepository.findAll(specification,pageable);
        return page;
    }
    @Transactional
    public void deleteBatch(List<Integer> ids) {
        permissionRepository.deleteBatch(ids);
    }
}
