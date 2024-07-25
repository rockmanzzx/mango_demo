package com.example.mango_admin.controller;

import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import org.example.core.http.HttpResult;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/findPage")
    public HttpResult listUsers(@RequestBody PageRequest pageRequest) {
        return HttpResult.success(userService.findPage(pageRequest));
//        return userService.findPage(pageRequest);
    }

}
