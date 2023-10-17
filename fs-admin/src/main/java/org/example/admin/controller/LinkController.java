package org.example.admin.controller;

import org.example.framework.admin.service.TagService;
import org.example.framework.blog.service.LinkService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Link;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.LinkVo;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.domain.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: fs
 * @date: 2023/10/17 22:11
 * @Description: everything is ok
 */
@RestController
@RequestMapping("/content/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 查列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, LinkVo linkVo){
        return linkService.pageTagList(pageNum,pageSize,linkVo);
    }


    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(linkService.getById(id));
    }

    /**
     * 增加
     * @param tag
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody Link link){
        return ResponseResult.okResult(linkService.save(link));
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseResult remove(@PathVariable("ids") String ids){
        List<Integer> idsInteger = Arrays.stream(ids.split(","))
                .map(item ->{
                    return Integer.parseInt(item);
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(linkService.removeByIds(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param tag
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Link link){
        return ResponseResult.okResult(linkService.updateById(link));
    }

}
