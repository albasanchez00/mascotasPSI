package com.ceatformacion.mascotaspsi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Configurar las páginas que según el rol mostrará o negará
        http.authorizeHttpRequests(auth->auth.requestMatchers(HttpMethod.GET,"/","/idnex","/media/**","/css/**","/js/**").permitAll()
                //Acceso al crud
                .requestMatchers(HttpMethod.GET,"/crud").hasAnyRole("Admin","User")
                .requestMatchers(HttpMethod.POST,"/crud").hasAnyRole("Admin","User")

        //Formulario de Gestión de Usuarios: solo rol 'admin'
                        .requestMatchers(HttpMethod.GET,"/altaUsuario","/formulario").hasAnyRole("Admin")
                        .requestMatchers(HttpMethod.POST,"/guardarUsuario").hasAnyRole("Admin")
                        .requestMatchers("/editar/**","/borrar/**").hasRole("Admin")

        //Cualquier otra ruta necesita autentificación.
                .anyRequest().authenticated()
        ).formLogin(form->form.loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/crud",true)
                .permitAll()
        ).logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    //Encripta y lee las contraseñas... con BCrypt para usarlo en el login de Spring
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Expone un objeto de Spring que usa internamente para autenticar usuarios,
    // y lo hace accesible para que el programador lo pueda utilizar también...
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception{
        return authConfig.getAuthenticationManager();
    }
}
