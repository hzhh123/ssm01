package cn.edu.dao;

import cn.edu.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/31.
 */
public interface RoleRepository extends JpaRepository<Role,Integer>,JpaSpecificationExecutor<Role> {
    @Query("select r from Role r where r.id=:id")
    Role get(@Param("id")Integer id);
    @Modifying
    @Query(value = "insert into role_permission(roleid,permissionid)values(?1,?2)",nativeQuery = true)
    void insert(Integer roleid,Integer permissionid);
    @Query("select r from Role r where  r.id in (?1)")
    List<Role>getRoles(List<Integer>roleids);

    @Query(value = "select permissionid from role_permission where roleid=?1",nativeQuery = true)
    List<Integer>getPermissionIds(Integer roleid);

    @Modifying
    @Query("delete from Role r where r.id in(:ids)")
    void deleteBatch(@Param("ids") List<Integer> ids);

    @Modifying
    @Query("update Role r set r.rolename=?1,r.desc=?2,r.state=?3 where  r.id" +
            "=?4")
    void update(String rolename,String desc,String state,Integer id);

    @Modifying
    @Query(value = "delete from role_menu ru where ru.roleid=?1",nativeQuery = true)
    void deleteRoleMenu(Integer roleid);

    @Modifying
    @Query(value = "insert into role_menu(roleid,menuid) values(1?,?2)",nativeQuery = true)
    void insertRoleMenu(Integer roleid,Integer menuid);

    @Query(value = "SELECT r.menuid from role_menu r where r.roleid=?1",nativeQuery = true)
    List<Integer>getMenuids(Integer roleid);

}
