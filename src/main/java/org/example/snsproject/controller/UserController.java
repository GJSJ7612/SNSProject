package org.example.snsproject.controller;

import org.example.snsproject.entity.Result;
import org.example.snsproject.entity.User;
import org.example.snsproject.service.UserService;
import org.example.snsproject.utils.JwtUtil;
import org.example.snsproject.utils.Md5Util;
import org.example.snsproject.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Validated
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("/register")
    @CrossOrigin
    public Result<Map<String, Serializable>> register(@RequestBody User user) {
        User userExist = userService.findUserByAccount(user.getAccount());
        if(userExist != null) {
            return Result.error(20005);
        }else {
            userService.register(user);
            User userLocal = userService.findUserByAccount(user.getAccount());
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",userLocal.getId());
            claims.put("account",userLocal.getAccount());
            String token = JwtUtil.genToken(claims);
            //把token存入redis中
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            valueOperations.set(token, token, 1, TimeUnit.DAYS);
            Map<String,Serializable> data = new HashMap<>();
            data.put("Oauth-Token",token);
            return Result.success(data);
        }
    }

    @GetMapping("/users/currentUser")
    @CrossOrigin
    public Result<User> getCurrentUser() {
        Map<String,Object> map = ThreadLocalUtil.get();
        if(map == null) {return Result.success(null);}
        String account = (String) map.get("account");
        User user = userService.findUserByAccount(account);
        return Result.success(user);
    }

    @PostMapping("/login")
    @CrossOrigin
    public Result<Map<String, Serializable>> login(@RequestBody User user) {
        //跟据用户名查询用户
        User loginUser = userService.findUserByAccount(user.getAccount());
        //判断该用户是否存在
        if(loginUser == null) return Result.error(20002);
        //判断密码是否正确
        if(Md5Util.getMD5String(user.getPassword()).equals(loginUser.getPassword())){
            //登录成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("account",loginUser.getAccount());
            String token = JwtUtil.genToken(claims);
            //把token存入redis中
            ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
            valueOperations.set(token, token, 1, TimeUnit.HOURS);
            Map<String,Serializable> data = new HashMap<>();
            data.put("Oauth-Token",token);
            return Result.success(data);
        }
        return Result.error(20002);
    }

    @GetMapping("/logout")
    @CrossOrigin
    public Result logout() {
        ThreadLocalUtil.remove();
        return Result.success();
    }
}
