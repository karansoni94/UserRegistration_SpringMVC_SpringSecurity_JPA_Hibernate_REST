package com.userregistration.controller;

import com.userregistration.model.SecurityQuestion;
import com.userregistration.service.SecurityQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/securityQuestions")
public class SecurityQuestionController {

    @Autowired
    SecurityQuestionService securityQuestionService;

    @Secured("ROLE_ADMIN")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<SecurityQuestion> getList() {
        return securityQuestionService.getQuestionList();
    }
}
