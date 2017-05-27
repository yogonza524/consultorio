/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.core.dao.models;

import com.core.enums.PacienteAttribute;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author Gonzalo H. Mendoza
 * email: yogonza524@gmail.com
 * StackOverflow: http://stackoverflow.com/users/5079517/gonza
 */
public class Paciente implements Serializable{

    private String id;
    private String nombreYapellido;
    private String dni;
    private String domicilio;
    private String telefonoFijo;
    private String telefonoMovil;
    private String obraSocial;
    private String fechaDeNacimiento;
    private String observaciones;

    public Paciente() {
    }

    public Paciente(String id, String nombreYapellido, String dni, String domicilio, String telefonoFijo, String telefonoMovil, String obraSocial, String fechaDeNacimiento, String observaciones) {
        this.id = id;
        this.nombreYapellido = nombreYapellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.obraSocial = obraSocial;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreYapellido() {
        return nombreYapellido;
    }

    public void setNombreYapellido(String nombreYapellido) {
        this.nombreYapellido = nombreYapellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nombreYapellido);
        hash = 67 * hash + Objects.hashCode(this.dni);
        hash = 67 * hash + Objects.hashCode(this.domicilio);
        hash = 67 * hash + Objects.hashCode(this.telefonoMovil);
        hash = 67 * hash + Objects.hashCode(this.obraSocial);
        hash = 67 * hash + Objects.hashCode(this.fechaDeNacimiento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public String attr(PacienteAttribute attribute){
        String result = "";
        switch(attribute){
            case DNI: result = this.dni; break;
            case DOMICILIO : result = this.domicilio; break;
            case ID : result = this.id; break;
            case NOMBREYAPELLIDO : result = this.nombreYapellido; break;
            case OBSERVACIONES : result = this.observaciones; break;
            case TELEFONO_FIJO : result = this.telefonoFijo; break;
            case TELEFONO_MOVIL : result = this.telefonoMovil; break;
        }
        return result;
    }

    @Override
    public String toString() {
        return "Paciente{" + "id=" + id + ", nombreYapellido=" + nombreYapellido + ", dni=" + dni + ", domicilio=" + domicilio + ", telefonoFijo=" + telefonoFijo + ", telefonoMovil=" + telefonoMovil + ", obraSocial=" + obraSocial + ", fechaDeNacimiento=" + fechaDeNacimiento + ", observaciones=" + observaciones + '}';
    }
}
