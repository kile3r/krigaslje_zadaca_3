/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.composite;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.model.Korisnik;
import org.foi.uzdiz.krigaslje.dz3.model.Spremnik;
import org.foi.uzdiz.krigaslje.dz3.singleton.Ispis;

/**
 *
 * @author kile
 */
public class Ulica implements Naselje {

    //id;naziv;broj mjesta; udio (%) mali;udio (%) srednji;udio (%) veliki
    //u1;Pavlinska;10;70;20;10
    String id;
    String naziv;
    int brojMjesta;
    int udioMali;
    int udioSrednji;
    int udioVeliki;
    int brojMalih;
    int brojSrednjih;
    int brojVelikih;
    public float otpadStaklo;
    public float otpadPapir;
    public float otpadMetal;
    public float otpadBio;
    public float otpadMjesano;

    public List<Korisnik> stanovnici;
    public List<Spremnik> spremnici;

    public Ulica() {

    }

    public Ulica(String id, String naziv, int brojMjesta, int udioMali, int udioSrednji, int udioVeliki) {
        this.id = id;
        this.naziv = naziv;
        this.brojMjesta = brojMjesta;
        this.udioMali = udioMali;
        this.udioSrednji = udioSrednji;
        this.udioVeliki = udioVeliki;
        this.otpadStaklo = 0;
        this.otpadPapir = 0;
        this.otpadMetal = 0;
        this.otpadBio = 0;
        this.otpadMjesano = 0;
        stanovnici = new ArrayList<>();
        spremnici = new ArrayList<>();
    }

    public void odrediKorisnike() {
        brojVelikih = (int) (brojMjesta * (udioVeliki / 100.0));
        brojMalih = (int) (brojMjesta * (udioMali / 100.0));
        brojSrednjih = (int) (brojMjesta * (udioSrednji / 100.0));

        int ukupnoKorisnika = brojVelikih + brojSrednjih + brojMalih;

        if (ukupnoKorisnika != brojMjesta) {

            if (ukupnoKorisnika < brojMjesta) {
                while (ukupnoKorisnika < brojMjesta) {
                    brojMalih++;
                    ukupnoKorisnika++;
                }
            } else {//jel treba ovo uopće?
                while (ukupnoKorisnika > brojMjesta) {
                    brojMalih--;
                    ukupnoKorisnika--;
                }
            }
        }
    }

    public void inicijalizirajKorisnike() {

        for (int i = 0; i < brojMalih; i++) {
            stanovnici.add(new Korisnik(1));
        }
        for (int i = 0; i < brojSrednjih; i++) {
            stanovnici.add(new Korisnik(2));
        }
        for (int i = 0; i < brojVelikih; i++) {
            stanovnici.add(new Korisnik(3));
        }
    }

    public void dodajKorisnikuSpremnike(List<Spremnik> inicijalnaListaSpremnika) {

        for (Spremnik spremnik : inicijalnaListaSpremnika) {
            if (spremnik.naBrojMalih > 0 && brojMalih > 0) {
                //dodamo malim korisnicima spremnike
                dodjeliMali(spremnik);
            }
            if (spremnik.naBrojSrednjih > 0 && brojSrednjih > 0) {
                dodjeliSrednji(spremnik);
            }
            if (spremnik.naBrojVelikih > 0 && brojVelikih > 0) {
                dodjeliVeliki(spremnik);
            }

        }//zatvara for

    }

    private void dodjeliMali(Spremnik spremnik) {

        Spremnik noviSpremnik = new Spremnik(spremnik.naziv, spremnik.vrsta, spremnik.naBrojMalih, spremnik.naBrojSrednjih, spremnik.naBrojVelikih, spremnik.nosivost);
        spremnici.add(noviSpremnik);
        for (Korisnik k : stanovnici) {
            if (k.tip == 1) {
                if (noviSpremnik.mozePrimitKorisnika(k.tip)) {
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                } else {
                    noviSpremnik = noviSpremnik.kloniraj();
                    spremnici.add(noviSpremnik);
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                }
            }
        }
    }

    private void dodjeliSrednji(Spremnik spremnik) {

        Spremnik noviSpremnik = new Spremnik(spremnik.naziv, spremnik.vrsta, spremnik.naBrojMalih, spremnik.naBrojSrednjih, spremnik.naBrojVelikih, spremnik.nosivost);
        spremnici.add(noviSpremnik);
        for (Korisnik k : stanovnici) {
            if (k.tip == 2) {
                if (noviSpremnik.mozePrimitKorisnika(k.tip)) {
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                } else {
                    noviSpremnik = noviSpremnik.kloniraj();
                    spremnici.add(noviSpremnik);
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                }
            }
        }

    }

    private void dodjeliVeliki(Spremnik spremnik) {
        Spremnik noviSpremnik = new Spremnik(spremnik.naziv, spremnik.vrsta, spremnik.naBrojMalih, spremnik.naBrojSrednjih, spremnik.naBrojVelikih, spremnik.nosivost);
        spremnici.add(noviSpremnik);
        for (Korisnik k : stanovnici) {
            if (k.tip == 3) {
                if (noviSpremnik.mozePrimitKorisnika(k.tip)) {
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                } else {
                    noviSpremnik = noviSpremnik.kloniraj();
                    spremnici.add(noviSpremnik);
                    k.dodijeljeniSpremnici.add(noviSpremnik);
                    noviSpremnik.listaKorisnikaSpremnika.add(k);
                }
            }
        }
    }

    public void odloziOtpad() {
        Ispis.getInstance().uvjetovaniIspis("***odlaganje otpada***" + naziv + "***");
        for (Korisnik k : stanovnici) {
            k.baciOtpad();
        }
        for (Spremnik s : spremnici) {
            if (s.naziv.equals("staklo")) {
                otpadStaklo += s.kolicinaOtpada;
            }
            if (s.naziv.equals("papir")) {
                otpadPapir += s.kolicinaOtpada;
            }
            if (s.naziv.equals("metal")) {
                otpadMetal += s.kolicinaOtpada;
            }
            if (s.naziv.equals("bio")) {
                otpadBio += s.kolicinaOtpada;
            }
            if (s.naziv.equals("mješano")) {
                otpadMjesano += s.kolicinaOtpada;
            }
        }
        Ispis.getInstance().uvjetovaniIspis("\nOtpad u ulici " + naziv + "\n");
        Ispis.getInstance().uvjetovaniIspis("Staklo: " + otpadStaklo);
        Ispis.getInstance().uvjetovaniIspis("Papir: " + otpadPapir);
        Ispis.getInstance().uvjetovaniIspis("Metal: " + otpadMetal);
        Ispis.getInstance().uvjetovaniIspis("Bio: " + otpadBio);
        Ispis.getInstance().uvjetovaniIspis("Mješano: " + otpadMjesano);
        Ispis.getInstance().uvjetovaniIspis("\n\n");
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

    public int getBrojMjesta() {
        return brojMjesta;
    }

    public void setBrojMjesta(int brojMjesta) {
        this.brojMjesta = brojMjesta;
    }

    public int getUdioMali() {
        return udioMali;
    }

    public void setUdioMali(int udioMali) {
        this.udioMali = udioMali;
    }

    public int getUdioSrednji() {
        return udioSrednji;
    }

    public void setUdioSrednji(int udioSrednji) {
        this.udioSrednji = udioSrednji;
    }

    public int getUdioVeliki() {
        return udioVeliki;
    }

    public void setUdioVeliki(int udioVeliki) {
        this.udioVeliki = udioVeliki;
    }

    public int getBrojMalih() {
        return brojMalih;
    }

    public void setBrojMalih(int brojMalih) {
        this.brojMalih = brojMalih;
    }

    public int getBrojSrednjih() {
        return brojSrednjih;
    }

    public void setBrojSrednjih(int brojSrednjih) {
        this.brojSrednjih = brojSrednjih;
    }

    public int getBrojVelikih() {
        return brojVelikih;
    }

    public void setBrojVelikih(int brojVelikih) {
        this.brojVelikih = brojVelikih;
    }

    public float getOtpadStaklo() {
        return otpadStaklo;
    }

    public void setOtpadStaklo(float otpadStaklo) {
        this.otpadStaklo = otpadStaklo;
    }

    public float getOtpadPapir() {
        return otpadPapir;
    }

    public void setOtpadPapir(float otpadPapir) {
        this.otpadPapir = otpadPapir;
    }

    public float getOtpadMetal() {
        return otpadMetal;
    }

    public void setOtpadMetal(float otpadMetal) {
        this.otpadMetal = otpadMetal;
    }

    public float getOtpadBio() {
        return otpadBio;
    }

    public void setOtpadBio(float otpadBio) {
        this.otpadBio = otpadBio;
    }

    public float getOtpadMjesano() {
        return otpadMjesano;
    }

    public void setOtpadMjesano(float otpadMjesano) {
        this.otpadMjesano = otpadMjesano;
    }

}
