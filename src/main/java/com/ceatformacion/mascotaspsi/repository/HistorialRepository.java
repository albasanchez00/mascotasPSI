package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialRepository extends JpaRepository <Historial, Integer>{
    //Aquí se creará el repositorio para la clase Historial.
    List<Historial> findByMascotasIdMascota(int mascotasIdMascota);
}
