/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.services;

import com.core.dao.models.Usuario;
import com.core.util.Conexion;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class UsuarioService {

    public Usuario find(String username){
        Usuario result = null;
        try {
            List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT * FROM usuarios u WHERE u.username = '" + username + "' LIMIT 1");
            
            if (query != null && !query.isEmpty()) {
                result = new Usuario();
                result.setId(query.get(0).get("id").toString());
                result.setGroup(query.get(0).get("group").toString());
                result.setUsername(username);
                result.setPassword(query.get(0).get("password").toString());
//                result.setUltimaConexion(toDate(query.get(0).get("ultima_conexion").toString()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }
    
    public Usuario find(String username, String password){
        Usuario result = null;
        try {
            List<HashMap<String,Object>> query = Conexion.getInstancia()
                    .consultar("SELECT * FROM usuarios u WHERE u.username = '" + username 
                            + "' AND u.password = '" + password + "' LIMIT 1");
            
            if (query != null && !query.isEmpty()) {
                result = new Usuario();
                result.setId(query.get(0).get("id").toString());
                result.setGroup(query.get(0).get("group").toString());
                result.setUsername(username);
                result.setPassword(query.get(0).get("password").toString());
//                result.setUltimaConexion(toDate(query.get(0).get("ultima_conexion") != null ?
//                        query.get(0).get("ultima_conexion").toString() : ""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return result;
    }
    
    public boolean add(Usuario u){
        if (u != null) {
            try {
                return Conexion.getInstancia().insertar("INSERT INTO usuarios(username, password, group) VALUES("
                        + "'" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getGroup() + "'");
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    public int count(){
        int result = 0;
        try {
            List<HashMap<String, Object>> query = Conexion.getInstancia().consultar("SELECT COUNT(*) INTO count FROM usuarios");
            
            if (query != null && !query.isEmpty()) {
                result = Double.valueOf(query.get(0).get("count").toString()).intValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void remove(Usuario u){
        if (u != null) {
            try {
                Conexion.getInstancia().borrar("DELETE FROM usuarios u WHERE u.id = '" + u.getId() + "' OR u.username = '" + u.getUsername() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void update(Usuario u){
        
        if (u != null) {
            try {
                Conexion.getInstancia().actualizar("UPDATE usuario SET username = '" + u.getUsername() + "', password = '" + u.getPassword() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Date toDate(String toString) {
        Date result = null;
        
        String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSX";
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
        try {
            result = sdf.parse(toString);
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public void login(String username, String password){
        try {
            Conexion.getInstancia().actualizar("UPDATE usuarios u SET ultima_conexion = '" + 
                    new java.sql.Timestamp(new Date().getTime())
                    + "' WHERE u.username = '" + username + "' AND u.password = '" + password + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
