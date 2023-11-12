package com.martin.Drive.entity;

import jakarta.persistence.*;

@Entity
@Table(name="fichero")
public class Fichero {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="ruta")
    private String ruta;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Fichero () {}

    public Fichero (String descripcion) {
        this.descripcion=descripcion;
    }
}


