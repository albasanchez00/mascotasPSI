package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MascotasRepository extends JpaRepository <Mascotas, Integer > {
}
