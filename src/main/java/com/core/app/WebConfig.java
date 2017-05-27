/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.app;

import com.core.interceptors.HomeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

    private static final String[] RESOURCE_LOCATIONS = {
		"classpath:/META-INF/resources/", "classpath:/resources/",
		"classpath:/static/", "classpath:/public/" };
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HomeInterceptor())
                .addPathPatterns("/home")
                .addPathPatterns("/pacientes")
                .addPathPatterns("/turnos")
                .addPathPatterns("/nuevo_paciente")
                .addPathPatterns("/busqueda")
                .addPathPatterns("/modificar_paciente/**")
                .addPathPatterns("/modificar_pacientes")
                .addPathPatterns("/ficha/**")
                ;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/**")) {
		registry.addResourceHandler("/**").addResourceLocations(
				RESOURCE_LOCATIONS);
	}
    }
}
