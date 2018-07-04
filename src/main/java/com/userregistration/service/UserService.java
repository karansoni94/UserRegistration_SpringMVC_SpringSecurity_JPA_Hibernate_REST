package com.userregistration.service;

import com.userregistration.model.UserVO;

import java.util.List;

public interface UserService {

    public UserVO createUser(UserVO userReq);

    public UserVO updateUser(UserVO userReq);

    public UserVO getUser(Integer userId);

    public void deleteUser(Integer userId);

    public List<UserVO> list();
}
