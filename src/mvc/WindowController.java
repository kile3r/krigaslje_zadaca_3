/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fo.uzdiz.krigaslje.dz3.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dz3.composite.Naselje;
import org.foi.uzdiz.krigaslje.dz3.composite.Podrucje;
import org.foi.uzdiz.krigaslje.dz3.composite.Ulica;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.FileReader;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.ReaderFactory;
import org.foi.uzdiz.krigaslje.dz3.main.EZO3;
import org.foi.uzdiz.krigaslje.dz3.model.Vozac;
import org.foi.uzdiz.krigaslje.dz3.model.Vozilo;

/**
 *
 * @author kile
 */
public class WindowController {

    WindowView wv;
    Parametri parametri;

    List<Podrucje> listaPodrucja;
    List<Vozilo> listaVozila;
    List<Vozac> listVozac;

    List<Vozac> noviVozaci;

    public boolean pronadjen = false;

    public boolean postojiVozilo = false;
    public boolean postojiVozac = false;
    
    Map<String, float[]> mapaOtpadaUkupno = new HashMap<>();

    //ovo jelista koja se puni s nečim kaj se ispisuje 
    List<String> listaSpremnik;

    public WindowController(List<Podrucje> listaPodrucja, List<Vozilo> listaVozila, List<Vozac> listVozac) {
        wv = new WindowView();
        wv.initializeWindow();
        listaSpremnik = new ArrayList<>();
        this.listaPodrucja = listaPodrucja;
        this.listaVozila = listaVozila;
        this.listVozac = listVozac;
        noviVozaci = new ArrayList<>();
        ispisiLinije(listaSpremnik);
        
        //ispisiInicijaliziraniSustav();
        
        ReaderFactory rfp = new ReaderFactory("dispecer");
        FileReader frp = rfp.fileReader();
        List<String[]> zapisiDispecera = frp.getRecords();
        obradiDatoteku(zapisiDispecera);
        
        
        pricekajKomandu();

    }

    public void ispisiLiniju(String s) {

        List<String> listaStringova = new ArrayList<>();

        listaStringova.add(s);

        ispisiLinije(listaStringova);

    }

    public void ispisiLinije(List<String> s) {

        while (s.size() > 0) {

            String recenica = s.remove(0);

            if (recenica.length() > EZO3.brojStupaca - 2) {
                String s1 = recenica.substring(0, EZO3.brojStupaca - 3);
                String s2 = recenica.substring(EZO3.brojStupaca - 3);
                s.add(s1);
                s.add(s2);
                continue;
            }

            boolean imaMjesta = wv.ispis(recenica);
            if (!imaMjesta) {
                listaSpremnik = s;
                pricekajNastavak();
                return;
            }
        }

    }

    private void pricekajNastavak() {
        wv.zabiljeziKomandu("Pritisnike n|N za nastavak");
        char c = 0;

        do {
            try {
                c = (char) System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(WindowController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } while (c != 'N' && c != 'n');

        wv.initializeWindow();
        ispisiLinije(listaSpremnik);

    }

    public void pricekajKomandu() {

        Scanner s = new Scanner(System.in);
        String komanda = s.nextLine();

        wv.zabiljeziKomandu(komanda);
        String[] djeloviKomande = komanda.split(";");
        obradiKomande(djeloviKomande);
    }

    public void obradiKomande(String[] parametri) {

        switch (parametri[0]) {
            case "OBRADI":
                obradi();
                System.out.println("Obraduje");
                break;
            case "PRIPREMI":
                pripremi(parametri[1]);
                System.out.println("Priprema");
                break;
            case "KRENI":
                kreni();
                System.out.println("Kreni");
                break;
            case "KVAR":
                kvar(parametri[1]);
                break;
            case "KONTROLA":
                kontrola(parametri[1]);
                break;
            case "ISPRAZNI":
                isprazni();
                System.out.println("prazni");
                break;
            case "STATUS":
                status();
                break;
            case "GODIŠNJI ODMOR":
                godisnjiOdmor(parametri[1]);
                break;
            case "BOLOVANJE":
                bolovanje(parametri[1]);
                break;
            case "OTKAZ":
                otkaz(parametri[1]);
                break;
            case "PREUZMI":
                preuzmi(parametri[1], parametri[2]);
                break;
            case "NOVI":
                novi(parametri[1]);
                break;
            case "VOZAČI":
                vozaci();
                break;
            case "IZLAZ":
                izlaz();
                break;
            default:
                ispisiGresku();
                break;
        }

    }

    private void obradi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void izlaz() {
        ispisiLiniju("Gasim...");
        System.exit(0);
    }

    private void pripremi(String parametar) {
        String[] vozila = parametar.split(",");
        for (Vozilo vozilo : listaVozila) {
            for (String str : vozila) {
                if (str.equals(vozilo.getId())) {
                    vozilo.setStatus(0);
                    ispisiLiniju("Vozilo " + vozilo.getId() + " je spremno za preuzimanje otpada");
                }
            }
        }
    }

    private void kreni() {

        //nah
    }

    private void kvar(String parametar) {
        String[] vozila = parametar.split(",");
        for (Vozilo vozilo : listaVozila) {
            for (String str : vozila) {
                if (str.equals(vozilo.getId())) {
                    vozilo.setStatus(1);
                    ispisiLiniju("Vozilo " + vozilo.getId() + " je u kvaru");
                }
            }
        }
    }

    private void kontrola(String parametar) {

        String[] vozila = parametar.split(",");
        for (Vozilo vozilo : listaVozila) {
            for (String str : vozila) {
                if (str.equals(vozilo.getId())) {
                    vozilo.setStatus(2);
                    ispisiLiniju("Vozilo " + vozilo.getId() + " je u kontroli");
                }
            }
        }
    }

    private void isprazni() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void status() {
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getStatus() != 1) {
                ispisiLiniju("Vozilo[" + vozilo.getId() + "] naziv " + vozilo.getNaziv() + " status " + vozilo.getStatus() + " tip " + vozilo.getTip() + " nosivost " + vozilo.getNosivost());
            }

        }
    }

    private void godisnjiOdmor(String parametar) {
        String[] vozaci = parametar.split(",");

        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                for (int i = 0; i < vozaci.length; i++) {
                    if (vozac.getIme().trim().equals(vozaci[i].trim())) {
                        pronadjen = true;
                        promijeniStatusVozacu(vozac.getIme(), 1);
                    } else {
                        pronadjen = false;
                    }
                }
            }
        }
    }

    private void bolovanje(String parametar) {

        String[] vozaci = parametar.split(",");

        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                for (int i = 0; i < vozaci.length; i++) {
                    if (vozac.getIme().trim().equals(vozaci[i].trim())) {
                        pronadjen = true;
                        promijeniStatusVozacu(vozac.getIme(), 2);
                    } else {
                        pronadjen = false;
                    }
                }
            }
        }
    }

    private void otkaz(String parametar) {
        String[] vozaci = parametar.split(",");

        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                for (int i = 0; i < vozaci.length; i++) {
                    if (vozac.getIme().trim().equals(vozaci[i].trim())) {
                        pronadjen = true;
                        promijeniStatusVozacu(vozac.getIme(), 3);
                    } else {
                        pronadjen = false;
                    }
                }
            }
        }
    }

    private void preuzmi(String vozacIme, String voziloId) {
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getId().trim().equals(voziloId.trim())) {
                postojiVozilo = true;
                break;
            }
        }
        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                if (vozac.getIme().trim().equals(vozacIme.trim()) && vozac.getStatus() != 3) {
                    postojiVozac = true;
                    break;
                }
            }
        }//jel postoji vozilo i voza

        if (postojiVozac && postojiVozilo) {
            for (Vozilo vozilo : listaVozila) {
                for (Vozac vozac : vozilo.getListaVozaca()) {
                    if (vozac.getIme().trim().equals(vozacIme.trim())) {
                        dodajUVozilo(voziloId, vozac);
                        //dajPopisVOzilaIVozaca();
                        vozilo.listaVozaca.remove(vozac);
                        //makniIzOriginalVozila(vozac, idMaticnogVozila);
                        dajPopisVOzilaIVozaca();
                        break;
                    }
                }
                break;
            }
        } else {
            ispisiLiniju("Čini se da ne postoji vozač ili vozilo koje ste unjeli.");
        }
    }

    private void dodajUVozilo(String idVozila, Vozac v) {
        for (Vozilo vozilo : listaVozila) {
            if (vozilo.getId().equals(idVozila)) {
                vozilo.getListaVozaca().add(v);
            }
        }

    }

//    private void makniIzOriginalVozila(Vozac v, String idMaticnogVozila) {
//        for (Vozilo vozilo : listaVozila) {
//            if (vozilo.getId().equals(idMaticnogVozila)) {
//                ispisiLiniju("nasal sam ti vozilo");
//                for (Vozac vozac : vozilo.getListaVozaca()) {
//                    if (vozac.getIme().trim().equals(v.getIme().trim())) {
//                        ispisiLiniju("Vozac ime " + v.getIme());
//                        ispisiLiniju("Od objekta " + vozac.getIme());
//                        vozilo.listaVozaca.remove(vozac);
//                        break;
//                    }
//                }
//            }
//        }
//    }

    private void novi(String parametar) {

        String[] vozaci = parametar.split(",");

        for (int i = 0; i < vozaci.length; i++) {
            noviVozaci.add(new Vozac(vozaci[i].trim(), 0));
        }

        for (Vozilo vozilo : listaVozila) {
            int brojVozaca = noviVozaci.size();
            if (noviVozaci.isEmpty() || imaAktivnihVozaca(vozilo.getListaVozaca())) {
                //ne puni se lista voazcima jer ili ima nekog aktivnog ili je lista novih prazna
                ispisiLiniju("Nema raspoloživih novih vozača za dodati u vozilo" +vozilo.getNaziv()+  " ili u vozilu postoji aktivni vozač");
            } else if (vozilo.getListaVozaca().isEmpty() || !imaAktivnihVozaca(vozilo.getListaVozaca()) || !noviVozaci.isEmpty()) {
                vozilo.listaVozaca.add(noviVozaci.get(brojVozaca - 1));
                noviVozaci.remove(brojVozaca - 1);
            }
        }
    }

    private boolean imaAktivnihVozaca(List<Vozac> listaVozaca) {
        boolean imaAktivnog = false;
        for (Vozac vozac : listaVozaca) {
            if (vozac.getStatus() == 0) {
                imaAktivnog = true;
                break;
            } else {
                imaAktivnog = false;
            }
        }
        return imaAktivnog;
    }

    private void vozaci() {
        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                ispisiLiniju("Vozac " + vozac.getIme().trim() + " ima status " + vozac.getStatus());
            }
        }
    }

    private void ispisiGresku() {
        ispisiLiniju("Nepostojeca komanda. Preskacem");
    }

    private void promijeniStatusVozacu(String ime, int status) {

        for (Vozilo vozilo : listaVozila) {
            for (Vozac vozac : vozilo.getListaVozaca()) {
                if (vozac.getIme().equals(ime)) {
                    vozac.setStatus(status);
                    ispisiLiniju("Vozac" + vozac.getIme().trim() + " ima novi status " + vozac.getStatus());
                }
            }
        }

    }

    private void dajPopisVOzilaIVozaca() {
        for (Vozilo vozilo : listaVozila) {
            ispisiLiniju("Vozilo ima : " + vozilo.getNaziv().trim());
            for (Vozac vozac : vozilo.getListaVozaca()) {
                ispisiLiniju("Vozac " + vozac.getIme().trim());
            }
        }
    }

    private void obradiDatoteku(List<String[]> zapisiDispecera) {
        for(String[] parametri : zapisiDispecera){
                    obradiKomande(parametri);
        }
    }
    
    
    public void ispisiInicijaliziraniSustav() {
        for (Podrucje podrucje : listaPodrucja) {
            ispisiLiniju("ID\tStaklo\tPapir\tMetal\tBio\tMjesano");
            zbrajanjePoUlicama((Naselje) podrucje);
            zbrajanjePoPodrucjima((Naselje) podrucje);
            zbrajanjePoIshodistu((Naselje) podrucje);
            ispisiLiniju(podrucje.getId() + "\t" + mapaOtpadaUkupno.get(podrucje.getId())[0] + "\t" + mapaOtpadaUkupno.get(podrucje.getId())[1] + "\t"
                    + mapaOtpadaUkupno.get(podrucje.getId())[2] + "\t" + mapaOtpadaUkupno.get(podrucje.getId())[3] + "\t" + mapaOtpadaUkupno.get(podrucje.getId())[4]);
            ispisPodrucja((Naselje) podrucje);
            ispisiLiniju("\n");
        }
    }
    
    private void ispisPodrucja(Naselje naselje) {
        for (Naselje podnaselje : ((Podrucje) naselje).getPotPodrucja()) {
            if (podnaselje instanceof Podrucje) {
                String key = ((Podrucje) podnaselje).getId();
                ispisiLiniju(key + "\t" + mapaOtpadaUkupno.get(key)[0] + "\t" + mapaOtpadaUkupno.get(key)[1] + "\t"
                    + mapaOtpadaUkupno.get(key)[2] + "\t" + mapaOtpadaUkupno.get(key)[3] + "\t" + mapaOtpadaUkupno.get(key)[4]);
                ispisPodrucja(podnaselje);
            } else {
                ispisiLiniju(((Ulica) podnaselje).getId() + "\t" + ((Ulica) podnaselje).otpadStaklo + "\t" + ((Ulica) podnaselje).otpadPapir + "\t"
                        + ((Ulica) podnaselje).otpadMetal + "\t" + ((Ulica) podnaselje).otpadBio + "\t" + ((Ulica) podnaselje).otpadMjesano);
            }
        }
    }
    
    private void zbrajanjePoUlicama(Naselje naselje) {
        for (Naselje podnaselje : ((Podrucje) naselje).getPotPodrucja()) {
            if (podnaselje instanceof Podrucje) {
                zbrajanjePoUlicama(podnaselje);
            } else {
                String key = ((Podrucje) naselje).getId();
                if (mapaOtpadaUkupno.containsKey(key)) {
                    mapaOtpadaUkupno.get(key)[0] = mapaOtpadaUkupno.get(key)[0] + ((Ulica) podnaselje).otpadStaklo;
                    mapaOtpadaUkupno.get(key)[1] = mapaOtpadaUkupno.get(key)[1] + ((Ulica) podnaselje).otpadPapir;
                    mapaOtpadaUkupno.get(key)[2] = mapaOtpadaUkupno.get(key)[2] + ((Ulica) podnaselje).otpadMetal;
                    mapaOtpadaUkupno.get(key)[3] = mapaOtpadaUkupno.get(key)[3] + ((Ulica) podnaselje).otpadBio;
                    mapaOtpadaUkupno.get(key)[4] = mapaOtpadaUkupno.get(key)[4] + ((Ulica) podnaselje).otpadMjesano;
                } else {
                    mapaOtpadaUkupno.put(key, new float[]{((Ulica) podnaselje).otpadStaklo, ((Ulica) podnaselje).otpadPapir, ((Ulica) podnaselje).otpadMetal, ((Ulica) podnaselje).otpadBio, ((Ulica) podnaselje).otpadMjesano});
                }
            }
        }
    }
    
    private void zbrajanjePoPodrucjima(Naselje naselje) {
        for (Naselje podnaselje : ((Podrucje) naselje).getPotPodrucja()) {
            if (podnaselje instanceof Podrucje) {
                for (Naselje manjeNaselje : ((Podrucje) podnaselje).getPotPodrucja()) {
                    String key = ((Podrucje) podnaselje).getId();
                    if (mapaOtpadaUkupno.containsKey(key)) {
                        mapaOtpadaUkupno.get(key)[0] = mapaOtpadaUkupno.get(key)[0] + mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[0];
                        mapaOtpadaUkupno.get(key)[1] = mapaOtpadaUkupno.get(key)[1] + mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[1];
                        mapaOtpadaUkupno.get(key)[2] = mapaOtpadaUkupno.get(key)[2] + mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[2];
                        mapaOtpadaUkupno.get(key)[3] = mapaOtpadaUkupno.get(key)[3] + mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[3];
                        mapaOtpadaUkupno.get(key)[4] = mapaOtpadaUkupno.get(key)[4] + mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[4];
                    } else {
                        mapaOtpadaUkupno.put(key, new float[] {
                            mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[0],
                            mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[1],
                            mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[2],
                            mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[3],
                            mapaOtpadaUkupno.get(((Podrucje) manjeNaselje).getId())[4]
                        });
                    }
                }
            }
        }
    }
    
    private void zbrajanjePoIshodistu(Naselje naselje) {
        for (Naselje podnaselje : ((Podrucje) naselje).getPotPodrucja()) {
            if (podnaselje instanceof Podrucje) {
                String key = ((Podrucje) naselje).getId();
                if (mapaOtpadaUkupno.containsKey(key)) {
                    mapaOtpadaUkupno.get(key)[0] = mapaOtpadaUkupno.get(key)[0] + mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[0];
                    mapaOtpadaUkupno.get(key)[1] = mapaOtpadaUkupno.get(key)[1] + mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[1];
                    mapaOtpadaUkupno.get(key)[2] = mapaOtpadaUkupno.get(key)[2] + mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[2];
                    mapaOtpadaUkupno.get(key)[3] = mapaOtpadaUkupno.get(key)[3] + mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[3];
                    mapaOtpadaUkupno.get(key)[4] = mapaOtpadaUkupno.get(key)[4] + mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[4];
                } else {
                    mapaOtpadaUkupno.put(key, new float[]{
                        mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[0],
                        mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[1],
                        mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[2],
                        mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[3],
                        mapaOtpadaUkupno.get(((Podrucje) podnaselje).getId())[4]
                    });
                }
            }
        }
    }

}
