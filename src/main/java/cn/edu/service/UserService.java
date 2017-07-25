package cn.edu.service;

import cn.edu.entity.User;
import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2017/7/21.
 */
public interface UserService {
    User get(Integer id);
    Page<User>page(Integer pageNo,Integer pageSize);

}
