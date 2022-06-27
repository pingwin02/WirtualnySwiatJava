package WirtualnySwiat.UI;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class PrzyciskHex extends Przycisk {
    private final double dlugoscBoku, wysokoscPrzycisku;

    private final Polygon ksztalt;

    public PrzyciskHex(Punkt p, Swiat swiat, GUI okno, double wysokoscPrzycisku) {
        super(swiat, p, okno);
        this.wysokoscPrzycisku = wysokoscPrzycisku;
        this.czcionka = new Font("Arial", Font.BOLD, (int) (wysokoscPrzycisku - wysokoscPrzycisku / 5));
        dlugoscBoku = wysokoscPrzycisku / 2;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setText(null);
        ksztalt = new Polygon();
        for (int i = 0; i < 6; i++) {
            ksztalt.addPoint((int) (dlugoscBoku + dlugoscBoku * Math.cos(i * Math.PI / 3 - Math.PI / 6)),
                    (int) (dlugoscBoku + dlugoscBoku * Math.sin(i * Math.PI / 3 - Math.PI / 6)));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (swiat.getPolePlanszy(p) != null) {
            g.setColor(swiat.getPolePlanszy(p).rysowanie());
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillPolygon(ksztalt);
        if (swiat.getPolePlanszy(p) != null) {
            g.setColor(Color.WHITE);
            g.setFont(czcionka);
            g.drawString(swiat.getPolePlanszy(p).getZnak(), (int) (0.2 * wysokoscPrzycisku), (int) (1.65 * dlugoscBoku));
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return ksztalt.contains(x, y);
    }
}
