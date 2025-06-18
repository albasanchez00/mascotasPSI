package com.ceatformacion.mascotaspsi.services;

import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HistorialService {

    private final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    //Crear un método que obtiene el historial x mascota
    public List<Historial> obtenerHistorialMascota(int idMascota){
        return historialRepository.findByMascotasIdMascota(idMascota);
    }

    //Guardar  la Entrada en el Historial
    public void guardarEntrada(Historial historial){
        historialRepository.save(historial);
    }

    //Eliminar entrada del Historial
    public void eliminarEntrada(Integer idHistorial){
        historialRepository.deleteById(idHistorial);
    }

}
