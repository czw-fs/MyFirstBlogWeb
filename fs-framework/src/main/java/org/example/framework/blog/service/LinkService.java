package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Link;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}