package com.userregistration.model;

import java.io.Serializable;
import java.util.List;

public class UserVO implements Serializable {
    Integer userId;
    String username;
    String email;
    String pswd;
    Integer roleId;
    List<SecurityQAVO> securityQAList;

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

    public List<SecurityQAVO> getSecurityQAList() {
        return securityQAList;
    }

    public void setSecurityQAList(List<SecurityQAVO> securityQAList) {
        this.securityQAList = securityQAList;
    }

    public User convertToUser() {
        Role role = new Role(this.roleId);
        return new User(this.userId,this.username,this.email,this.pswd, role);
    }
}
