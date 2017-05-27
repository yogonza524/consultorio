package com.core.app;

import com.core.dao.models.Paciente;
import com.core.dao.models.Usuario;
import com.core.enums.UserGroup;
import com.core.models.Busqueda;
import com.core.models.UserLogin;
import com.core.services.PacienteService;
import com.core.services.UsuarioService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
    
    private final String logoName = "Consultorio";
    
    //Inject dependencies
    @Autowired private UsuarioService usuarioService;
    @Autowired private PacienteService pacienteService;
    
    @GetMapping("/")
    public String index(Model model){
        
        return "index";
    }
    
    @RequestMapping("/login")
    public String login( Model model) {
        model.addAttribute("userlogin", new UserLogin());
        return "login";
    }
    
    @RequestMapping("/home")
    public String home(Model model) {
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        return "home";
    }
    
    @RequestMapping("/pacientes")
    public String pacientes(Model model) {
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        model.addAttribute("pacientes", pacienteService.all());
        
        return "pacientes";
    }
    
    @RequestMapping("/turnos")
    public String turnos(Model model) {
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        return "turnos";
    }
    
    @RequestMapping("/nuevo_paciente")
    public String nuevoPaciente(Model model,
            HttpServletRequest request,
            HttpServletResponse response) {
        
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        model.addAttribute("nuevoPaciente", request.getSession().getAttribute("nuevoPaciente") != null? (Paciente)request.getSession().getAttribute("nuevoPaciente") : new Paciente());
        //Add a secure secret
        String secret = UUID.randomUUID().toString();
        request.getSession().setAttribute("secret", secret);
        model.addAttribute("secret", secret);
        
        return "nuevo_paciente";
    }
    
    @PostMapping("/nuevo_paciente")
    public String processNuevoPaciente(Model model,
            @ModelAttribute("nuevoPaciente") Paciente nuevoPaciente,
            @ModelAttribute("secret") String secret,
            HttpServletRequest request,
            HttpServletResponse response){
        
        boolean hasError = false;
        if (nuevoPaciente != null && request.getSession().getAttribute("secret").toString().equals(secret)) {
            
            boolean success = false;
            if (pacienteService.add(nuevoPaciente)) {
                success = true;
                System.out.println("Agregado!");
            }
            else{
                System.out.println("Error al agregar " + pacienteService.getError());
                hasError = true;
                model.addAttribute("hasError", hasError);
                model.addAttribute("errorMessage", pacienteService.getError());
            }
            
            model.addAttribute("nuevoPaciente", new Paciente());
            model.addAttribute("success", success);
        }
        else{
            hasError = true;
        }
        
        model.addAttribute("hasError", hasError);
        model.addAttribute("buscar", new Busqueda());
        model.addAttribute("logoName", logoName);
        
        return "/nuevo_paciente";
    }
    
    @PostMapping("/busqueda")
    public String buscar(@ModelAttribute("search") String search, 
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        model.addAttribute("buscado", search);
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        
        List<String> l = new ArrayList<>();
        l.add("Primero");
        l.add("Segundo");
        l.add("Tercero");
        
        model.addAttribute("rows", l);
        
        System.out.println("Buscado: " + search);
        
        return "busqueda";
    }
    
    @PostMapping("/processLogin")
    public String processLogin(@ModelAttribute("userlogin")  UserLogin userlogin, 
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        model.addAttribute("buscar", new Busqueda());
        
        if (userlogin != null && !userlogin.getPassword().isEmpty() && !userlogin.getUsername().isEmpty()) {
            String username = userlogin.getUsername();
            String pass = userlogin.getPassword();
            
            Usuario u = usuarioService.find(username, pass);
            
            if (u != null && u.getGroup().equals(UserGroup.USER.name())) {
                try {
                    request.getSession().setAttribute("USER_IN", true);
                    request.getSession().setAttribute("GROUP", "USER");
                    
                    //Update time of last conection
                    usuarioService.login(username, pass);
                    
                    response.sendRedirect(request.getContextPath() + "/home");
                } catch (IOException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "home";
            }
            else{
                try {
                    response.sendRedirect(request.getContextPath() + "/login");
                } catch (IOException ex) {
                    Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return "home";
            }
        }
        return "login";
    }
    
    @RequestMapping("/modificar_paciente")
    public String modificarPaciente(Model model,
            @RequestParam(value = "id", defaultValue = "No seleccionado") String id,
            HttpServletRequest request,
            HttpServletResponse response){
        
        model.addAttribute("buscar", new Busqueda());
        model.addAttribute("logoName", logoName);
        
        if (id.equals("No seleccionado")) {
            try {
                response.sendRedirect(request.getContextPath() + "/modificar_pacientes");
                return "/modificar_pacientes";
            } catch (IOException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        return "modificar_paciente";
    }
    
    @RequestMapping("/modificar_pacientes")
    public String modificarPacientes(Model model){
        
        model.addAttribute("pacientes", pacienteService.all());
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        return "/modificar_pacientes";
    }
    
    @RequestMapping("/ficha")
    public String ficha(Model model,
            @RequestParam(value="id", defaultValue = "sin parametro") String id,
            HttpServletRequest request,
            HttpServletResponse response){
        
        model.addAttribute("pacientes", pacienteService.all());
        model.addAttribute("logoName", logoName);
        model.addAttribute("buscar", new Busqueda());
        
        if (id.equals("sin parametro")) {
            try {
                response.sendRedirect(request.getContextPath() + "/pacientes");
                return "pacientes";
            } catch (IOException ex) {
                Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        Paciente paciente = pacienteService.byId(id);
        if (paciente != null) {
            model.addAttribute("paciente", paciente);
            model.addAttribute("noPacient", false);
        }
        else{
            model.addAttribute("noPacient", true);
        }
        
        return "/ficha";
    }
}
