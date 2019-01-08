/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kile
 */
public class Vozilo {

//    id;naziv;tip: 0 - dizel, 1 - električni;vrsta: 0 - staklo, 1 - papir, 2 - metal, 3 - bio, 4 - mješano; nosivost (kg);vozači
//v1;staklarko 1;1;0;4000;pero, ivo
    String id;
    String naziv;
    int tip;
    int vrsta;
    int nosivost;
    int status;  // 0-pripremi 1- kvar 2- kontorla 3- isprazni
    public List<Vozac> listaVozaca;

    public Vozilo() {
    }

    public Vozilo(String[] zapisi) {
        id = zapisi[0];
        naziv = zapisi[1];
        tip = Integer.parseInt(zapisi[2]);
        vrsta = Integer.parseInt(zapisi[3]);
        nosivost = Integer.parseInt(zapisi[4]);
        listaVozaca = dodajVozace(zapisi[5]);
        status = 0;

    }

    private List<Vozac> dodajVozace(String zapis) {
        
        List<Vozac> listaVozaca = new ArrayList<>();
        String[] vozaci = null;
        vozaci = zapis.split(",");
        for (String s : vozaci) {
            Vozac v = new Vozac(s);
            listaVozaca.add(v);
        }
        return listaVozaca;
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

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }

    public int getNosivost() {
        return nosivost;
    }

    public void setNosivost(int nosivost) {
        this.nosivost = nosivost;
    }

    public List<Vozac> getListaVozaca() {
        return listaVozaca;
    }

    public void setListaVozaca(List<Vozac> listaVozaca) {
        this.listaVozaca = listaVozaca;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    

}
