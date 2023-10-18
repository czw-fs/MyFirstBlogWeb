package org.example.framework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.admin.service.RoleMenuService;
import org.example.framework.blog.service.RoleService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Role;
import org.example.framework.domain.entity.RoleMenu;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.mapper.RoleMapper;
import org.example.framework.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, Role role) {
        //分页查询
        LambdaQueryWrapper<Role> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName());
        queryWrapper.eq(StringUtils.hasText(role.getStatus()),Role::getStatus,role.getStatus());

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page, queryWrapper);
        //封装数据返回
        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    @Transactional
    public Boolean add(Role role) {

        roleMapper.insert(role);
        //mp插入后返回主键
        Long roleId = role.getId();

        //将新建角色的menuId和角色id对应起来，存储到sys_role_menu
        List<RoleMenu> roleMenuList = role.getMenuIds().stream().map(menuId -> {
            return new RoleMenu(roleId, menuId);
        }).collect(Collectors.toList());

        roleMenuService.saveBatch(roleMenuList);


        return true;
    }

    @Override
    @Transactional
    public Boolean removeByIdList(List<Integer> idsInteger) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        for (Integer roleId : idsInteger) {
            wrapper.eq(RoleMenu::getRoleId,roleId);
        }
        roleMenuMapper.delete(wrapper);
        removeByIds(idsInteger);
        return true;
    }


}
