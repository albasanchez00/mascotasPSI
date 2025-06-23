package com.ceatformacion.mascotaspsi.repository;

import com.ceatformacion.mascotaspsi.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialRepository extends JpaRepository <Historial, Integer>{
    List<Historial> findByMascotasIdMascota(int mascotaId);
}

