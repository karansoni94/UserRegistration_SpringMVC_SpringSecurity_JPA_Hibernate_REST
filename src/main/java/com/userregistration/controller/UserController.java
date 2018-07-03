package com.userregistration.controller;

import com.userregistration.model.UserRequest;
import com.userregistration.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public UserRequest createUser(@RequestBody UserRequest userReq) {
        return userService.createUser(userReq);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
    public UserRequest updateUser(@PathVariable("userId") Integer userId, @RequestBody UserRequest user) {
        user.setUserId(userId);
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
    public UserRequest getUser(@PathVariable("userId") Integer userId) {
        return userService.getUser(userId);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
    }
}
