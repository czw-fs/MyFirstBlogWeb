package org.example.framework.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.framework.domain.entity.Role;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(Long userId);
}
