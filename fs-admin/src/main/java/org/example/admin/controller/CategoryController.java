package org.example.admin.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import org.example.framework.blog.service.CategoryService;
import org.example.framework.domain.AppHttpCodeEnum;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.entity.Category;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.vo.CategoryVo;
import org.example.framework.domain.vo.ExcelCategoryVo;
import org.example.framework.utils.BeanCopyUtils;
import org.example.framework.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory(){
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @GetMapping("/list")
    public ResponseResult getCategoryList(Integer pageNum, Integer pageSize, CategoryVo tagListDto){
        return categoryService.getCategoryPage(pageNum,pageSize,tagListDto);
    }


    @PreAuthorize("@ps.hasPermission('content:category:export')")
    @GetMapping("/export")
    public void export(HttpServletResponse response){
        try {
            //设置下载文件的请求头
            WebUtils.setDownLoadHeader("分类.xlsx",response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();

            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
            //把数据写入到Excel中
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
                    .doWrite(excelCategoryVos);

        } catch (Exception e) {
            //如果出现异常也要响应json
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 根据id查
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(categoryService.getById(id));
    }

    /**
     * 增加
     * @param tag
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody Category category){
        return ResponseResult.okResult(categoryService.save(category));
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseResult remove(@PathVariable("ids") String ids){
        List<Integer> idsInteger = Arrays.stream(ids.split(","))
                .map(item ->{
                    return Integer.parseInt(item);
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(categoryService.removeByIds(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody Category ctegory){
        return ResponseResult.okResult(categoryService.updateById(ctegory));
    }



}
