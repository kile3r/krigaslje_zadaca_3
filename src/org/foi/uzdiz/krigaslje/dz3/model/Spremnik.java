/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.model;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.singleton.Ispis;
import org.foi.uzdiz.krigaslje.dz3.main.DataImporter;

/**
 *
 * @author kile
 */
public class Spremnik {

    //naziv;vrsta: 0 - kanta, 1 - kontejner;1 na broj malih;1 na broj srednjih;1 na broj velikih;nosivost (kg)
    public int id;
    public String naziv;
    public int vrsta;
    public int naBrojMalih;
    public int naBrojSrednjih;
    public int naBrojVelikih;
    public int nosivost;

    public float kolicinaOtpada;

    public List<Spremnik> listaSpremnika;
    public List<Korisnik> listaKorisnikaSpremnika;

    public Spremnik() {
    }

    public Spremnik(String[] zapisi) {
        naziv = zapisi[0];
        vrsta = Integer.parseInt(zapisi[1]);
        naBrojMalih = Integer.parseInt(zapisi[2]);
        naBrojSrednjih = Integer.parseInt(zapisi[3]);
        naBrojVelikih = Integer.parseInt(zapisi[4]);
        nosivost = Integer.parseInt(zapisi[5]);
        kolicinaOtpada = 0;
        dodajId();
        listaKorisnikaSpremnika = new ArrayList<>();
        if (listaSpremnika != null) {
            listaSpremnika.add(this);
        }

    }

    public Spremnik(String naziv, int vrsta, int naBrojMalih, int naBrojSrednjih, int naBrojVelikih, int nosivost) {
        this.naziv = naziv;
        this.vrsta = vrsta;
        this.naBrojMalih = naBrojMalih;
        this.naBrojSrednjih = naBrojSrednjih;
        this.naBrojVelikih = naBrojVelikih;
        this.nosivost = nosivost;
        this.listaKorisnikaSpremnika = new ArrayList<>();
    }

    public Spremnik kloniraj() {
        return new Spremnik(naziv, vrsta, naBrojMalih, naBrojSrednjih, naBrojVelikih, nosivost);
    }

    private void dodajId() {
        int nid = 1000;
        while (postojiID(nid)) {
            nid++;
        }
        this.id = nid;
    }

    private boolean postojiID(int nid) {

        if (listaSpremnika == null) {
            return false;
        } else {

            for (Spremnik s : listaSpremnika) {
                if (s.id == nid) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean mozePrimitKorisnika(int i) {
        
        if (i == 1) {
            if (listaKorisnikaSpremnika.size() < naBrojMalih) {
                return true;
            } else {
                return false;
            }
        }
        if (i == 2) {
            if (listaKorisnikaSpremnika.size() < naBrojSrednjih) {
                return true;
            } else {
                return false;
            }
        }
        if (i == 3) {
            if (listaKorisnikaSpremnika.size() < naBrojVelikih) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    float dodajOtpad(Korisnik k, float otpad) {
        int kid = k.id;
        if ((kolicinaOtpada + otpad) <= nosivost) {
            kolicinaOtpada = kolicinaOtpada + otpad;
            Ispis.getInstance().uvjetovaniIspis("Korisnik (" + kid + ") je u spremnik " + naziv + " (" + id + ") bacio " + otpad + "kg otpada");
            return otpad;
        } else if (kolicinaOtpada < nosivost) {
            float diff = nosivost - kolicinaOtpada;
            kolicinaOtpada = nosivost;
            float preostali = otpad - diff;
            Ispis.getInstance().uvjetovaniIspis("Korisnik (" + kid + ") je u spremnik " + naziv + " (" + id + ") bacio " + diff + "kg otpada i napunio kontenjer. Ostalo mu je " + preostali + "kg. ");
            return diff;
        } else {
            Ispis.getInstance().uvjetovaniIspis("Korisnik (" + kid + ") ne može baciti otpad jer je spremnik " + naziv + " (" + id + ") pun!");
            return 0;
        }
    }

    public void popuniListuSpremnika() {

        DataImporter di = new DataImporter();
        listaSpremnika = di.getSpremnici();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrsta() {
        return vrsta;
    }

    public void setVrsta(int vrsta) {
        this.vrsta = vrsta;
    }

    public int getNaBrojMalih() {
        return naBrojMalih;
    }

    public void setNaBrojMalih(int naBrojMalih) {
        this.naBrojMalih = naBrojMalih;
    }

    public int getNaBrojSrednjih() {
        return naBrojSrednjih;
    }

    public void setNaBrojSrednjih(int naBrojSrednjih) {
        this.naBrojSrednjih = naBrojSrednjih;
    }

    public int getNaBrojVelikih() {
        return naBrojVelikih;
    }

    public void setNaBrojVelikih(int naBrojVelikih) {
        this.naBrojVelikih = naBrojVelikih;
    }

    public int getNosivost() {
        return nosivost;
    }

    public void setNosivost(int nosivost) {
        this.nosivost = nosivost;
    }

    public float getKolicinaOtpada() {
        return kolicinaOtpada;
    }

    public void setKolicinaOtpada(float kolicinaOtpada) {
        this.kolicinaOtpada = kolicinaOtpada;
    }

    public List<Spremnik> getListaSpremnika() {
        return listaSpremnika;
    }

    public void setListaSpremnika(List<Spremnik> listaSpremnika) {
        this.listaSpremnika = listaSpremnika;
    }

    public List<Korisnik> getListaKorisnikaSpremnika() {
        return listaKorisnikaSpremnika;
    }

    public void setListaKorisnikaSpremnika(List<Korisnik> listaKorisnikaSpremnika) {
        this.listaKorisnikaSpremnika = listaKorisnikaSpremnika;
    }

}
