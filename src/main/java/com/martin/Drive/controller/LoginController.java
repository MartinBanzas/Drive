package com.martin.Drive.controller;

import com.martin.Drive.config.UserProjection;
import com.martin.Drive.entity.AuthRequest;
import com.martin.Drive.entity.User;
import com.martin.Drive.service.JwtService;
import com.martin.Drive.service.UserService;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @CrossOrigin()
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
    @CrossOrigin()
    public String addUser(@RequestBody User userInfo){
        return userService.addUser(userInfo);

    }

  /*  @CrossOrigin
    @PatchMapping("/updateScore")
    public ResponseEntity<?> updateScore(@RequestBody User entidadActualizada) {

       User user = entidadActualizada;
       userService.updateScore(user);

        return ResponseEntity.ok().build();
    }*/

}
