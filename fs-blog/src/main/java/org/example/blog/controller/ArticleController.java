package org.example.blog.controller;



import io.swagger.annotations.Api;
import org.example.framework.annotation.SystemLog;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Article;
import org.example.framework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id){
        return articleService.updateViewCount(id);
    }

    /**
     * 热门文章列表
     * @return
     */
    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){

        ResponseResult result =  articleService.hotArticleList();
        return result;
    }

    /**
     * 文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @GetMapping("/articleList")
    @SystemLog(BusinessName = "获取文章列表")
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        ResponseResult responseResult = articleService.articleList(pageNum, pageSize, categoryId);
        return responseResult;
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

}
