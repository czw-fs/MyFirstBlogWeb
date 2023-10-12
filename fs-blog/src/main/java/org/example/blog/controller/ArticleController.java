package org.example.blog.controller;



import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Article;
import org.example.framework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

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
    public ResponseResult articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }

}