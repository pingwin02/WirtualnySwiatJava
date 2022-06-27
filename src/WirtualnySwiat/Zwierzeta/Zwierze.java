package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public abstract class Zwierze extends Organizm {

    Zwierze(String nazwa, Punkt miejsce, int sila, int inicjatywa, String znak, Color kolor, Swiat swiat) {
        super(nazwa, miejsce, sila, inicjatywa, znak, kolor, swiat);
    }

    @Override
    public void akcja() {
        Punkt cel = swiat.losujRuch(getLokacja(), 1);
        swiat.dodajWpis(this, cel);
        kolizja(cel);
    }
}
