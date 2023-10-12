package org.example.framework.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.framework.constants.SystemConstants;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Link;
import org.example.framework.mapper.LinkMapper;
import org.example.framework.service.LinkService;
import org.example.framework.utils.BeanCopyUtils;
import org.example.framework.vo.LinkVo;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.list;

@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);

        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return ResponseResult.okResult(linkVos);
    }
}