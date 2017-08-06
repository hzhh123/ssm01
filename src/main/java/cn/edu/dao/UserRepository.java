package cn.edu.dao;

import cn.edu.entity.Role;
import cn.edu.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    @Modifying
    @Query("delete from User u where u.id in(:ids)")
    void deleteBatch(@Param("ids") List<Integer>ids);
    @Modifying
    @Query("update User u set u.username=:username,u.password=:password,u.state=:state,u.updatetime=:updatetime where u.id=:id")
    void update(@Param("state")String state, @Param("username") String username, @Param("password")String password, @Param("id")Integer id, @Param("updatetime")Timestamp updatetime);
    @Modifying
    @Query("update User u set u.state=:state where u.id=:id")
    void updateStatue(@Param("id") Integer id,@Param("state") String state);
    @Query("select u from User u where u.username=:username")
    List<User> getUserByUsername(@Param("username") String username);
    @Modifying
    @Query(value = "INSERT INTO user_role(userid,roleid)VALUES (?1,?2)",nativeQuery = true)
    void insert(Integer userid,Integer roleid);
    @Query(value = "select roleid from user_role where userid=?1",nativeQuery = true)
    List<Integer>getRoleids(Integer userid);
}
