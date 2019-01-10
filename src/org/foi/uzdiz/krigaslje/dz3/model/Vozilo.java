/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.model;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.composite.Ulica;
import org.foi.uzdiz.krigaslje.dz3.singleton.Ispis;
import org.foi.uzdiz.krigaslje.dz3.singleton.Parametri;

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
    
    int trenutnaKolicina;
    int ciklusiOdvoza;
    
    String vrstaString;

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
        trenutnaKolicina = 0;
        ciklusiOdvoza = 0;
        vrstaString = mapirajVrstu(vrsta);

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
    
    
    public void preuzmiOtpad(List<Ulica> ulice){
        if(status==1){
            for(Ulica u : ulice){
                for(Spremnik s: u.spremnici){
                    if(s.naziv.equals(vrstaString)&&s.kolicinaOtpada>0){
                        if((trenutnaKolicina+s.kolicinaOtpada)<nosivost){
                            trenutnaKolicina+=s.kolicinaOtpada;
                            
                            Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je preuzelo " + s.kolicinaOtpada +"kg otpada iz spremnika " + ". #= " + trenutnaKolicina + "/" + nosivost);
                            s.kolicinaOtpada = 0;
                        }else{
                            status = 0;
                            Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je napunjeno i odvozi otpad na odlagalište!");
                        }
                        return;
                    }
                }
            }
        }else{
            ciklusiOdvoza++;
            if(ciklusiOdvoza>=Parametri.getBrojRadnihCiklusaZaOdvoz()){
                ciklusiOdvoza=0;
                status=1;
                trenutnaKolicina = 0;
                Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " je obavilo odvoz otpada na odlagalište!");
            }else{
                Ispis.getInstance().uvjetovaniIspis("Vozilo " + naziv + " odvozi otpad na odlagalište!");
            }
        }
    }
    
    private String mapirajVrstu(int vrsta){
        if(vrsta==0) return "staklo";
        if(vrsta==1) return "papir";
        if(vrsta==2) return "metal";
        if(vrsta==3) return "bio";
        if(vrsta==4) return "mješano";
        else return "";
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

    public int getTrenutnaKolicina() {
        return trenutnaKolicina;
    }

    public void setTrenutnaKolicina(int trenutnaKolicina) {
        this.trenutnaKolicina = trenutnaKolicina;
    }

    public int getCiklusiOdvoza() {
        return ciklusiOdvoza;
    }

    public void setCiklusiOdvoza(int ciklusiOdvoza) {
        this.ciklusiOdvoza = ciklusiOdvoza;
    }

    public String getVrstaString() {
        return vrstaString;
    }

    public void setVrstaString(String vrstaString) {
        this.vrstaString = vrstaString;
    }
    
    
    
    

}
