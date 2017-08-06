package cn.edu.service.impl;

import cn.edu.dao.MenuRepository;
import cn.edu.entity.Menu;
import cn.edu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;
    @Transactional
    public void save(Menu menu) {
        menuRepository.save(menu);
    }
    @Transactional
    public void delete(Integer id) {
        menuRepository.delete(id);
    }
    @Transactional
    public void deleteBatch(List<Integer> ids) {
        menuRepository.deleteBatch(ids);
    }
    @Transactional
    public void update(Menu menu) {
        menuRepository.update(menu.getName(),menu.getParentid(),menu.getUrl(),menu.getIcon(),menu.getIsheader(),menu.getOrder(),menu.getState(),menu.getId());
    }
    @Transactional(readOnly = true)
    public Menu get(Integer id) {
        return menuRepository.findOne(id);
    }
    @Transactional(readOnly = true)
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Page<Menu> page(Integer pageIndex, Integer pageSize) {
        PageRequest pageRequest=new PageRequest(pageIndex-1,pageSize);
        return menuRepository.findAll(pageRequest);
    }
    @Transactional(readOnly = true)
    public Page<Menu> page(Integer pageIndex, Integer pageSize, final String keyword) {
        Specification<Menu>specification=new Specification<Menu>() {
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path<String>rolename=root.get("name");
                Predicate  k1=criteriaBuilder.like(rolename,"%"+keyword+"%");
                return criteriaBuilder.and(k1);
            }
        };
        PageRequest pageable=new PageRequest(pageIndex-1, pageSize);
        Page<Menu> page=menuRepository.findAll(specification,pageable);
        return page;
    }
    @Transactional(readOnly = true)
    public List<Menu> findMenusByStateAndIdIn(String state, List<Integer> ids) {
        return menuRepository.findMenusByStateAndIdIn(state,ids);
    }
    @Transactional(readOnly = true)
    public List<Menu> findAllByName(String name) {
        return menuRepository.findAllByName(name);
    }
}
