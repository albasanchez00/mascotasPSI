package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.repository.MascotasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MascotaController {
    @Autowired
    private MascotasRepository mascotasRepository;

    @GetMapping("/registro")
    public String registro(Model model){
        model.addAttribute("mascotas", new Mascotas());
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
}
