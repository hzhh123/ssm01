package cn.edu.dao;

import cn.edu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
