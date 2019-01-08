/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.factoryMethod;

/**
 *
 * @author kile
 */
public class ReaderFactory {
    private String datoteka;
    public ReaderFactory(String dat){
        datoteka = dat;
    }
    public FileReader fileReader(){
        if("ulice".equals(datoteka)) return new ReaderUlice();
        if("spremnici".equals(datoteka)) return new ReaderSpremnici();
        if("vozila".equals(datoteka)) return new ReaderVozila();
        if("podrucja".equals(datoteka)) return new ReaderPodrucja();
        if("dispecer".equals(datoteka)) return new ReaderDispecer();
        else return null;
    }
}
