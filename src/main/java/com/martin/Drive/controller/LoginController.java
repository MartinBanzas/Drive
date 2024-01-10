package com.martin.Drive.controller;

import com.martin.Drive.entity.AuthRequest;
import com.martin.Drive.entity.User;
import com.martin.Drive.service.JwtService;
import com.martin.Drive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/loginUser")
    public String addUser(@RequestBody AuthRequest authRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        }else {
            throw new UsernameNotFoundException("Invalid user request");
        }
    }


    @PostMapping("/addUser")
    public String addUser(@RequestBody User userInfo){
        return userService.addUser(userInfo);

    }

}
