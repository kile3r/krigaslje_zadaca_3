/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.composite;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kile
 */
public class Podrucje implements Naselje {
    
    String id;
    String naziv;
    boolean ishodiste;
    List<Naselje> potPodrucja = new ArrayList<>();

    public Podrucje(String id, String naziv) {
        this.id = id;
        this.naziv = naziv;
        this.ishodiste = true;
        this.potPodrucja = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public boolean isIshodiste() {
        return ishodiste;
    }

    public void setIshodiste(boolean ishodiste) {
        this.ishodiste = ishodiste;
    }

    public List<Naselje> getPotPodrucja() {
        return potPodrucja;
    }

    public void setPotPodrucja(List<Naselje> potPodrucja) {
        this.potPodrucja = potPodrucja;
    } 
}
