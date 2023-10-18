package org.example.admin.controller;

import org.example.framework.blog.service.ArticleService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.AddArticleDto;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Article;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.ArticleListVo;
import org.example.framework.domain.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;



    @GetMapping("/list")
    public ResponseResult getPage(Integer pageNum, Integer pageSize, ArticleListVo articleListVo){
        return articleService.getPage(pageNum,pageSize,articleListVo);
    }

    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(articleService.getById(id));
    }

    /**
     * 增加
     * @param
     * @return
     */
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article){
        return articleService.add(article);
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
        return ResponseResult.okResult(articleService.removeByIds(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param tag
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Article article){
        return ResponseResult.okResult(articleService.updateById(article));
    }



}