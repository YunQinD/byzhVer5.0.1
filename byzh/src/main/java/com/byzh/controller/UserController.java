package com.byzh.controller;

import com.byzh.Mapper.PostMapper;
import com.byzh.Mapper.UserMapper;
import com.byzh.pojo.User;
import com.byzh.service.UserService;
import com.byzh.utils.R;
import com.byzh.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public R getAll(){
        return new R(true,userService.list());
    }

    //注册请求
    @PostMapping("register")
    public R save(@RequestBody User users){
        StringUtil stringUtil = new StringUtil();
        return new R(stringUtil.isPwd(users.getPwd()) && userService.register(users));
    }

    @DeleteMapping("{id}")
    public R delete(@PathVariable String id){
        return new R(userService.delete(id));
    }

    //修改用户信息
    @PutMapping
    public R update(@RequestBody User users){
        String uid = users.getId();
        String nickName = users.getNickname();
        if(users.getHeading() == null){
            users.setHeading(userMapper.getHeadingByUid(uid));
        }
        return new R(userService.modify(users) && postMapper.updatePostName(nickName,uid));
    }

    @GetMapping("{id}")
    public R getById(@PathVariable String id){
        return new R(true,userService.getById(id));
    }

    //登录请求
    @GetMapping("/login/{id}/{pwd}")
    public R login(@PathVariable String id, @PathVariable String pwd, HttpServletRequest request){
        User user = userService.login(id, pwd);
        if(user!=null) {
            request.getSession().setAttribute("user",user);
        }
        return new R(user != null,user);
    }

    
    @GetMapping("/ss")
    public R getUserFromSession(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return new R(user!=null,user);
    }
    /*@GetMapping("login")
    public R login(String id,String pwd){
        Users users = userService.login(id, pwd);
        return new R(users != null);
    }*/
}
