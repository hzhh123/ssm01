package cn.edu.service.impl;

import cn.edu.dao.UserRepository;
import cn.edu.entity.User;
import cn.edu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Transactional(readOnly = true)
    public Page<User> page(Integer pageNo, Integer pageSize) {
        PageRequest pageable=new PageRequest(pageNo-1, pageSize);
        Page<User> page=userRepository.findAll(pageable);
        return page;
    }
}
