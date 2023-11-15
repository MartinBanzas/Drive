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
    @Column(name="tipo")
    private String tipo;

    @Column(name="size")
    private Long size;

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

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


