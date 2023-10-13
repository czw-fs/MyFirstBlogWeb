package org.example.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.User;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

}
