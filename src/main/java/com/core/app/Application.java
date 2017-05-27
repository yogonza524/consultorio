package com.core.app;

import com.core.services.FichaService;
import com.core.services.PacienteService;
import com.core.services.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    //Beans
    @Bean
    UsuarioService usuarioService(){
        return new UsuarioService();
    }
    
    @Bean
    PacienteService pacienteService(){
        return new PacienteService();
    }
    
    @Bean
    FichaService fichaService(){
        return new FichaService();
    }
}
