package com.userregistration.service;

import com.userregistration.dao.SecurityQADao;
import com.userregistration.dao.UserDao;
import com.userregistration.model.SecurityQA;
import com.userregistration.model.SecurityQARequest;
import com.userregistration.model.User;
import com.userregistration.model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityQADao securityQADao;

    public UserRequest createUser(UserRequest userReq) {
        User user = userReq.convertToUser();
        user = userDao.create(user);
        if (userReq.getSecurityQAList() != null && userReq.getSecurityQAList().size() == 2) {
            List<SecurityQA> qaList = new ArrayList<>();
            for (SecurityQARequest qa : userReq.getSecurityQAList()) {
                SecurityQA securityQA = qa.convertToSecurityQA();
                securityQA.setUser(user);
                qaList.add(securityQADao.create(securityQA));
            }
            user.setSecurityQAs(qaList);
        }
        return user.convertToUserRequest();
    }

    public UserRequest updateUser(UserRequest userReq) {
        User user = userReq.convertToUser();
        user = userDao.update(user);
        if (userReq.getSecurityQAList() != null && userReq.getSecurityQAList().size() == 2) {
            for (SecurityQARequest qa : userReq.getSecurityQAList()) {
                SecurityQA securityQA = qa.convertToSecurityQA();
                securityQA.setUser(user);
                securityQADao.update(securityQA);
            }
        }
        return userReq;
    }

    public UserRequest getUser(Integer userId) {
        return userDao.read(userId).convertToUserRequest();
    }

    public void deleteUser(Integer userId) {
        securityQADao.deleteSecurityQAsForUser(userId);
        userDao.delete(userId);
    }

    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userDao.findByUserName(username);
        GrantedAuthority auth = new SimpleGrantedAuthority(user.getRole().getRole());
        return buildUserForAuthentication(user, Arrays.asList(auth));
    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                          List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPswd(), true, true, true, true, authorities);
    }

}
