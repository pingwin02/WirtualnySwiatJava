package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public abstract class Roslina extends Organizm {

    Roslina(String nazwa, Punkt miejsce, int sila, int inicjatywa, String znak, Color kolor, Swiat swiat) {
        super(nazwa, miejsce, sila, inicjatywa, znak, kolor, swiat);
    }

    @Override
    public void akcja() {
        swiat.dodajWpis(this + " próbuje się zasiać");
        if (Swiat.losujLiczbe(0, 25) == 0 && !getNarodzony()) {
            Punkt p = swiat.sprawdzMiejsce(getLokacja());
            if (p != null) {
                Organizm dziecko = stworzDziecko(p);
                dziecko.setNarodzony(true);
                swiat.dodajOrganizm(dziecko);
                swiat.dodajWpis(this + " zasiał się");
            }
        }
    }
}
