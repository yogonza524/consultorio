/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.enums;

/**
 *
 * @author Usuario
 */
public enum TratamientoArealizar {
    
    CARIES("CARIES","blue"),
    ENDODONCIA("ENDODONCIA", "grey"),
    PIEZA_NO_ERUPCIONADA("PIEZA NO ERUPCIONADA", "black"),
    PIEZA_A_EXTRAER("PIEZA A EXTRAER", "yellow"),
    CORONA_A_REALIZAR("CORONA A REALIZAR", "brown"),
    GINGIVITIS_MARGINAL_CRONICA("GINGIVITIS MARGINAL CRONICA", "pink"),
    PERIODONTITIS_MARGINAL("PERIODONTITIS MARGINAL", "purple"),
    ABRACIONES("ABRACIONES", "#CCC")
    ;
    
    final String nombre;
    final String color;

    TratamientoArealizar(String nombre, String color){
        this.nombre = nombre;
        this.color = color;
    }

    public String nombre(){
        return this.nombre;
    }

    public String color(){
        return this.color;
    }

    
}
