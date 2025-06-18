package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Historial;
import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.HistorialRepository;
import com.ceatformacion.mascotaspsi.repository.MascotasRepository;
import com.ceatformacion.mascotaspsi.services.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HistorialController {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private MascotasRepository mascotasRepository;

    private HistorialService historialService;
    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @GetMapping("/historial/{id}")
    public String getHistorialMascota(@PathVariable int id){
        historialRepository.findById(id).get();
        return "historial";
    }


    /*API REST → Recibe y envía los datos JSON
        GET → Obtiene los datos JSON
        POST → Crea un nuevo registro JSON
        PUT → Actualiza un registro JSON
        DELETE → Elimina un registro JSON
    */
    @ResponseBody //API REST para guardar una nueva entrada al historial
    @PostMapping("api/historial/")
    public Historial save(@PathVariable Historial historial){
        return historialRepository.save(historial);
    }

    //Este método busca el historial de la mascota cuando se solicite por el ID
    @ResponseBody //API REST para eliminar una entrada del historial
    @GetMapping("/mascotas/{id}")
    public List<Historial> findByMascotaId(@PathVariable int id){
        return historialService.obtenerHistorialMascota(id);
    }

    //Controlar la vista del formulario e historial
    @GetMapping("/consulta/{id}")
    public String getHistorialById(@PathVariable int id, Model model){
        Mascotas mascota = mascotasRepository.findById(id).orElseThrow();
        List<Historial> historial = historialRepository.findByMascotasIdMascota(id);
        model.addAttribute("mascotas", mascota);
        model.addAttribute("historial", historial);
        model.addAttribute("nuevaVisita", new Historial());
        return "historial";
    }

    //Guardar la vista
    @PostMapping("/consulta/{id}")
    public String regisrtarVisita(@PathVariable int id, @ModelAttribute ("nuevaVisita") Historial nuevaVisita){
        Mascotas mascota = mascotasRepository.findById(id).orElseThrow();
        nuevaVisita.setMascotas(mascota);
        historialRepository.save(nuevaVisita);
        return "redirect:/consulta/"+id;
    }





}
