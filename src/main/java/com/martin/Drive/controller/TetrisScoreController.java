package com.martin.Drive.controller;


import com.martin.Drive.entity.User;
import com.martin.Drive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tetris")
public class TetrisScoreController {

    @Autowired
    private UserService userService;

    @PatchMapping("/highScore")
    @CrossOrigin()
    public ResponseEntity<?> updateScore(@RequestBody User player) {

        userService.updateScore(player);

        return ResponseEntity.ok().build();
    }



   }


