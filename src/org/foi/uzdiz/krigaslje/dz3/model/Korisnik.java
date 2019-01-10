/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.model;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dz3.singleton.RndGenerator;

/**
 *
 * @author kile
 */
public class Korisnik {

    public int id;
    public int tip;//1 mali, 2 srednji, 3 veliki
    public float otpadStaklo;
    public float otpadPapir;
    public float otpadMetal;
    public float otpadBio;
    public float otpadMjesano;
    public static List<Korisnik> listaKorisnika = new ArrayList<>();
    public List<Spremnik> dodijeljeniSpremnici;

    public Korisnik(int k) {
        tip = k;
        dodajID();
        dodijeljeniSpremnici = new ArrayList<>();
        listaKorisnika.add(this);
    }

    private void dodajID() {
        int noviid = 1;
        while (postojiID(noviid)) {
            noviid++;
        }
        this.id = noviid;
    }

    private boolean postojiID(int nid) {
        for (Korisnik k : listaKorisnika) {
            if (k.id == nid) {
                return true;
            }
        }
        return false;
    }

//    public String info() {
//        String info = "Korisnik " + id + "(" + tip + ")\n";
//        info += "Spremnici: ";
//        for (Spremnik s : dodijeljeniSpremnici) {
//            info += s.naziv + " " + s.id + " | ";
//        }
//        return info;
//    }
    public void generirajOtpad() {
        if (tip == 1) {
            generirajOtpadMali();
        }
        if (tip == 2) {
            generirajOtpadSrednji();
        }
        if (tip == 3) {
            generirajOtpadVeliki();
        }
    }

    public void generirajOtpadMali() {
        int min = Parametri.getMaliMin();
        otpadStaklo = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getMaliStaklo()), Parametri.getMaliStaklo());
        otpadPapir = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getMaliPapir()), Parametri.getMaliPapir());
        otpadMetal = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getMaliMetal()), Parametri.getMaliMetal());
        otpadBio = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getMaliBio()), Parametri.getMaliBio());
        otpadMjesano = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getMaliMjesano()), Parametri.getMaliMjesano());

    }

    public void generirajOtpadSrednji() {
        int min = Parametri.getSrednjiMin();
        otpadStaklo = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getSrednjiStaklo()), Parametri.getSrednjiStaklo());
        otpadPapir = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getSrednjiPapir()), Parametri.getSrednjiPapir());
        otpadMetal = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getSrednjiMetal()), Parametri.getSrednjiMetal());
        otpadBio = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getSrednjiBio()), Parametri.getSrednjiBio());
        otpadMjesano = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getSrednjiMjesano()), Parametri.getSrednjiMjesano());
    }

    public void generirajOtpadVeliki() {
        int min = Parametri.getVelikiMin();
        otpadStaklo = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getVelikiStaklo()), Parametri.getVelikiStaklo());
        otpadPapir = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getVelikiPapir()), Parametri.getVelikiPapir());
        otpadMetal = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getVelikiMetal()), Parametri.getVelikiMetal());
        otpadBio = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getVelikiBio()), Parametri.getVelikiBio());
        otpadMjesano = RndGenerator.getRndGeneratorInstance().dajSlucajniBrojsDecimalama((float) ((min / 100.0) * Parametri.getVelikiMjesano()), Parametri.getVelikiMjesano());

    }

    public void baciOtpad() {
        for (Spremnik s : dodijeljeniSpremnici) {
            if (s.naziv.equals("staklo")) {
                otpadStaklo = otpadStaklo - s.dodajOtpad(this, otpadStaklo);
            }
            if (s.naziv.equals("papir")) {
                otpadPapir = otpadPapir - s.dodajOtpad(this, otpadPapir);
            }
            if (s.naziv.equals("metal")) {
                otpadMetal = otpadMetal - s.dodajOtpad(this, otpadMetal);
            }
            if (s.naziv.equals("bio")) {
                otpadBio = otpadBio - s.dodajOtpad(this, otpadBio);
            }
            if (s.naziv.equals("mješano")) {
                otpadMjesano = otpadMjesano - s.dodajOtpad(this, otpadMjesano);
            }
        }
    }

}
