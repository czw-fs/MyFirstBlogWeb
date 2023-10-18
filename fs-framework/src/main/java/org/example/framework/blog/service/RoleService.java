package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Role;
import org.example.framework.domain.vo.PageVo;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<String> selectRoleKeyByUserId(Long id);


    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, Role role);


    Boolean add(Role role);

    Boolean removeByIdList(List<Integer> idsInteger);
}
