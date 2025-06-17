package com.ceatformacion.mascotaspsi.services;


import com.ceatformacion.mascotaspsi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/* Declara un servicio que le indica Spring que pueda utilizar Spring Security
   con los datos guardados en nuestra Base de Datos
*/
@Service
public class UsuarioDetailsServices implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    //Constructor
    public UsuarioDetailsServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    //Metodo que devuelve los datos del usuario
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Busca el usuario en la base de datos
        return usuarioRepository.findByUsername(username).map(UsuarioDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
