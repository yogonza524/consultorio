/*
 * To change this license header, choose License Headers in Project Properties.
 * To change thi
s template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.services;

import com.core.dao.models.Ficha;
import com.core.util.Conexion;
import java.util.HashMap;
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
        if (f != null && !f.getId().isEmpty()) {
            try {
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
