package cn.edu.coffee.service;

import cn.edu.coffee.model.User;

import java.util.List;

public interface UserService {

    public User findUser(User user);

    public void addUser(User from);

    //修改会员等级
    public void editUserLevel(User user);

    //修改phone
    public void editUserPhone(User user);

    public List<User> findAllUser();

}
