package cn.edu.service;

import cn.edu.entity.Menu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface MenuService {
    void save(Menu menu);
    void delete(Integer id);
    void deleteBatch(List<Integer>ids);
    void update(Menu menu);
     Menu get(Integer id);
     List<Menu>findAll();
     Page<Menu>page(Integer pageIndex,Integer pageSize);
     Page<Menu>page(Integer pageIndex,Integer pageSize,String keyword);
     List<Menu>findMenusByStateAndIdIn(String state,List<Integer>ids);
    List<Menu>findAllByName(String name);

}
