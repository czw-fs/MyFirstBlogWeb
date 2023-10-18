package org.example.framework.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.admin.service.RoleMenuService;
import org.example.framework.domain.entity.RoleMenu;
import org.example.framework.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
}
