package org.example.framework.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.admin.service.UserRoleService;
import org.example.framework.domain.entity.UserRole;
import org.example.framework.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
