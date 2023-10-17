package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Link;
import org.example.framework.domain.vo.LinkVo;
import org.example.framework.domain.vo.PageVo;

public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, LinkVo linkVo);
}
