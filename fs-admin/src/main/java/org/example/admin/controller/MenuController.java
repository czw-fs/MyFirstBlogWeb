package org.example.admin.controller;

import org.example.framework.admin.service.MenuService;
import org.example.framework.admin.service.TagService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Menu;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.domain.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: fs
 * @date: 2023/10/17 22:26
 * @Description: everything is ok
 */
@RestController
@RequestMapping("/system/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 查列表
     * @param pageNum
     * @param pageSize
     * @param
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, Menu menu){
        return menuService.pageTagList(pageNum,pageSize,menu);
    }


    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(menuService.getById(id));
    }

    /**
     * 增加
     * @param
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody Menu menu){
        return ResponseResult.okResult(menuService.save(menu));
    }

    /**
     * 根据id删除
     * @param
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseResult remove(@PathVariable("ids") String ids){
        List<Integer> idsInteger = Arrays.stream(ids.split(","))
                .map(item ->{
                    return Integer.parseInt(item);
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(menuService.removeByIds(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Menu menu){
        return ResponseResult.okResult(menuService.updateById(menu));
    }


}
