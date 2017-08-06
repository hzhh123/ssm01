package cn.edu.dao;

import cn.edu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/8/3.
 */
public interface MenuRepository extends JpaRepository<Menu,Integer>,JpaSpecificationExecutor<Menu> {
    @Modifying
    @Query("delete from Menu u where u.id in(:ids)")
    void deleteBatch(@Param("ids") List<Integer> ids);

    List<Menu>findMenusByStateAndIdIn(String state,List<Integer>ids);

    @Modifying
    @Query("update Menu m set m.name=?1,m.parentid=?2,m.url=?3,m.icon=?4,m.isheader=?5,m.order=?6,m.state=?7 where m.id=?8")
    void update(String name,Integer parentid,String url,String icon,String isheader,String order,String state,Integer id);

    List<Menu>findAllByName(String name);

}
