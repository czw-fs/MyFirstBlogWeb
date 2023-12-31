package org.example.framework.blog.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.domain.entity.Article;
import org.example.framework.domain.entity.Category;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.mapper.CategoryMapper;
import org.example.framework.blog.service.ArticleService;
import org.example.framework.constants.SystemConstants;
import org.example.framework.domain.ResponseResult;
import org.example.framework.blog.service.CategoryService;
import org.example.framework.utils.BeanCopyUtils;
import org.example.framework.domain.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public ResponseResult getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public ResponseResult getCategoryPage(Integer pageNum, Integer pageSize, CategoryVo categoryVo) {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(categoryVo.getName()),Category::getName,categoryVo.getName());
        wrapper.eq(StringUtils.hasText(categoryVo.getStatus()),Category::getStatus,categoryVo.getStatus());

        Page page = new Page<CategoryVo>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);

        PageVo pageVo = new PageVo(page.getRecords(),page.getTotal());

        return ResponseResult.okResult(pageVo);
    }
}
