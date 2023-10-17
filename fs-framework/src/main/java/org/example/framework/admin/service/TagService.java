package org.example.framework.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.PageVo;
import org.example.framework.domain.vo.TagVo;

import java.util.List;

/**
 * 标签(Tag)表服务接口
 *
 * @author makejava
 * @since 2022-07-19 22:33:38
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);

    List<TagVo> listAllTag();
}
