package cn.edu.coffee.model;

/**
 * Created by HeYong on 2017/3/13.
 */
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String phone;
    private int level;

    public Integer getUserId() {
        return userId;
    }

    public User setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public User setLevel(int level) {
        this.level = level;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", level=" + level +
                '}';
    }
}
