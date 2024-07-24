package com.example.mango_admin.controller;

import com.example.mango_admin.model.User;
import com.example.mango_admin.service.UserService;
import org.example.core.page.PageRequest;
import org.example.core.page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public PageResult listUsers(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize) {
        PageRequest pageRequest = new PageRequest(pageNum, pageSize);
//        return userService.findPage(pageRequest);
    }

}
