/*package com.example.blog.service;

import com.example.blog.dto.JWTResponse;
import com.example.blog.dto.LogInRequest;
import com.example.blog.dto.RegisterRequest;
import com.example.blog.repository.UserRepository;

public class UserService {

    //private final UserRepository userRepository;
    //void register(RegisterRequest request);

    //JWTResponse login(LogInRequest request);



}
*/
package com.example.blog.service;

import com.example.blog.dto.JWTResponse;
import com.example.blog.dto.LogInRequest;
import com.example.blog.dto.RegisterRequest;
import com.example.blog.dto.UserResponse;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public JWTResponse register (RegisterRequest registerRequest){

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

      User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setUsername(registerRequest.getUsername());
        user.setName(registerRequest.getName());


        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);


        String token = jwtService.generateToken(user.getUsername());

        return new JWTResponse(token);


    }


    public UserResponse login(LogInRequest logInRequest){
        User user= userRepository.findByUsername(logInRequest.getUsername())
                .orElseThrow(()-> new RuntimeException("User Not Found!"));

                if(!passwordEncoder.matches(logInRequest.getPassword(), user.getPassword())){
                    throw new RuntimeException("The password entered is incorrect!"
                    );
                }

                String token;
                token = jwtService.generateToken(user.getUsername());


              return new UserResponse(token , user.getUsername(), user.getEmail(), user.getName());

    }






}



