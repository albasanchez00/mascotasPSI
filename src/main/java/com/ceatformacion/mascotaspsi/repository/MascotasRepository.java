package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotasRepository extends JpaRepository <Mascotas, Integer > {
    //Vamos a necesitar una lista para que busque por nombre...
    List<Mascotas> findByNombreContainingIgnoreCase(String nombre);
}