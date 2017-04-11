package cn.edu.coffee.service.impl;

import cn.edu.coffee.mapper.UserMapper;
import cn.edu.coffee.model.User;
import cn.edu.coffee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findUser(User user) {
        return userMapper.findUserByProperties(user);
    }

    @Override
    public void addUser(User from) {

        userMapper.addUser(from);
    }

    @Override
    public void editUserLevel(User user) {
        userMapper.updateLevel(user);
    }

    @Override
    public void editUserPhone(User user) {

        userMapper.updatePhone(user);
    }

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }
}
