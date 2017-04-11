package cn.edu.coffee.mapper;

import cn.edu.coffee.model.Admin;

import java.util.List;

/**
 * Created by HeYong on 2017/3/24.
 */
public interface AdminMapper {
    public List<Admin> selectAllAdmin();

    public void insertAdmin(Admin admin);

    public void editAdmin(Admin admin);
}
