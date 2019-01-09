/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.main;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.composite.Podrucje;
import org.foi.uzdiz.krigaslje.dz3.composite.Ulica;
import org.foi.uzdiz.krigaslje.dz3.model.Korisnik;
import org.foi.uzdiz.krigaslje.dz3.model.Spremnik;
import org.foi.uzdiz.krigaslje.dz3.model.Vozac;
import org.foi.uzdiz.krigaslje.dz3.model.Vozilo;

/**
 *
 * @author kile
 */
public class InicijalizatorSustava {

    DataImporter di;

    public InicijalizatorSustava(DataImporter di) {
        this.di = di;
    }

    public List<Podrucje> inicijalizirajPodrucje() {

        List<Podrucje> listaPodrucja = new ArrayList<>();
        List<String[]> listaPotpodrucja = new ArrayList<>();
        List<Ulica> listaUlica = new ArrayList<>();
        List<Podrucje> listaIshodista = new ArrayList<>();
        Podrucje p;
        for (String[] zapis : di.getZapisePodrucja()) {
            p = new Podrucje(zapis[0], zapis[1]);
            listaPodrucja.add(p);
            String[] djelovi = zapis[2].split(",");
            listaPotpodrucja.add(djelovi);

        }//zatvara for
        Ulica u;
        for (String[] zapis : di.getZapiseUlica()) {
            u = new Ulica(zapis[0], zapis[1], Integer.parseInt(zapis[2]), Integer.parseInt(zapis[3]), Integer.parseInt(zapis[4]), Integer.parseInt(zapis[5]));
            listaUlica.add(u);
        }

        listaUlica = inicijalizcijaKorisnikaSpremnikaUlicama(listaUlica);

        for (String[] djelovi : listaPotpodrucja) {
            int index = listaPotpodrucja.indexOf(djelovi);
            for (String dio : djelovi) {

                if (dio.startsWith("p")) {
                    for (Podrucje podrucje : listaPodrucja) {
                        if (podrucje.getId().equals(dio)) {
                            podrucje.setIshodiste(false);
                            listaPodrucja.get(index).getPotPodrucja().add(podrucje);
                        }

                    }
                } else {
                    for (Ulica ulica : listaUlica) {
                        if (ulica.getId().equals(dio)) {
                            listaPodrucja.get(index).getPotPodrucja().add(ulica);
                        }

                    }

                }

            }

        }

        for (Podrucje ishodiste : listaPodrucja) {
            if (ishodiste.isIshodiste()) {
                listaIshodista.add(ishodiste);
            }
        }
        return listaIshodista;
    }//mEtodu zatvara

    private List<Ulica> inicijalizcijaKorisnikaSpremnikaUlicama(List<Ulica> listaUlica) {

        Spremnik s = new Spremnik();
        s.popuniListuSpremnika();

        for (Ulica ulica : listaUlica) {
            //System.out.println("U ulici" + ulica.getNaziv() + "se nalazi ");
            ulica.odrediKorisnike();
            ulica.inicijalizirajKorisnike();
            ulica.dodajKorisnikuSpremnike(s.listaSpremnika);
            for (Korisnik k : ulica.stanovnici) {
                k.generirajOtpad();
                //System.out.println("\nKorisnik [" + k.id + "] ima otpad");
//                System.out.println("Metal " + k.otpadMetal
//                        + "\nPapir " + k.otpadPapir
//                        + "\nStaklo " + k.otpadStaklo
//                        + "\nBio " + k.otpadBio
//                        + "\nMjesano " + k.otpadMjesano
//                );
                ulica.otpadMetal += k.otpadMetal;
                ulica.otpadPapir += k.otpadPapir;
                ulica.otpadStaklo += k.otpadStaklo;
                ulica.otpadBio += k.otpadBio;
                ulica.otpadMjesano += k.otpadMjesano;

            }
        }
        return listaUlica;
    }

    public List<Vozilo> inicijalizacijaVozila() {

        return di.getVozila();

    }

    public List<Vozac> inicijalizacijaVozaca(List<Vozilo> listaVozila) {

        List<Vozac> listaVozaca = new ArrayList<>();
        Vozac vozac;
        for (Vozilo vozilo : listaVozila) {

            if (vozilo.listaVozaca != null && !vozilo.listaVozaca.isEmpty()) {
                for (Vozac v : vozilo.getListaVozaca()) {
                    vozac = new Vozac(v.getIme(), 0);
                    listaVozaca.add(vozac);
                }

            }

        }
        return listaVozaca;
    }
}
