/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epn;

/**
 *
 * @author Aspire
 */
public class Facultad {

    public Facultad(int idfacultad, String nombrefacultad) {
        this.idfacultad = idfacultad;
        this.nombrefacultad = nombrefacultad;
    }
    
    int idfacultad;
    String nombrefacultad;

    public Facultad() {
    }

    
    public int getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(int idfacultad) {
        this.idfacultad = idfacultad;
    }

    public String getNombrefacultad() {
        return nombrefacultad;
    }

    public void setNombrefacultad(String nombrefacultad) {
        this.nombrefacultad = nombrefacultad;
    }
    
}
