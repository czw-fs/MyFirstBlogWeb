package org.example.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Article;

public interface ArticleService extends IService<Article> {
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);


    ResponseResult hotArticleList();

    ResponseResult getArticleDetail(Long id);
}