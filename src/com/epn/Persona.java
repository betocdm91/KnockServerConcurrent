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
public class Persona {

    public Persona(int idpersona, String usuario, String contaseña) {
        this.idpersona = idpersona;
        this.usuario = usuario;
        this.contaseña = contaseña;
    }

    public Persona() {
    }

    int idpersona;
    String usuario;
    String contaseña;

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContaseña() {
        return contaseña;
    }

    public void setContaseña(String contaseña) {
        this.contaseña = contaseña;
    }
    
    
}
