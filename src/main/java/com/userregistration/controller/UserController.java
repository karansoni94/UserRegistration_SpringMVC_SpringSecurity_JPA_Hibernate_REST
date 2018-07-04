package com.userregistration.controller;

import com.userregistration.model.UserVO;
import com.userregistration.service.SecurityQAService;
import com.userregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityQAService securityQAService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public UserVO createUser(@RequestBody UserVO userReq) {
        return userService.createUser(userReq);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public UserVO updateUser(@PathVariable("userId") Integer userId, @RequestBody UserVO user) {
        user.setUserId(userId);
        return userService.updateUser(user);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public UserVO getUser(@PathVariable("userId") Integer userId) {
        return userService.getUser(userId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<UserVO> getUserList() {
        return userService.list();
    }


    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/{userId}/validateQA", method = RequestMethod.POST, produces = "application/json")
    public void validateQA(@PathVariable("userId") Integer userId, @RequestBody UserVO userVO) {
        securityQAService.validateSecurityQAs(userId, userVO.getSecurityQAList());
    }
}
