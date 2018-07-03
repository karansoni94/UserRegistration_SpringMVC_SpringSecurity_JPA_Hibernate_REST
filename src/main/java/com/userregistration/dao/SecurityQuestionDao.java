package com.userregistration.dao;

import com.userregistration.model.SecurityQuestion;
import org.springframework.stereotype.Repository;

@Repository
public class SecurityQuestionDao extends ModelDao<SecurityQuestion> {

    @Override
    public Class<SecurityQuestion> getClassName() {
        return SecurityQuestion.class;
    }
}
