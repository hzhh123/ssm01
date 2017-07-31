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
    @Query("update Permission p set p.parentId=:parentId,p.code=:code,p.icon=:icon,p.isEdit=:isEdit,p.isPublic=:isPublic," +
            "p.isEnable=:isEnable,p.sortCode=:sortCode,p.name=:name,p.createtime=:createtime,p.modifytime=:modifytime,p.jsEvent=:jsEvent," +
            "p.type=:type,p.url=:url,p.remark=:remark where p.id=:id")
    void update(@Param("id")Integer id, @Param("parentId")Integer parentId, @Param("code")String code,
                @Param("name")String name, @Param("url")String url, @Param("type")String type, @Param("jsEvent")String jsEvent,
                @Param("icon")String icon, @Param("sortCode")String sortCode, @Param("isPublic")boolean isPublic,
                @Param("isEnable")boolean isEnable, @Param("isEdit")boolean isEdit,
                @Param("remark")String remark, @Param("createtime")Timestamp createtime,@Param("modifytime")Timestamp modifytime);
}
