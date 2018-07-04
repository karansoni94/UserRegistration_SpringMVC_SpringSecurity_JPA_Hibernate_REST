package com.userregistration.service;

import com.userregistration.dao.SecurityQADao;
import com.userregistration.dao.UserDao;
import com.userregistration.model.SecurityQA;
import com.userregistration.model.SecurityQAVO;
import com.userregistration.model.User;
import com.userregistration.model.UserVO;
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
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityQADao securityQADao;

    public UserVO createUser(UserVO userReq) {
        User user = userReq.convertToUser();
        user = userDao.create(user);
        if (userReq.getSecurityQAList() != null && userReq.getSecurityQAList().size() == 2) {
            List<SecurityQA> qaList = new ArrayList<>();
            for (SecurityQAVO qa : userReq.getSecurityQAList()) {
                SecurityQA securityQA = qa.convertToSecurityQA();
                securityQA.setUser(user);
                qaList.add(securityQADao.create(securityQA));
            }
            user.setSecurityQAs(qaList);
        }
        return user.convertToUserVO();
    }

    public UserVO updateUser(UserVO userReq) {
        User user = userReq.convertToUser();
        user = userDao.update(user);
        if (userReq.getSecurityQAList() != null && userReq.getSecurityQAList().size() == 2) {
            for (SecurityQAVO qa : userReq.getSecurityQAList()) {
                SecurityQA securityQA = qa.convertToSecurityQA();
                securityQA.setUser(user);
                securityQADao.update(securityQA);
            }
        }
        return userReq;
    }

    public UserVO getUser(Integer userId) {
        return userDao.read(userId).convertToUserVO();
    }

    public List<UserVO> list() {
        List<User> users = userDao.list();
        List<UserVO> userVOList = new ArrayList<>();
        for (User user : users) {
            userVOList.add(user.convertToUserVO());
        }
        return userVOList;
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
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPswd(), true, true, true, true, authorities);
    }

}
