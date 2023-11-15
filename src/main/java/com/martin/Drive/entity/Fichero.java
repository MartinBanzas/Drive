package com.martin.Drive.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
;

@Data
@Entity
@Table(name="fichero")
public class Fichero {


    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) private long id;
    @Column(name="descripcion") private String descripcion;
    @Column(name="ruta") private String ruta;
    @Column(name="tipo") private String tipo;
    @Column(name="size") private Long size;
    @Column (name="fCreacion") private LocalDateTime fCreacion;

    public Fichero () {}


}


