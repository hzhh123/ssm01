package cn.edu.dao;

import cn.edu.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface PermissionRepository extends JpaRepository<Permission,Integer>,JpaSpecificationExecutor<Permission> {
    @Modifying
    @Query("delete from Permission u where u.id in(:ids)")
    void deleteBatch(@Param("ids") List<Integer> ids);
    @Modifying
    @Query("update Permission p set p.parentId=?1,p.code=?2,p.name=?3,p.url=?4,p.type=?5,p.icon=?6,p.jsEvent=?7," +
            "p.sortCode=?8,p.remark=?9,p.classname=?10,p.isPublic=?11,p.isEnable=?12,p.createtime=?13,p.modifytime=?14 where p.id=?15")
    void update(Integer parentId,String code,String name,String url,String type,String icon,String jsEvent,String sortCode,
                String remark,String classname,String isPublic,String isEnable,Timestamp createtime,Timestamp modifytime,Integer id);
    @Query(value = "select p from  Permission p where p.id in(?1)")
    List<Permission>getPermissions(List<Integer>permissionids);

    List<Permission>getPermissionByIsPublic(String isPublic);

    @Query("select p from Permission p where p.id in(?1)")
    List<Permission>findAllById(List<Integer>ids);

}
