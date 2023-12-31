package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.AddArticleDto;
import org.example.framework.domain.entity.Article;
import org.example.framework.domain.vo.ArticleListVo;

public interface ArticleService extends IService<Article> {
    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult hotArticleList();

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult add(AddArticleDto article);


    ResponseResult getPage(Integer pageNum, Integer pageSize, ArticleListVo articleListVo);
}