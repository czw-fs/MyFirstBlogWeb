package org.example.admin.controller;

import org.example.framework.blog.service.UserService;
import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.dto.TagListDto;
import org.example.framework.domain.entity.Tag;
import org.example.framework.domain.entity.User;
import org.example.framework.domain.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody User user){
        user.setId(user.getUserId());
        return ResponseResult.okResult(userService.updateById(user));
    }

    @GetMapping("/list")
    public ResponseResult listPage(Integer pageNum, Integer pageSize,User user){
        return userService.listPage(pageNum,pageSize,user);
    }

    /**
     * 根据id查
     * @param
     * @return
     */
    @GetMapping("/{id}")
    public ResponseResult getById(@PathVariable("id") Long id){
        return ResponseResult.okResult(userService.getById(id));
    }

    /**
     * 增加
     * @param
     * @return
     */
    @PostMapping
    public ResponseResult save(@RequestBody User user){
        return ResponseResult.okResult(userService.add(user));
    }

    /**
     * 根据id删除
     * @param
     * @return
     */
    @DeleteMapping("/{ids}")
    public ResponseResult remove(@PathVariable("ids") String ids){
        List<Long> idsInteger = Arrays.stream(ids.split(","))
                .map(item ->{
                    return Long.parseLong(item);
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(userService.removeByIdList(idsInteger));
    }

    /**
     * 根据id更新数据
     * @param
     * @return
     */
    @PutMapping
    public ResponseResult update(@RequestBody User user){
        return ResponseResult.okResult(userService.updateById(user));
    }


}
