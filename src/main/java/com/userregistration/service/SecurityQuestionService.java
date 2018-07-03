package com.userregistration.service;

import com.userregistration.dao.SecurityQuestionDao;
import com.userregistration.model.SecurityQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SecurityQuestionService {
    @Autowired
    private SecurityQuestionDao securityQuestionDao;

    public List<SecurityQuestion> getQuestionList() {
        return securityQuestionDao.list();
    }
}
