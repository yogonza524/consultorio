/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.services;

import com.core.dao.models.Paciente;
import com.core.enums.PacienteAttribute;
import com.core.util.Conexion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        return !error.isEmpty();
    }
    
    public boolean add(Paciente p){
        boolean result = false;
        if (p != null) {
            String query = "INSERT INTO pacientes(\n" +
"            nombre_y_apellido, dni, domicilio, telefono_fijo, telefono_movil, \n" +
"            obra_social, fecha_de_nacimiento, observaciones)\n" +
"    VALUES ('" + p.getNombreYapellido() + "', '" + p.getDni() + "', '" + 
                    p.getDomicilio() + "', '" + p.getTelefonoFijo() + "', '" + 
                    p.getTelefonoMovil() + "', '" + p.getObraSocial() + "', " +
                    (p.getFechaDeNacimiento() != null && !p.getFechaDeNacimiento().isEmpty()? "'" +  p.getFechaDeNacimiento() + "'" : "'01/01/1900'") + ", '" + p.getObservaciones() + "')";
            try {
                Conexion.getInstancia().insertar(query);
                result = true;
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
    
    public void update(Paciente p){
        if (p != null && p.getId() != null && !p.getId().isEmpty()) {
            try {
                String query = "UPDATE pacientes p SET"
                        + " nobre_y_apellido = '" + p.getNombreYapellido() + "'"
                        + " domicilio = '" + p.getDomicilio() + "'"
                        + " dni = '" + p.getDni()+ "'"
                        + " telefono_fijo = '" + p.getTelefonoFijo() + ""
                        + " telefono_movil = '" + p.getTelefonoMovil() + "'"
                        + " obra_social = '" + p.getObraSocial() + "'"
                        + " WHERE p.id = '" + p.getId() +"'";
                Conexion.getInstancia().actualizar(query);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
