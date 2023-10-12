package org.example.framework.service;

import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.User;
public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}