package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult listPage(Integer pageNum, Integer pageSize, User user);

    Boolean add(User user);

    Boolean removeByIdList(List<Long> idsLong);
}
