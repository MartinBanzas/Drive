package com.martin.Drive.dao;

import com.martin.Drive.entity.Fichero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FicheroRepository extends JpaRepository <Fichero, Integer> {
}
