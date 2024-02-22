package com.martin.Drive.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String username;
    private String email;
    private String roles;
    private String password;
    private String nombre;
    private int puntuacion;
    private int movil;
    private String twitter;
    private String facebook;
    private String instagram;
    private String bio;
    private String avatar;


}
