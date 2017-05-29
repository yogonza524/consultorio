/*
 * To change this license header, choose License Headers in Project Properties.
 * To change thi
s template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.services;

import com.core.dao.models.Ficha;
import com.core.enums.FichaAttribute;
import com.core.util.Conexion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class FichaService {
    
    public boolean add(Ficha f){
        boolean result = false;
        if (f != null && f.getId() != null && !f.getId().isEmpty()) {
            try {
                
                //Verificar que ya no existe la ficha en la tabla
                List<HashMap<String, Object>> q = Conexion.getInstancia().consultar("SELECT id FROM ficha f WHERE id = '" + f.getId() + "'");
                if (q != null && !q.isEmpty()) {
                    //Ya existe el id de la ficha
                    return false;
                }
                
                String query = "INSERT INTO ficha(" + "id, " +
                        "fuma, " + (f.getCuantosFuma() != null && !f.getCuantosFuma().isEmpty() ? "cuantos_fuma, " : "") +
                        (f.getOclusion() != null && !f.getOclusion().isEmpty() ? "oclusion, " : "") + 
                        "bruxismo, " + (f.getTipoBruxismo() != null && !f.getTipoBruxismo().isEmpty() ? "tipo_bruxismo, " : "") +
                        "usa_placa, " + "miorrelajante, " + "cantidad_de_piezas_existentes" + ")"
                        + " VALUES('" + f.getId() + "', " + f.isFuma() + ", '" + 
                        (f.getCuantosFuma() != null && !f.getCuantosFuma().isEmpty() ? f.getCuantosFuma() : "") + "', '" + 
                        (f.getOclusion() != null && !f.getOclusion().isEmpty() ? f.getOclusion() : "") + "', " + 
                        f.isBruxismo() + ", '" + (f.getTipoBruxismo() != null && !f.getTipoBruxismo().isEmpty() ? f.getTipoBruxismo() : "") + "', " +
                        f.isUsaPlaca() + ", " + f.isMiorelajante() + ", " + f.getCantidadDePiezasExistentes() + ")";
                
                String consulta = "INSERT INTO ficha(id, fuma, cuantos_fuma, bruxismo, tipo_bruxismo, usa_placa, miorrelajante"
                        + ", cantidad_de_piezas_existentes) VALUES('" + f.getId() + "', " + f.isFuma() + ", '" + (f.getCuantosFuma() != null && !f.getCuantosFuma().isEmpty() ? f.getCuantosFuma() : "") + "', "
                        + "" + f.isBruxismo() + ", '" + (f.getTipoBruxismo() != null && !f.getTipoBruxismo().isEmpty() ? f.getTipoBruxismo() : "") + "', "
                        + "" + f.isUsaPlaca() + ", " + f.isMiorelajante() + ", " + f.getCantidadDePiezasExistentes() + ")";
                
                System.out.println(consulta);
                Conexion.getInstancia().insertar(consulta);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public List<Ficha> all(){
        List<Ficha> result = null;
        try {
            String query = "SELECT * FROM ficha";
            List<HashMap<String,Object>> q = Conexion.getInstancia().consultar(query);
            
            if (q != null && !q.isEmpty()) {
                result = new ArrayList<>();
                for(HashMap<String,Object> r : q){
                    Ficha f = new Ficha();
                    f.setBruxismo(r.get("bruxismo") != null ? Boolean.valueOf(r.get("bruxismo").toString()) : false);
                    f.setCantidadDePiezasExistentes(r.get("cantidad_de_piezas_existentes") != null ? Double.valueOf(r.get("cantidad_de_piezas_existentes").toString()).intValue() : 0);
                    f.setCuantosFuma(r.get("cuantos_fuma").toString());
                    f.setOclusion(r.get("oclusion").toString());
                    f.setFuma(r.get("fuma") != null ? Boolean.valueOf(r.get("fuma").toString()) : false);
                    f.setId(r.get("id").toString());
                    f.setMiorelajante(r.get("miorelajante") != null ? Boolean.valueOf(r.get("miorelajante").toString()) : false);
                    f.setTipoBruxismo(r.get("tipo_bruxismo").toString());
                    f.setUsaPlaca(r.get("usa_placa") != null ? Boolean.valueOf(r.get("usa_placa").toString()) : false);
                    
                    result.add(f);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public int count(){
        int result = 0;
        try {
            List<HashMap<String,Object>> query = Conexion.getInstancia().consultar("SELECT COUNT(*) INTO count FROM ficha");
            if (query != null && !query.isEmpty()) {
                result = Integer.valueOf(query.get(0).get("count").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean removeById(String id){
        boolean result = false;
        try {
            if (id != null && !id.isEmpty()) {
                Conexion.getInstancia().borrar("DELETE FROM ficha f WHERE f.id = '" + id + "'");
                List<HashMap<String, Object>> query = Conexion.getInstancia().consultar("SELECT id FROM ficha f WHERE f.id = '" + id + "'");
                if (query != null && query.isEmpty()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean updateBy(FichaAttribute attr, String id, Object value){
        boolean result = false;
        if (id != null && !id.isEmpty()) {
            try {
                
                String field = "";
                switch(attr){
                    case BRUXISMO : field = "bruxismo"; break;
                    case CANTIDAD_DE_PIEZAS_EXISTENTES : field = "cantidad_de_piezas_existentes"; break;
                    case CUANTOS_FUMA : field = "cuantos_fuma"; break;
                    case FUMA : field = "fuma"; break;
                    case MIORRELAJANTE : field = "miorrelajante"; break;
                    case OCLUSION : field = "oclusion"; break;
                    case TIPO_BRUXISMO : field = "tipo_bruxismo"; break;
                    case USA_PLACA : field = "usa_placa"; break;
                }
                int rows = Conexion.getInstancia().actualizar("UPDATE ficha SET " + field + "= " + (value instanceof String ? "'" : "") + value + (value instanceof String ? "'" : ""));
                if (rows == 1) {
                    result = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
