package com.userregistration.service;

import com.userregistration.model.UserRequest;

public interface IUserService {

    public UserRequest createUser(UserRequest userReq);

    public UserRequest updateUser(UserRequest userReq);

    public UserRequest getUser(Integer userId);

    public void deleteUser(Integer userId);
}
