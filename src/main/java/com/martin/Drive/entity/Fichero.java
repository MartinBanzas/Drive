package com.martin.Drive.entity;

import jakarta.persistence.*;

@Entity
@Table(name="fichero")
public class Fichero {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="descripcion")
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaarchivo() {
        return rutaarchivo;
    }

    public void setRutaarchivo(String rutaarchivo) {
        this.rutaarchivo = rutaarchivo;
    }

    @Column(name="ruta")
    private String rutaarchivo;

    public Fichero () {}

    public Fichero (String descripcion) {
        this.descripcion=descripcion;
    }
}


