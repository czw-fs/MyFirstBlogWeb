package org.example.framework.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.blog.service.ArticleTagService;
import org.example.framework.domain.entity.ArticleTag;
import org.example.framework.mapper.ArticleMapper;
import org.example.framework.mapper.ArticleTagMapper;
import org.springframework.stereotype.Service;

@Service
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper,ArticleTag> implements ArticleTagService {


}
