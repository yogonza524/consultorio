/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.services;

import com.core.dao.models.Ficha;
import com.core.dao.models.Paciente;
import com.core.enums.PacienteAttribute;
import com.core.util.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
@Service
public class PacienteService {
    
    private String error;
    
    public String getError(){
        return this.error;
    }
    
    public boolean hasError(){
        return error != null && !error.isEmpty();
    }
    
    public boolean add(Paciente p){
        boolean result = false;
        if (p != null) {
            String id = UUID.randomUUID().toString().substring(0,32);
            String query = "INSERT INTO pacientes(\n" + "id," +
"            nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, \n" +
"            obra_social, fecha_de_nacimiento, observaciones)\n" +
"    VALUES ('" + id + "', '" + p.getNombreYapellido() + "', '" + p.getDni() + "', '" + 
                    p.getDomicilio() + "', '" + p.getTelefonoFijo() + "', '" + 
                    p.getTelefonoMovil() + "', '" + p.getObraSocial() + "', " +
                    (p.getFechaDeNacimiento() != null && !p.getFechaDeNacimiento().isEmpty()? "'" +  p.getFechaDeNacimiento() + "'" : "'01/01/1900'") + ", '" + p.getObservaciones() + "')";
            try {
                Conexion.getInstancia().insertar(query);
                
                //Creo la ficha
                FichaService fs = new FichaService();
                
                Ficha f = new Ficha();
                f.setId(id);
                f.setBruxismo(false);
                f.setCuantosFuma("No especificado");
                f.setMiorelajante(false);
                f.setUsaPlaca(false);
                f.setOclusion("No especificado");
                f.setCantidadDePiezasExistentes(32);
                
                result = fs.add(f);
                error = "";
                
            } catch (Exception e) {
                error = e.getMessage();
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public List<Paciente> all(){
        List<Paciente> result = null;
        try {
            List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT * FROM pacientes");
            if (query != null && !query.isEmpty()) {
                result = new ArrayList<>();
                for(HashMap<String,Object> record : query){
                    Paciente p = new Paciente();
                    p.setDni(record.get("dni").toString());
                    p.setId(record.get("id").toString());
                    p.setDomicilio(record.get("domicilio").toString());
                    p.setNombreYapellido(record.get("nombre_y_apellido").toString());
                    p.setObraSocial(record.get("obra_social").toString());
                    p.setObservaciones(record.get("observaciones").toString());
                    p.setTelefonoFijo(record.get("telefono_fijo").toString());
                    p.setTelefonoMovil(record.get("telefono_movil").toString());
                    p.setFechaDeNacimiento(record.get("fecha_de_nacimiento").toString());
                    
                    result.add(p);
                    
                    System.out.println(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public Paciente byId(String id){
        Paciente result = null;
        if (id != null && !id.isEmpty()) {
            try {
                List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT * FROM pacientes p WHERE p.id = '" + id + "'");
                if (query != null && !query.isEmpty()) {
                    result = new Paciente();
                    HashMap<String,Object> r = query.get(0);
                    result.setDni(r.get("dni").toString());
                    result.setDomicilio(r.get("domicilio").toString());
                    result.setFechaDeNacimiento(r.get("fecha_de_nacimiento").toString());
                    result.setId(r.get("id").toString());
                    result.setNombreYapellido(r.get("nombre_y_apellido").toString());
                    result.setObraSocial(r.get("obra_social").toString());
                    result.setObservaciones(r.get("observaciones").toString());
                    result.setTelefonoFijo(r.get("telefono_fijo").toString());
                    result.setTelefonoMovil(r.get("telefono_movil").toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public Paciente byDni(String dni){
        Paciente result = null;
        if (dni != null && !dni.isEmpty()) {
            try {
                List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT * FROM pacientes p WHERE p.dni = '" + dni + "'");
                if (query != null && !query.isEmpty()) {
                    result = new Paciente();
                    HashMap<String,Object> r = query.get(0);
                    result.setDni(r.get("dni").toString());
                    result.setDomicilio(r.get("domicilio").toString());
                    result.setFechaDeNacimiento(r.get("fecha_de_nacimiento").toString());
                    result.setId(r.get("id").toString());
                    result.setNombreYapellido(r.get("nombre_y_apellido").toString());
                    result.setObraSocial(r.get("obra_social").toString());
                    result.setObservaciones(r.get("observaciones").toString());
                    result.setTelefonoFijo(r.get("telefono_fijo").toString());
                    result.setTelefonoMovil(r.get("telefono_movil").toString());
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public int count(){
        int result = 0;
        try {
            List<HashMap<String, Object>> query = Conexion.getInstancia().consultar("SELECT COUNT(*) INTO count FROM pacientes");
            
            if (query != null && !query.isEmpty()) {
                result = Double.valueOf(query.get(0).get("count").toString()).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public List<Paciente> find(String token){
        List<Paciente> result = null;
        if (token != null && !token.isEmpty()) {
            try {
                List<HashMap<String, Object>> q = Conexion.getInstancia()
                        .consultar("SELECT * FROM pacientes p WHERE p.id LIKE '%' || '" + token + "'|| '%'"
                                + " OR p.nombre_y_apellido LIKE '%' || '" + token + "' || '%'"
                                + " OR p.observaciones LIKE '%' || '" + token + "' || '%'"
                                + " OR p.dni LIKE '%' || '" + token + "' || '%'"
                                + " OR p.domicilio LIKE '%' || '" + token + "' || '%'"
                                + ";");
                if (q != null && !q.isEmpty()) {
                    result = new ArrayList<>();
                    
                    for(HashMap<String,Object> r : q){
                        Paciente p = new Paciente();
                        p.setDni(r.get("dni").toString());
                        p.setDomicilio(r.get("domicilio").toString());
                        p.setId(r.get("id").toString());
                        p.setNombreYapellido(r.get("nombre_y_apellido").toString());
                        p.setObraSocial(r.get("obra_social").toString());
                        p.setFechaDeNacimiento(r.get("fecha_de_nacimiento").toString());
                        p.setTelefonoFijo(r.get("telefono_fijo").toString());
                        p.setTelefonoMovil(r.get("telefono_movil").toString());
                        p.setObservaciones(r.get("observaciones").toString());
                        
                        result.add(p);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    
    public boolean update(Paciente p){
        System.out.println(p);
        boolean result = false;
        if (p != null && p.getId() != null && !p.getId().isEmpty()) {
            try {
                String query = "UPDATE pacientes p SET"
                        + " nombre_y_apellido = '" + p.getNombreYapellido() + "',"
                        + " domicilio = '" + p.getDomicilio() + "',"
                        + " dni = '" + p.getDni()+ "',"
                        + " telefono_fijo = '" + p.getTelefonoFijo() + "',"
                        + " telefono_movil = '" + p.getTelefonoMovil() + "',"
                        + " obra_social = '" + p.getObraSocial() + "',"
                        + " observaciones = '" + p.getObservaciones() + "',"
                        + " fecha_de_nacimiento = '" + p.getFechaDeNacimiento() + "'"
                        + " WHERE p.id = '" + p.getId() +"'";
                System.out.println(query);
                result = Conexion.getInstancia().update(query);
                error = "";
            } catch (Exception e) {
                error = e.getMessage();
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public void updateFields(Paciente p, PacienteAttribute... attrs){
        if (p != null && p.getId() != null && !p.getId().isEmpty()) {
            if (attrs.length == 1) {
            
                return;
            }
            
            if (attrs.length > 1) {
                String query = "UPDATE pacientes p SET ";
                
                for(PacienteAttribute attr : attrs){
                    String field = "";
                    switch(attr){
                        case DNI: field = "dni"; break;
                        case DOMICILIO : field = "domicilio"; break;
                        case ID : field = "id"; break;
                        case NOMBREYAPELLIDO : field = "nombre_y_apellido"; break;
                        case OBSERVACIONES : field = "observaciones"; break;
                        case TELEFONO_FIJO : field = "telefono_fijo"; break;
                        case TELEFONO_MOVIL : field = "telefono_movil"; break;
                    }
                    query += field + "='" + p.attr(attr) + "' AND ";
                }
                query = query.substring(0, query.length() - 4);
                
                try {
                    Conexion.getInstancia().actualizar(query);
                } catch (SQLException ex) {
                    Logger.getLogger(PacienteService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void remove(String id){
        try {
            Conexion.getInstancia().borrar("DELETE FROM pacientes p WHERE p.id='" + id + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void remove(Paciente p){
        if (p != null && p.getId() != null && !p.getId().isEmpty()) {
            this.removeBy(PacienteAttribute.ID, p.getId());
        }
    }
    
    public void removeBy(PacienteAttribute attr, String val){
        try {
            String field = "";
            switch(attr){
                case DNI: field = "dni"; break;
                case DOMICILIO : field = "domicilio"; break;
                case ID : field = "id"; break;
                case NOMBREYAPELLIDO : field = "nombre_y_apellido"; break;
                case OBSERVACIONES : field = "observaciones"; break;
                case TELEFONO_FIJO : field = "telefono_fijo"; break;
                case TELEFONO_MOVIL : field = "telefono_movil"; break;
            }
            Conexion.getInstancia().borrar("DELETE FROM pacientes p WHERE p." + field + "='" + val + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean exists(String id){
        boolean result = false;
        try {
            List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT COUNT(*) FROM pacientes p WHERE p.id='" + id + "'");
            if (query != null && !query.isEmpty()) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
