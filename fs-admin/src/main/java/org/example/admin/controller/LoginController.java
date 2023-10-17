package org.example.admin.controller;

import org.example.framework.admin.service.LoginService;
import org.example.framework.domain.*;
import org.example.framework.domain.entity.User;
import org.example.framework.domain.vo.UserInfoVo;
import org.example.framework.utils.BeanCopyUtils;
import org.example.framework.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

//    @Autowired
//    private MenuService menuService;
//
//    @Autowired
//    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

//    @GetMapping("getInfo")
//    public ResponseResult<AdminUserInfoVo> getInfo(){
//        //获取当前登录的用户
//        LoginUser loginUser = SecurityUtils.getLoginUser();
//        //根据用户id查询权限信息
//        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
//        //根据用户id查询角色信息
//        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());
//
//        //获取用户信息
//        User user = loginUser.getUser();
//        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
//        //封装数据返回
//
//        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
//        return ResponseResult.okResult(adminUserInfoVo);
//    }

}