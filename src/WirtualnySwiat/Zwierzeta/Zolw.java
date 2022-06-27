package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Zolw extends Zwierze {

    public Zolw(Punkt miejsce, Swiat swiat) {
        super("Żółw", miejsce, 2, 1, "Z", Color.GREEN.darker(), swiat);
    }

    @Override
    public void akcja() {
        if (Swiat.losujLiczbe(0, 4) == 0) {
            super.akcja();
        } else {
            swiat.dodajWpis(this + " nie poruszył się");
        }
    }

    @Override
    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return atakujacy.getSila() < 5;
    }

    @Override
    public Zolw stworzDziecko(Punkt miejsce) {
        return new Zolw(miejsce, swiat);
    }
}
