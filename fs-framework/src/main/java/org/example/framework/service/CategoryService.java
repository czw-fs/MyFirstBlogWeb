package org.example.framework.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Category;


public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();

}