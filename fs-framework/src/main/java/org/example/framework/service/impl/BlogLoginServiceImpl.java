package org.example.framework.service.impl;

import org.example.framework.domain.ResponseResult;
import org.example.framework.domain.LoginUser;
import org.example.framework.domain.entity.User;
import org.example.framework.service.BlogLoginService;
import org.example.framework.utils.BeanCopyUtils;
import org.example.framework.utils.JwtUtil;
import org.example.framework.utils.RedisCache;
import org.example.framework.domain.vo.BlogUserLoginVo;
import org.example.framework.domain.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {

        /**
         * 注意： UsernamePasswordAuthenticationToken() 构造器两个参数代表未认证，三个参数是已认证
         * 这里无token，代表未登录，使用两个参数的
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        //判断是否认证通过
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //获取登录用户信息
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //获取userId
        String userId = loginUser.getUser().getId().toString();
        //生成jwttoken
        String jwt = JwtUtil.createJWT(userId);

        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        //把User转换成UserInfoVo
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //把token和userinfo封装 返回
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt,userInfoVo);
        return ResponseResult.okResult(vo);
    }


    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //获取userid
        Long userId = loginUser.getUser().getId();

        //删除redis中的用户信息
        redisCache.deleteObject("bloglogin:"+userId);

        return ResponseResult.okResult();
    }
}
