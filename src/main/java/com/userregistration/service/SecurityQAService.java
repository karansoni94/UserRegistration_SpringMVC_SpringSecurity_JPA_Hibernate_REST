package com.userregistration.service;

import com.userregistration.dao.SecurityQADao;
import com.userregistration.model.SecurityQAVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SecurityQAService {

    @Autowired
    SecurityQADao securityQADao;

    public void deleteSecurityQAs(Integer userId) {
        securityQADao.deleteSecurityQAsForUser(userId);
    }

    public void validateSecurityQAs(Integer userId, List<SecurityQAVO> securityQAVOList) {
        throw new RuntimeException("Implementation Pending");
    }
}
