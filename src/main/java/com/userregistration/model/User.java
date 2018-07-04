package com.userregistration.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "username", unique = true, nullable = false, length = 30)
    String username;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    String email;

    @Column(name = "pswd", nullable = false)
    String pswd;

    @ManyToOne(targetEntity = Role.class, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(targetEntity = SecurityQA.class, mappedBy = "user")
    private List<SecurityQA> securityQAs;

    public User() {
    }

    public User(Integer userId, String username, String email, String pswd, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.pswd = pswd;
        this.role = role;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<SecurityQA> getSecurityQAs() {
        return securityQAs;
    }

    public void setSecurityQAs(List<SecurityQA> securityQAs) {
        this.securityQAs = securityQAs;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public UserVO convertToUserVO() {
        UserVO userVO = new UserVO();
        userVO.setUserId(this.userId);
        userVO.setUsername(this.username);
        userVO.setEmail(this.email);
        userVO.setPswd(this.pswd);
        userVO.setRoleId(this.getRole().getRoleId());
        if (this.securityQAs != null) {
            List<SecurityQAVO> qaList = new ArrayList<>(this.securityQAs.size());
            for (SecurityQA qa : this.securityQAs) {
                SecurityQAVO qaReq = new SecurityQAVO();
                qaReq.setQuestionId(qa.getQuestion().getQuestionId());
                qaReq.setAnswer(qa.getAnswer());
                qaList.add(qaReq);
            }
            userVO.setSecurityQAList(qaList);
        }
        return userVO;
    }
}
