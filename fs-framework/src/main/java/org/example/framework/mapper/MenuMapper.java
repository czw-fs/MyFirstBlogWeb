package org.example.framework.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.framework.domain.entity.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userId);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    List<Menu> getAllMenuList();
}
