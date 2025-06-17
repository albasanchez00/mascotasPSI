package com.ceatformacion.mascotaspsi.controller;

import com.ceatformacion.mascotaspsi.model.Mascotas;
import com.ceatformacion.mascotaspsi.model.Usuario;
import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/altaUsuario")
    public String altaUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "altaUsuario";
    }

    @PostMapping("/guardarUsuario")
    public String guardarUsuario(@ModelAttribute Usuario usuario,Model model) {
        if (usuarioRepository.findByUsername(usuario.getUsername()).isEmpty()) {
            Usuario user = new Usuario();
            user.setUsername(usuario.getUsername());
            user.setPassword(encoder.encode(usuario.getPassword()));
            user.setRol(usuario.getRol());
            usuarioRepository.save(user);
            return "redirect:/";
        }else{
            model.addAttribute("error", "El usuario ya existe, indique uno nuevo");
            return "altaUsuario";
        }
    }
    //Metodo que nos permite eliminar un usuario
    @GetMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable int id, Model model){
        usuarioRepository.deleteById(id);
        return "redirect:/";
    }
    //Metodo que nos permite actualizar un usuario
    @GetMapping("/editarUsuario/{id}")
    public String actualizarUsuario(@PathVariable int id, Model model){
        //Debemos enviar los datos del cliente que hemos consultado mediante el {id},
        //Hibernate lo busca y lo almacena en un objeto (Clientes).
        //Se busca en la BBDD y despues se almacenan los datos en un objeto tipo Cliente.
        Usuario usuario = usuarioRepository.findById(id).get(); //Busca x el id invitado por la url..
        model.addAttribute("usuario", usuario);
        return "altaUsuario";
    }
    //Metodo que nos permite actualizar un usuario
    @PostMapping("/actualizarUsuario")
    public String actualizarUsuario(@ModelAttribute Usuario usuario, Model model){
        usuarioRepository.save(usuario);
        return "redirect:/";
    }
    //Logout
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
}
