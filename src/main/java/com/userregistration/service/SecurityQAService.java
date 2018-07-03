package com.userregistration.service;

import com.userregistration.dao.SecurityQADao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SecurityQAService {

    @Autowired
    SecurityQADao securityQADao;

    public void deleteSecurityQAs(Integer userId) {
        securityQADao.deleteSecurityQAsForUser(userId);
    }
}
