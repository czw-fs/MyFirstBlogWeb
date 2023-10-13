package org.example.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Comment;

public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}