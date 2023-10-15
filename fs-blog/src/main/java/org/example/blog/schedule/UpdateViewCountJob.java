package org.example.blog.schedule;


import org.example.framework.domain.entity.Article;
import org.example.framework.service.ArticleService;
import org.example.framework.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ArticleService articleService;

    @Scheduled(cron = "0 0/10 * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> viewCountMap = redisCache.getCacheMap("article:viewCount");

        List<Article> articles = viewCountMap.entrySet()
                .stream()
                .map(entry -> {
                   return new Article(Long.valueOf(entry.getKey()), entry.getValue().longValue());
                })
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);

    }
}
