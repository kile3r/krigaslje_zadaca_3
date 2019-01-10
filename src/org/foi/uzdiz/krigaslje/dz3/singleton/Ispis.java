/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author kile
 */
public class Ispis {
    static Ispis instance;
    
    private Ispis(){
        
    }
    public static Ispis getInstance(){
        if(instance == null) instance = new Ispis();
        return instance;
    }
    public void ispis(String str){
        System.out.println(str);
        writeToFile(str);
    }
    public void uvjetovaniIspis(String str){
        if(Parametri.getIspis()==0){
            System.out.println(str);
            writeToFile(str);
        }else{
            //samo statistika
        }
    }
    private void writeToFile(String str){
        str = str + "\n";
        String datotekaIzlaza = Parametri.getDatotekaIzlaz();
        try {
            File file = new File(datotekaIzlaza);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream writer = new FileOutputStream(datotekaIzlaza,true);
            writer.write(str.getBytes());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
