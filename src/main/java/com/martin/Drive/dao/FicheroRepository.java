package com.martin.Drive.dao;

import com.martin.Drive.entity.Fichero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface FicheroRepository extends JpaRepository <Fichero, Long> {

Page<Fichero> findById(@RequestParam("id") Long id, Pageable pageable);
Page <Fichero> findByDescripcion(@RequestParam("descripcion") String descripcion, Pageable pageable);

List <Fichero> findAll();

}
