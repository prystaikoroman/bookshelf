package com.learnjava.bookshelf.controller;

import com.learnjava.bookshelf.dto.UserDto;
import com.learnjava.bookshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String register(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

}
