/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.model;

/**
 *
 * @author kile
 */
public class Vozac {

    String ime;
    int status; // case - 0-vozi 1-god odmor 2-bolovanje 3-otkaz

    public Vozac(String ime) {
        this.ime = ime;
    }

    public Vozac(String ime, int status) {
        this.ime = ime;
        this.status = status;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
