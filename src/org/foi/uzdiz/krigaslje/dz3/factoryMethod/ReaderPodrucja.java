/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.uzdiz.krigaslje.dz3.factoryMethod;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import org.fo.uzdiz.krigaslje.dz3.singleton.Ispis;
import org.fo.uzdiz.krigaslje.dz3.singleton.Parametri;


/**
 *
 * @author kile
 */
public class ReaderPodrucja implements FileReader{
    @Override
    public List<String[]> getRecords() {
        String fileName = Parametri.getDatotekaPodrucja();
        List<String[]> listaPodrucja = new ArrayList<>();
        try{
            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            String str;
            file.readLine(); //skip first row
            while((str = file.readLine())!= null){
                str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
                if(isOkPodrucje(str)){
                        listaPodrucja.add(str.split(";"));
                }else{
                    Ispis.getInstance().ispis("Nepravilan zapis u datoteci podrucja: " + str);
            }
        }
        }catch(IOException e){
            
        }
        return listaPodrucja;
    }
    
    private boolean isOkPodrucje(String str){
        String[] parts = str.split(";");
        if(parts.length==3){
            //TODO: check integera 
            return true;
        }else return false;
    }
}

