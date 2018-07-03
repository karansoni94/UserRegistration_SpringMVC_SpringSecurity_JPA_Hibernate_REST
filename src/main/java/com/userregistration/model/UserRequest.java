package com.userregistration.model;

import java.io.Serializable;
import java.util.List;

public class UserRequest implements Serializable {
    Integer userId;
    String username;
    String email;
    String pswd;
    Integer roleId;
    List<SecurityQARequest> securityQAList;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<SecurityQARequest> getSecurityQAList() {
        return securityQAList;
    }

    public void setSecurityQAList(List<SecurityQARequest> securityQAList) {
        this.securityQAList = securityQAList;
    }

    public User convertToUser() {
        User user = new User();
        user.setUserId(this.userId);
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPswd(this.pswd);
        Role role = new Role(this.roleId);
        user.setRole(role);
        return user;
    }
}
