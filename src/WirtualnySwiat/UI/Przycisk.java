package WirtualnySwiat.UI;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;

public class Przycisk extends JButton {

    protected Font czcionka;
    protected Punkt p;
    protected Swiat swiat;
    protected GUI okno;

    public Przycisk(Swiat swiat, Punkt p, GUI okno) {
        this.swiat = swiat;
        this.p = p;
        this.okno = okno;
        this.czcionka = new Font("Arial", Font.BOLD, Integer.min(okno.getSzerOkna() / swiat.getRozmiarX() - 7,
                okno.getWysOkna() / swiat.getRozmiarY() - 7));
        setToolTipText(String.valueOf(p));
        Organizm miejsce = swiat.getPolePlanszy(p);
        setMargin(new Insets(1, 0, 1, 0));
        if (miejsce != null) {
            setText(miejsce.getZnak());
            setFont(czcionka);
            setEnabled(false);
            setBackground(miejsce.rysowanie());
            setUI(new MetalButtonUI() {
                protected Color getDisabledTextColor() {
                    return Color.WHITE;
                }
            });
        } else {
            addActionListener(e -> {
                String wybor = new WyborOrganizmu().getWybor();
                if (wybor != null) {
                    swiat.dodajOrganizm(wybor, p, -1, -1, -1);
                }
                okno.odswiezEkran();
            });
            setBackground(Color.WHITE);
            setFocusable(false);
        }
    }
}