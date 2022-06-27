package WirtualnySwiat.UI;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import javax.swing.*;

public class PlanszaHex extends JPanel {
    public PlanszaHex(Swiat swiat, GUI okno) {
        setLayout(null);
        int wysPrzycisku = Integer.min(okno.getWysOkna() / swiat.getRozmiarY() / 2,
                okno.getSzerOkna() / swiat.getRozmiarX());
        double wysTrojRown = wysPrzycisku * Math.sqrt(3) / 2;
        int poprSzerOkna = (swiat.getRozmiarX() + (swiat.getRozmiarY() - 1) / 2) * wysPrzycisku + 50;
        okno.getWindow().setSize(Integer.max(poprSzerOkna, okno.getSzerOkna()), okno.getWysOkna());
        double offsetY = 0;
        double offsetX = 0;

        for (int y = 0; y < swiat.getRozmiarY(); y++) {
            for (int x = 0; x < swiat.getRozmiarX(); x++) {
                JButton przycisk = new PrzyciskHex(new Punkt(x, y), swiat, okno, wysPrzycisku);
                add(przycisk);
                przycisk.setBounds((int) offsetX, (int) offsetY, wysPrzycisku, wysPrzycisku);
                offsetX += wysPrzycisku;
            }
            offsetY += wysTrojRown;
            offsetX = (y + 1) * wysPrzycisku / 2;
        }
    }
}
