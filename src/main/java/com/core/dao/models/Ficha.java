/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.dao.models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Usuario
 */
public class Ficha implements Serializable{
    
    private String id;
    private boolean fuma;
    private String cuantosFuma;
    private String oclusion;
    private boolean bruxismo;
    private String tipoBruxismo;
    private boolean usaPlaca;
    private boolean miorelajante;
    private int cantidadDePiezasExistentes;

    public Ficha() {
    }

    public Ficha(String id, boolean fuma, String cuantosFuma, String oclusion, boolean bruxismo, String tipoBruxismo, boolean usaPlaca, boolean miorelajante, int cantidadDePiezasExistentes) {
        this.id = id;
        this.fuma = fuma;
        this.cuantosFuma = cuantosFuma;
        this.oclusion = oclusion;
        this.bruxismo = bruxismo;
        this.tipoBruxismo = tipoBruxismo;
        this.usaPlaca = usaPlaca;
        this.miorelajante = miorelajante;
        this.cantidadDePiezasExistentes = cantidadDePiezasExistentes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isFuma() {
        return fuma;
    }

    public void setFuma(boolean fuma) {
        this.fuma = fuma;
    }

    public String getCuantosFuma() {
        return cuantosFuma;
    }

    public void setCuantosFuma(String cuantosFuma) {
        this.cuantosFuma = cuantosFuma;
    }

    public String getOclusion() {
        return oclusion;
    }

    public void setOclusion(String oclusion) {
        this.oclusion = oclusion;
    }

    public boolean isBruxismo() {
        return bruxismo;
    }

    public void setBruxismo(boolean bruxismo) {
        this.bruxismo = bruxismo;
    }

    public String getTipoBruxismo() {
        return tipoBruxismo;
    }

    public void setTipoBruxismo(String tipoBruxismo) {
        this.tipoBruxismo = tipoBruxismo;
    }

    public boolean isUsaPlaca() {
        return usaPlaca;
    }

    public void setUsaPlaca(boolean usaPlaca) {
        this.usaPlaca = usaPlaca;
    }

    public boolean isMiorelajante() {
        return miorelajante;
    }

    public void setMiorelajante(boolean miorelajante) {
        this.miorelajante = miorelajante;
    }

    public int getCantidadDePiezasExistentes() {
        return cantidadDePiezasExistentes;
    }

    public void setCantidadDePiezasExistentes(int cantidadDePiezasExistentes) {
        this.cantidadDePiezasExistentes = cantidadDePiezasExistentes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + (this.fuma ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.cuantosFuma);
        hash = 71 * hash + Objects.hashCode(this.oclusion);
        hash = 71 * hash + (this.bruxismo ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.tipoBruxismo);
        hash = 71 * hash + (this.usaPlaca ? 1 : 0);
        hash = 71 * hash + (this.miorelajante ? 1 : 0);
        hash = 71 * hash + this.cantidadDePiezasExistentes;
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
        final Ficha other = (Ficha) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ficha{" + "id=" + id + ", fuma=" + fuma + ", cuantosFuma=" + cuantosFuma + ", oclusion=" + oclusion + ", bruxismo=" + bruxismo + ", tipoBruxismo=" + tipoBruxismo + ", usaPlaca=" + usaPlaca + ", miorelajante=" + miorelajante + ", cantidadDePiezasExistentes=" + cantidadDePiezasExistentes + '}';
    }
}
