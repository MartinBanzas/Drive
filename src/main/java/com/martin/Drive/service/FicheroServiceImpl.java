package com.martin.Drive.service;

import com.martin.Drive.dao.FicheroRepository;
import com.martin.Drive.entity.Fichero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FicheroServiceImpl implements FicheroService {

private FicheroRepository ficheroRepository;

@Autowired
public FicheroServiceImpl (FicheroRepository ficheroRepository) {

    this.ficheroRepository=ficheroRepository;
}

    @Override
    public List<Fichero> findAll() {
        return ficheroRepository.findAll();
    }

    @Override
    public Fichero findById(Long theId) {

        Optional<Fichero> result = ficheroRepository.findById(theId);

       Fichero theFichero = null;

       if (result.isPresent()) {
           theFichero=result.get();
       } else {
           throw new RuntimeException("File not found");
       }
       return theFichero;
    }

    @Override
    public void save(Fichero theFichero) {

    ficheroRepository.save(theFichero);

    }

    @Override
    public void deleteById(Long theId) {
    ficheroRepository.deleteById(theId);

    }
}
