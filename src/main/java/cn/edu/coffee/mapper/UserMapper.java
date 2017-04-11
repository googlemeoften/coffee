package cn.edu.coffee.mapper;

import cn.edu.coffee.model.User;

import java.util.List;

/**
 * Created by HeYong on 2017/3/13.
 */
public interface UserMapper {

    //通过用户名进行查找
    public User findUserByProperties(User user);

    //添加用户
    public void addUser(User form);

    public void updatePassword(String password);


    public void updateLevel(User user);

    public void updatePhone(User user);

    //删除用户
    public void deleteUser(int uid);

    //查询所有用户
    public List<User> findAllUser();
}
