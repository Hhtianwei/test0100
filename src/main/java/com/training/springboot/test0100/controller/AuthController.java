package com.training.springboot.test0100.controller;

import com.training.springboot.test0100.entity.User;
import com.training.springboot.test0100.repository.UserRepository;
import com.training.springboot.test0100.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 田伟 on 2018-08-20.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(User user){
        User result = userService.register(user.getUsername(),user.getPassword(),user.getMobile());
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<String> createToken(String username,String password){
        String token = userService.createToken(username,password);
        if(token == null){
            ResponseEntity.noContent();
        }
        return ResponseEntity.ok(token);
    }


}
