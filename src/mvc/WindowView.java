/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import java.util.ArrayList;
import java.util.List;
import org.foi.uzdiz.krigaslje.dz3.main.EZO3;
import static org.foi.uzdiz.krigaslje.dz3.main.EZO3.ANSI_ESC;

/**
 *
 * @author kile
 */
public class WindowView {

    int brojIspisanihLinija = 0;

    List<String> medjuspremnikKomandi;

    public WindowView() {
        medjuspremnikKomandi = new ArrayList<>();

        for (int i = 0; i < Integer.valueOf(EZO3.brojRedakaKomandi) - 1; i++) {
            medjuspremnikKomandi.add(" ");
        }

    }

    public void obrisiEkran() {
        System.out.print(ANSI_ESC + "2J");//brise ekran
    }

    public void initializeWindow() {

        obrisiEkran();
        int br = Integer.valueOf(EZO3.brojRedaka);
        int bs = EZO3.brojStupaca; //broj stupca odakle krece
        int brk = Integer.valueOf(EZO3.brojRedakaKomandi);

        int j = 1;
        //stupci
        for (int i = 2; i < br; i++) {
            ispisiNaPoziciji(i, j, 20, "|");
            ispisiNaPoziciji(i, bs, 20, "|");
        }

        //redki
        for (int i = 2; i < bs; i++) {
            ispisiNaPoziciji(0, i, 20, "_");
            ispisiNaPoziciji(br, i, 20, "-");
            ispisiNaPoziciji(br - brk - 1, i, 20, "-");
        }

        brojIspisanihLinija = 0;
        ispisKomandi();

    }

    public void resetirajKursor() {
        int br = Integer.valueOf(EZO3.brojRedaka);
        postaviKursor(br - 1, 2);
    }

    static void postaviKursor(int x, int y) {
        System.out.print(ANSI_ESC + x + ";" + y + "f");
    }

    public void ispisiLinijuNaPoziciji(int x, int y, int boja, String tekst) {
        postaviKursor(x, y);
        System.out.print(ANSI_ESC + boja + "m");
        System.out.print(String.format("%1$-" + (EZO3.brojStupaca - 2) + "s", tekst));
        System.out.println(ANSI_ESC + "0m");
        resetirajKursor();
    }

    public void ispisiNaPoziciji(int x, int y, int boja, String tekst) {
        postaviKursor(x, y);
        System.out.print(ANSI_ESC + boja + "m");
        System.out.print(tekst);
        System.out.println(ANSI_ESC + "0m");
        resetirajKursor();
    }

    public void zabiljeziKomandu(String a) {
        medjuspremnikKomandi.add(a);
        medjuspremnikKomandi.remove(0);
        ispisKomandi();
    }

    private void ispisKomandi() {
        int br = Integer.valueOf(EZO3.brojRedaka);
        int brk = Integer.valueOf(EZO3.brojRedakaKomandi);
        for (int i = 0; i < medjuspremnikKomandi.size(); i++) {
            ispisiLinijuNaPoziciji(br - brk + i, 2, 20, medjuspremnikKomandi.get(i));
        }
        ispisiLinijuNaPoziciji(br - 1, 2, 20, " ");
    }

    public boolean ispis(String a) {

        int br = Integer.valueOf(EZO3.brojRedaka);
        int brk = Integer.valueOf(EZO3.brojRedakaKomandi);

        ispisiLinijuNaPoziciji(2 + brojIspisanihLinija, 2, 36, a);
        brojIspisanihLinija++;

        if (brojIspisanihLinija == (br - brk - 3)) {
            return false;
        }

        return true;
    }

}
