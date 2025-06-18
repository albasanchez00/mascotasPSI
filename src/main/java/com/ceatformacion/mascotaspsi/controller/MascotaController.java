package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MascotaController {
    //Atributos
    @Autowired
    private MascotasRepository mascotasRepository;

    //Métodos
    @GetMapping("/")
    public String inicio(){
        return "index";
    }
    @GetMapping("/formulario")
    public String registro(Model model){
        model.addAttribute("mascota", new Mascotas());
        return "formulario";
    }

    @PostMapping("/crud")
    public String listado(@ModelAttribute Mascotas mascotaForm, Model model){
        mascotasRepository.save(mascotaForm);//Se guarda en la BBDD.
        return "redirect:/crud";
    }

    @GetMapping("/crud")
    public String listado(Model model){
        model.addAttribute("mascotas", mascotasRepository.findAll());
        return "crud";
    }

    @GetMapping("/editar/{id}")
    public String actualizarMascota(@PathVariable int id, Model model){
        //Debemos enviar los datos del cliente que hemos consultado mediante el {id},
        //Hibernate lo busca y lo almacena en un objeto (Clientes).
        //Se busca en la BBDD y despues se almacenan los datos en un objeto tipo Cliente.
        Mascotas mascota = mascotasRepository.findById(id).get(); //Busca x el id invitado por la url..
        model.addAttribute("mascota", mascota);
        return "formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarMascota(@PathVariable int id, Model model){
        mascotasRepository.deleteById(id);
        return "redirect:/crud";
    }

    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam String nombre, Model model){
        List<Mascotas> resultado = mascotasRepository.findByNombreContainingIgnoreCase(nombre);
        model.addAttribute("mascotas", resultado);
        return "crud";
    }


}
