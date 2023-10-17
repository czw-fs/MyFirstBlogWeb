package org.example.framework.admin.service;

import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}