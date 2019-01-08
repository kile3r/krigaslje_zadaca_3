package org.foi.uzdiz.krigaslje.dz3.main;

import java.util.List;
import mvc.WindowController;
import org.fo.uzdiz.krigaslje.dz3.singleton.Parametri;
import org.fo.uzdiz.krigaslje.dz3.singleton.RndGenerator;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.FileReader;
import org.foi.uzdiz.krigaslje.dz3.factoryMethod.ReaderFactory;
import org.foi.uzdiz.krigaslje.dz3.model.Vozilo;

/**
 *
 * @author kile
 */
public class EZO3 {

    public static final String ANSI_ESC = "\033[";

    public static int brojStupaca = 80;
    public static String brojRedaka = null;
    public static String brojRedakaKomandi = null;
    public static String datotekaParametara = null;

    public static void main(String[] args) {

        Parametri.getInstance().readParameterFile(args[0]);

        try {

            //System.out.println("Broj arg" + args.length);
            if (args.length != 5) {
                System.out.println(ANSI_ESC + "31m");
                System.out.println("NeDobar broj argumenata..pokušajte ponovo");
                System.out.println(ANSI_ESC + "0m");
                return;
            } else {

                for (int i = 1; i < args.length; i += 2) {

                    System.out.println("arg " + "broj " + i + args[i]);
                    switch (args[i]) {
                        case "--brg":
                            brojRedaka = args[i + 1];
                            break;
                        case "--brd":
                            brojRedakaKomandi = args[i + 1];
                            break;
                        default:
                            System.out.println(ANSI_ESC + "31m");
                            System.out.println("Unjeli ste nepostojeci argument");
                            System.out.println(ANSI_ESC + "0m");
                    }
                }

            }

            datotekaParametara = args[0];

        } catch (Exception e) {
            System.out.println(ANSI_ESC + "31m");
            System.err.println("Došlo je do pogreške u pokretanju programa...Provjerite ulazne parametre!");
            System.out.println(ANSI_ESC + "0m");
            return;

        }

        DataImporter di = new DataImporter();
        InicijalizatorSustava is = new InicijalizatorSustava(di);
        
        

        
        
        
        ReaderFactory rfp = new ReaderFactory("dispecer");
        FileReader frp = rfp.fileReader();
        List<String[]> zapisiDispecera = frp.getRecords();
        
        List<Vozilo> listaVozila = is.inicijalizacijaVozila();
        //Dispecer d = new Dispecer(wc, is.inicijalizirajPodrucje(), listaVozila, is.inicijalizacijaVozaca(listaVozila));
        
        WindowController wc = new WindowController(is.inicijalizirajPodrucje(), listaVozila, is.inicijalizacijaVozaca(listaVozila));
        
        for(String[] parametri : zapisiDispecera){
                    wc.obradiKomandeDatoteke(parametri);
        }
        //ovoo sve krivo piše jer nije ispisLInije() u kontroleru
        
    }

}
