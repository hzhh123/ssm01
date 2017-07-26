package cn.edu.dao;

import cn.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    @Modifying
    @Query("delete from User u where u.id in(:ids)")
    void deleteBatch(@Param("ids") List<Integer>ids);
    @Modifying
    @Query("update User u set u.username=:username,u.password=:password where u.id=:id")
    void update(@Param("username") String username,@Param("password")String password,@Param("id")Integer id);
}
