package org.example.framework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Menu;
import org.example.framework.domain.vo.PageVo;


import java.util.List;

public interface MenuService extends IService<Menu> {
    List<String> selectPermsByUserId(Long id);

    List<Menu> selectRouterMenuTreeByUserId(Long userId);

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, Menu menu);

    List<Menu> selectAllMenuTree();
}
