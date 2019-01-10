/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.main;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.FileReader;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.ReaderFactory;
import org.foi.uzdiz.krigaslje.dz3.model.Spremnik;
import org.foi.uzdiz.krigaslje.dz3.model.Vozilo;

/**
 *
 * @author kile
 */
public class DataImporter {

    Parametri param = Parametri.getInstance();

    public DataImporter() {
        param.readParameterFile(EZO3.datotekaParametara);
    }

    public List<Vozilo> getVozila() {
        ReaderFactory rf = new ReaderFactory("vozila");
        FileReader fr = rf.fileReader();

        List<String[]> zapisVozila = fr.getRecords();
        List<Vozilo> listaVozila = new ArrayList<>();

        for (String[] zapis : zapisVozila) {
            listaVozila.add(new Vozilo(zapis));
        }

        return listaVozila;

    }

    public List<Spremnik> getSpremnici() {

        ReaderFactory rf = new ReaderFactory("spremnici");
        FileReader fr = rf.fileReader();

        List<String[]> zapisSpremnika = fr.getRecords();
        List<Spremnik> listaSpremnika = new ArrayList<>();

        for (String[] zapis : zapisSpremnika) {
            listaSpremnika.add(new Spremnik(zapis));

        }
        return listaSpremnika;
    }

    public List<String[]> getZapiseUlica() {

        ReaderFactory rfu = new ReaderFactory("ulice");
        FileReader fru = rfu.fileReader();
        List<String[]> zapisiUlica = fru.getRecords();

        return zapisiUlica;
    }

    public List<String[]> getZapisePodrucja() {

        ReaderFactory rfp = new ReaderFactory("podrucja");
        FileReader frp = rfp.fileReader();
        List<String[]> zapisiPodrucja = frp.getRecords();

        return zapisiPodrucja;

    }

}
