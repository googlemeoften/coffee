package cn.edu.coffee.model;

/**
 * Created by HeYong on 2017/3/24.
 */
public class Admin {
    private int adminId;
    private String adminName;
    private String adminContact;
    private int adminWork;
    private int adminLevel;
    private int adminSalary;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(String adminContact) {
        this.adminContact = adminContact;
    }

    public int getAdminWork() {
        return adminWork;
    }

    public void setAdminWork(int adminWork) {
        this.adminWork = adminWork;
    }

    public int getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(int adminLevel) {
        this.adminLevel = adminLevel;
    }

    public int getAdminSalary() {
        return adminSalary;
    }

    public void setAdminSalary(int adminSalary) {
        this.adminSalary = adminSalary;
    }
}
