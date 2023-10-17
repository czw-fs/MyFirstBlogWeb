package org.example.framework.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Category;
import org.example.framework.domain.vo.CategoryVo;

import java.util.List;


public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

}