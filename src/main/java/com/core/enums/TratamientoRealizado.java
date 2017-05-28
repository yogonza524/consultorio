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
public enum TratamientoRealizado {
    
    OBTURACION("OBTURACION","red"),
    PIEZA_EXTRAIDA("PIEZA_EXTRAIDA", "grey"),
    ENDODONCIA_REALIZADA("ENDODONCIA REALIZADA", "black"),
    CORONA("CORONA", "brown"),
    TRATAMIENTO_DE_CONDUCTO("TRATAMIENTO DE CONDUCTO", "green")
    ;
    
    final String nombre;
    final String color;

    TratamientoRealizado(String nombre, String color){
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
