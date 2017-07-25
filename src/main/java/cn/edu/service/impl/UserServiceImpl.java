package cn.edu.service.impl;

import cn.edu.dao.UserRepository;
import cn.edu.entity.User;
import cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2017/7/21.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    public User get(Integer id) {
        return userRepository.findOne(id);
    }
}
