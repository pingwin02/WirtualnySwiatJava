package WirtualnySwiat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenedzerSave {

    private Swiat swiat;

    public MenedzerSave() {
    }

    public MenedzerSave(Swiat swiat) {
        this.swiat = swiat;
    }

    public Swiat generujGre(int ilosc, int rozmiarX, int rozmiarY, int czyHex) {

        if (czyHex == 1) {
            swiat = new SwiatHex(rozmiarX, rozmiarY);
        } else {
            swiat = new Swiat(rozmiarX, rozmiarY);
        }

        swiat.dodajOrganizm("C", new Punkt(rozmiarX / 2, rozmiarY / 2), -1, -1, -1);
        ilosc--;
        String[] gatunki = {"W", "O", "L", "Z", "A", "T", "M", "G", "B", "X"};
        while (ilosc > 0) {
            int index = Swiat.losujLiczbe(0, gatunki.length - 1);
            Punkt p;
            do {
                p = new Punkt(Swiat.losujLiczbe(0, rozmiarX - 1), Swiat.losujLiczbe(0, rozmiarY - 1));
            } while (swiat.getPolePlanszy(p) != null);
            swiat.dodajOrganizm(gatunki[index], p, -1, -1, -1);
            ilosc--;
        }

        return swiat;
    }

    public void generujSave(String nazwaPliku) {
        nazwaPliku += ".sv";
        File plik = new File(nazwaPliku);
        PrintWriter pw;
        try {
            plik.createNewFile();
            pw = new PrintWriter(plik);
            pw.print(swiat.getRozmiarX() + " " + swiat.getRozmiarY() + " " + swiat.getczyHex() + "\n");
            pw.print(swiat.getNumerTury() + " " + swiat.getOrganizmy().size() + " " + swiat.getCooldown() + "\n");
            for (Organizm o : swiat.getOrganizmy()) {
                pw.print(o.generujKomende() + "\n");
            }
            pw.print("#ZAPIS GRY WIRTUALNY SWIAT v2.0#\n");
            pw.print("#Damian Jankowski s188597#\n");
            pw.print("#znak polX polY sila inicjatywa wiek#\n");
            pw.print("#[A]ntylopa [C]zlowiek [S]uperman [L]is [O]wca [W]ilk [Z]olw#\n");
            pw.print("#[B]arszcz [G]uarana [M]lecz [T]rawa [X]Wilczejagody#\n");
            pw.print("#Superman - czlowiek z wlaczona umiejetnoscia#\n");
            pw.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Swiat wczytajSave(String nazwaPliku) {
        nazwaPliku += ".sv";
        File plik = new File(nazwaPliku);
        Scanner scanner;
        try {
            scanner = new Scanner(plik);
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
        String[] tab = scanner.nextLine().split(" ");
        int rozmiarX = Integer.parseInt(tab[0]);
        int rozmiarY = Integer.parseInt(tab[1]);
        if (rozmiarX <= 0 || rozmiarY <= 0) {
            throw new Error("Błędny zapis");
        }
        int czyHex = Integer.parseInt(tab[2]);
        if (czyHex == 1) {
            swiat = new SwiatHex(rozmiarX, rozmiarY);
        } else {
            swiat = new Swiat(rozmiarX, rozmiarY);
        }
        tab = scanner.nextLine().split(" ");
        int numerTury = Integer.parseInt(tab[0]);
        swiat.setNumerTury(numerTury);
        for (int i = 0; i < Integer.parseInt(tab[1]); i++) {
            String linia = scanner.nextLine();
            String[] info = linia.split(" ");
            swiat.dodajOrganizm(info[0], new Punkt(Integer.parseInt(info[1]), Integer.parseInt(info[2])),
                    Integer.parseInt(info[3]), Integer.parseInt(info[4]), Integer.parseInt(info[5]));
        }
        swiat.setCooldown(Integer.parseInt(tab[2]));
        scanner.close();

        return swiat;
    }
}
