package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);
}
