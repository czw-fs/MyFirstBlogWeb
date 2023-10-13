package org.example.blog;

import org.example.framework.domain.entity.Article;
import org.example.framework.mapper.ArticleMapper;
import org.example.framework.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * SpringBoot应用启动时进行一些初始化操作可以选择使用CommandLineRunner
 * 重写CommandLineRunner中的run方法可以使后端服务器在启动时自动调用该重写方法，并将该类bean放入注入容器
 * -> 启动调用，非手动调用
 *
 * 在应用启动时把博客的浏览量从数据库存储到redis中
 */
@Component
public class ViewCountRunner implements CommandLineRunner {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id  viewCount
        List<Article> articleList = articleMapper.selectList(null);

        //文章id作为间，浏览量作为值
        Map<String, Integer> viewCountMap = articleList.stream().collect(Collectors
                .toMap(
                article -> article.getId().toString(),
                article -> {
                    return article.getViewCount().intValue();//
                }));

        //存储到redis中
        redisCache.setCacheMap("article:viewCount",viewCountMap);
    }
}