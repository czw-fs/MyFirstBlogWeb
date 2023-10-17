package org.example.admin.controller;

import org.example.framework.domain.ResponseResult;
import org.example.framework.admin.service.TagService;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.domain.vo.TagVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    /**
     * 查列表
     * @param pageNum
     * @param pageSize
     * @param tagListDto
     * @return
     */
    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    /**
     * 获取所有分类
     * @return
     */
    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        List<TagVo> list = tagService.listAllTag();
        return ResponseResult.okResult(list);
    }
    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(tagService.getById(id));
    }

    /**
     * 增加
     * @param tag
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody Tag tag){
        return ResponseResult.okResult(tagService.save(tag));
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
        return ResponseResult.okResult(tagService.removeByIds(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param tag
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Tag tag){
        return ResponseResult.okResult(tagService.updateById(tag));
    }
}
