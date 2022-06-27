package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;
import WirtualnySwiat.Zwierzeta.Zwierze;

import java.awt.*;

public class BarszczSosnowskiego extends Roslina {

    public BarszczSosnowskiego(Punkt miejsce, Swiat swiat) {
        super("Barszcz Sosnowskiego", miejsce, 10, 0, "B", new Color(238, 9, 182), swiat);
    }

    @Override
    public void akcja() {

        int[][] kier = swiat.getKierunki();

        for (int i = 0; i < kier.length; i++) {
                Punkt miejsce = new Punkt(getLokacja().getX() + kier[i][0], getLokacja().getY() + kier[i][1]);
                if (swiat.sprawdzGranice(miejsce)) {
                    Organizm obok = swiat.getPolePlanszy(miejsce);
                    if (obok instanceof Zwierze) {
                        swiat.dodajWpis(this + " zabił " + obok);
                        obok.setZyje(false);
                        swiat.setPolePlanszy(obok.getLokacja(), null);
                    }
                }
        }

        super.akcja();
    }

    @Override
    protected boolean czyZjadlSpecjalna(Organizm jedzacy) {
        setZyje(false);
        swiat.setPolePlanszy(getLokacja(), null);
        return true;
    }

    @Override
    public BarszczSosnowskiego stworzDziecko(Punkt miejsce) {
        return new BarszczSosnowskiego(miejsce, swiat);
    }
}
