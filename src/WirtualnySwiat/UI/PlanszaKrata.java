package WirtualnySwiat.UI;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import javax.swing.*;
import java.awt.*;

public class PlanszaKrata extends JPanel {

    public PlanszaKrata(Swiat swiat, GUI okno) {
        int rozmiarX = swiat.getRozmiarX();
        int rozmiarY = swiat.getRozmiarY();
        setLayout(new GridLayout(rozmiarY, rozmiarX));
        okno.getWindow().setSize(okno.getSzerOkna(),okno.getWysOkna());

        for (int y = 0; y < rozmiarY; y++) {
            for (int x = 0; x < rozmiarX; x++) {
                JButton przycisk = new Przycisk(swiat, new Punkt(x, y), okno);
                add(przycisk);
            }
        }

    }
}
