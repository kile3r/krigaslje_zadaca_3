/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fo.uzdiz.krigaslje.dz3.singleton.Parametri;
import org.foi.uzdiz.krigaslje.dr3.composite.Podrucje;
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
    
    
    //ovo jelista koja se puni s nečim kaj se ispisuje 
    List<String> listaSpremnik;
    
    public WindowController(List<Podrucje> listaPodrucja, List<Vozilo> listaVozila, List<Vozac> listVozac) {
        wv = new WindowView();
        wv.initializeWindow();
        listaSpremnik = new ArrayList<>();
        this.listaPodrucja = listaPodrucja;
        this.listaVozila = listaVozila;
        this.listVozac = listVozac;
        ispisiLinije(listaSpremnik);
        //ispisiLinije(up.getGreskePriUlazu());
        //pricekajKomandu();

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
        if (komanda.equals("I")) {
            wv.obrisiEkran();
            //dispecera tu inicijaliziram i pošaljem mu komandu
        }
    } 
    
    
    public void obradiKomandeDatoteke(String[] parametri){
        
        
        switch(parametri[0]){
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
                System.out.println("Prazni");
                break;
            case "STATUS":
                status();
                break;
            case "GODIŠNJI ODMOR":
                godisnjiOdmor(parametri[1]);
                System.out.println("god odmor");
                break;
            case "BOLOVANJE":
                bolovanje();
                System.out.println("Bol");
                break;
            case "OTKAZ":
                otkaz();
                System.out.println("Fired");
                break;
            case "PREUZMI":
                preuzmi();
                System.out.println("Mazni");
                break;
            case "NOVI":
                novi();
                System.out.println("Novi ");
                break;
            case "VOZAČI":
                vozaci();
                System.out.println("Vozaci");
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
        for(Vozilo vozilo: listaVozila){
            for(String str : vozila ){
                if(str.equals(vozilo.getId())){
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
        for(Vozilo vozilo: listaVozila){
            for(String str : vozila ){
                if(str.equals(vozilo.getId())){
                    vozilo.setStatus(1);
                    ispisiLiniju("Vozilo " + vozilo.getId() + " je u kvaru");
                }
            }
        }
    }

    private void kontrola(String parametar) {
        
        String[] vozila = parametar.split(",");
        for(Vozilo vozilo: listaVozila){
            for(String str : vozila ){
                if(str.equals(vozilo.getId())){
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
        for(Vozilo vozilo: listaVozila){
            if(vozilo.getStatus()!=1){
                ispisiLiniju("Vozilo[" + vozilo.getId() + "] naziv " +  vozilo.getNaziv() + " status " + vozilo.getStatus() + " tip " + vozilo.getTip() + " nosivost " + vozilo.getNosivost());
            }
            
        }
    }

    private void godisnjiOdmor(String parametar) {
        
        String[] vozaci = parametar.split(",");
        
        for(Vozilo vozilo: listaVozila){
            for(Vozac vozac: vozilo.listaVozaca){
                
            
            }
        }
    }

    private void bolovanje() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void otkaz() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void preuzmi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void novi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void vozaci() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void ispisiGresku() {
        ispisiLiniju("Nepostojeca komanda. Preskacem");
    }

    
}
