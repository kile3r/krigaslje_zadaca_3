package org.fo.uzdiz.krigaslje.dz3.singleton;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author kile
 */

public class Parametri {

    private static Parametri instance;

    static String datotekaPodrucja;
    static String datotekaUlice;
    static String datotekaSpremnici;
    static String datotekaVozila;
    static String datotekaIzlaz;
    static String datotekaDispecer;
    
    
    static int ispis;
    static int sjemeGeneratora;
    static int brojDecimala;
    static int brojRadnihCiklusaZaOdvoz;
    static int preuzimanja;
    static int maliMin;
    static int srednjiMin;
    static int velikiMin;
    static int maliStaklo;
    static int maliPapir;
    static int maliMetal;
    static int maliBio;
    static int maliMjesano;
    static int srednjiStaklo;
    static int srednjiPapir;
    static int srednjiMetal;
    static int srednjiBio;
    static int srednjiMjesano;
    static int velikiStaklo;
    static int velikiPapir;
    static int velikiMetal;
    static int velikiBio;
    static int velikiMjesano;
    static int kapacitetDizelVozila;
    static int kapacitetElektroVozila;
    static int punjenjeDizelVozila;
    static int punjenjeElektroVozila;

    private Parametri() {
    }

    public static Parametri getInstance() {
        if (instance == null) {
            instance = new Parametri();
        }
        return instance;
    }
    public void readParameterFile(String fileName){
        try{
            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            String str;
            while((str = file.readLine())!=null){
                str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
                String[] parametriZapis = str.split(":");
                if(parametriZapis.length==2){
                    switch(parametriZapis[0]){
                        case "područja":
                            datotekaPodrucja = parametriZapis[1];
                            break;
                        case "dispečer":
                            datotekaDispecer = parametriZapis[1];
                            break;
                        case "ulice":
                            datotekaUlice = parametriZapis[1];
                            break;
                        case "spremnici":
                            datotekaSpremnici = parametriZapis[1];
                            break;
                        case "vozila":
                            datotekaVozila = parametriZapis[1];
                            break;
                        case "izlaz":
                            datotekaIzlaz = parametriZapis[1];
                            break;
                        case "ispis":
                            ispis = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "sjemeGeneratora":
                            sjemeGeneratora = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "brojDecimala":
                            brojDecimala = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "brojRadnihCiklusaZaOdvoz":
                            brojRadnihCiklusaZaOdvoz = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "preuzimanje":
                            preuzimanja = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliMin":
                            maliMin = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiMin":
                            srednjiMin = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiMin":
                            velikiMin = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliStaklo":
                            maliStaklo = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliPapir":
                            maliPapir = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliMetal":
                            maliMetal = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliBio":
                            maliBio = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "maliMješano":
                            maliMjesano = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiStaklo":
                            srednjiStaklo = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiPapir":
                            srednjiPapir  = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiMetal":
                            srednjiMetal = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiBio":
                            srednjiBio = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "srednjiMješano":
                            srednjiMjesano = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiStaklo":
                            velikiStaklo = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiPapir":
                            velikiPapir = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiMetal":
                            velikiMetal = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiBio":
                            velikiBio = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "velikiMješano":
                            velikiMjesano = Integer.parseInt(parametriZapis[1]);
                            break;
                        case "kapacitetDizelVozila":
                            kapacitetDizelVozila = Integer.parseInt(parametriZapis[1].replaceAll("^\\s",""));
                            break;
                        case "kapacitetElektroVozila":
                            kapacitetElektroVozila = Integer.parseInt(parametriZapis[1].replaceAll("^\\s",""));
                            break;
                        case "punjenjeDizelVozila":
                            punjenjeDizelVozila = Integer.parseInt(parametriZapis[1].replaceAll("^\\s",""));
                            break;
                        case "punjenjeElektroVozila":
                            punjenjeElektroVozila = Integer.parseInt(parametriZapis[1].replaceAll("^\\s",""));
                            break;
                        default:
                            break;
                    }
                }else{
                    System.out.println("Nije dobra linija u datoteci");
                    
                }
            }
        }catch(IOException e){
            System.out.println("Neuspješno čitanje datoteke parametara!");
        }
    }

    @Override
    public String toString() {
        return "ulice: " + datotekaUlice + " srednjimjesano: " + srednjiMjesano;
    }

    public static String getDatotekaUlice() {
        return datotekaUlice;
    }

    public static String getDatotekaSpremnici() {
        return datotekaSpremnici;
    }

    public static String getDatotekaVozila() {
        return datotekaVozila;
    }

    public static String getDatotekaIzlaz() {
        return datotekaIzlaz;
    }

    public static int getIspis() {
        return ispis;
    }

    public static int getSjemeGeneratora() {
        return sjemeGeneratora;
    }

    public static int getBrojDecimala() {
        return brojDecimala;
    }

    public static int getBrojRadnihCiklusaZaOdvoz() {
        return brojRadnihCiklusaZaOdvoz;
    }

    public static int getPreuzimanja() {
        return preuzimanja;
    }

    public static int getMaliMin() {
        return maliMin;
    }

    public static int getSrednjiMin() {
        return srednjiMin;
    }

    public static int getVelikiMin() {
        return velikiMin;
    }

    public static int getMaliStaklo() {
        return maliStaklo;
    }

    public static int getMaliPapir() {
        return maliPapir;
    }

    public static int getMaliMetal() {
        return maliMetal;
    }

    public static int getMaliBio() {
        return maliBio;
    }

    public static int getMaliMjesano() {
        return maliMjesano;
    }

    public static int getSrednjiStaklo() {
        return srednjiStaklo;
    }

    public static int getSrednjiPapir() {
        return srednjiPapir;
    }

    public static int getSrednjiMetal() {
        return srednjiMetal;
    }

    public static int getSrednjiBio() {
        return srednjiBio;
    }

    public static int getSrednjiMjesano() {
        return srednjiMjesano;
    }

    public static int getVelikiStaklo() {
        return velikiStaklo;
    }

    public static int getVelikiPapir() {
        return velikiPapir;
    }

    public static int getVelikiMetal() {
        return velikiMetal;
    }

    public static int getVelikiBio() {
        return velikiBio;
    }

    public static int getVelikiMjesano() {
        return velikiMjesano;
    }

    public static String getDatotekaDispecer() {
        return datotekaDispecer;
    }

    public static String getDatotekaPodrucja() {
        return datotekaPodrucja;
    }

    public static int getKapacitetDizelVozila() {
        return kapacitetDizelVozila;
    }

    public static int getKapacitetElektroVozila() {
        return kapacitetElektroVozila;
    }

    public static int getPunjenjeDizelVozila() {
        return punjenjeDizelVozila;
    }

    public static int getPunjenjeElektroVozila() {
        return punjenjeElektroVozila;
    }

}
