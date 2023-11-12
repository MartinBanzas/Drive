package com.martin.Drive.service;

import com.martin.Drive.entity.Fichero;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FicheroService {

    List<Fichero> findAll();

    Fichero findById(Long theId);

    void save(Fichero theSave);

    void deleteById(Long theId);

}
