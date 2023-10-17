package org.example.framework.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.admin.service.MenuService;
import org.example.framework.constants.SystemConstants;
import org.example.framework.domain.entity.Menu;
import org.example.framework.mapper.MenuMapper;
import org.example.framework.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> selectPermsByUserId(Long id) {
        //如果是管理员，返回所有的权限
        if(id == 1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType,SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus, SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms)
                    .collect(Collectors.toList());
            return perms;
        }
        //否则返回所具有的权限
        return menuMapper.selectPermsByUserId(id);
    }

    @Override
    public List<Menu> selectRouterMenuTreeByUserId(Long userId) {
        List<Menu> menuList = null;
        //判断是否是管理员
        if(SecurityUtils.isAdmin()){
            //如果是 获取所有符合要求的Menu
            menuList = menuMapper.selectAllRouterMenu();
        }else{
            //否则  获取当前用户所具有的Menu
            menuList = menuMapper.selectRouterMenuTreeByUserId(userId);
        }

        //构建tree
        //先找出第一层的菜单  然后去找他们的子菜单设置到children属性中
        List<Menu> menuTree = builderMenuTree(menuList,0L);
        return menuTree;
    }

    private List<Menu> builderMenuTree(List<Menu> menuList, Long parentId) {
        List<Menu> menuTree = menuList.stream()
                //获取所有根目录
                .filter(menu -> menu.getParentId().equals(parentId))
                //将所有根目录成树
                .map(menu -> {
                    menu.setChildren(getChildren(menu, menuList));
                    return menu;
                })
                .collect(Collectors.toList());
        return menuTree;
    }

    /**
     * 获取存入参数的 子Menu集合
     * @param menu
     * @param menus
     * @return
     */
    private List<Menu> getChildren(Menu menu, List<Menu> menuList) {
        List<Menu> childrenList = menuList.stream()
                //获取当前目录的所有下级目录
                .filter(m -> m.getParentId().equals(menu.getId()))
                //将下级目录集合的放入上级目录的children属性中，递归成树
                .map(m->{
                    m.setChildren(getChildren(m,menuList));
                    return m;
                })
                .collect(Collectors.toList());
        return childrenList;
    }
}