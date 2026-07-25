/*package com.example.blog.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {

}
*/


package com.example.blog.controller;

import com.example.blog.dto.JWTResponse;
import com.example.blog.dto.LogInRequest;
import com.example.blog.dto.RegisterRequest;
import com.example.blog.dto.UserResponse;
import com.example.blog.model.User;
import com.example.blog.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class UserController {


    private final UserService userService;


    public UserController(UserService userService){
        this.userService = userService;}

    @PostMapping("/register")
    public ResponseEntity <?> register (@RequestBody RegisterRequest registerRequest){
    try {
        JWTResponse jwtResponse= userService.register(registerRequest);
                return ResponseEntity.ok(jwtResponse);

    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    }

    @PostMapping ("/log-in")
public ResponseEntity<?> login (@RequestBody LogInRequest logInRequest){
        try {
            UserResponse userResponse;
            userResponse=userService.login(logInRequest);
        return ResponseEntity.ok(userResponse);

        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());

        }

    }


/*
    @PostMapping
    public User createUser(@RequestBody User user){

        return userService.createUser(user);
    }


    @GetMapping
    public List<User> getUsers(){

        return userService.getUsers();
    }*/
}