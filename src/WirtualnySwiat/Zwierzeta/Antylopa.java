package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Antylopa extends Zwierze {

    public Antylopa(Punkt miejsce, Swiat swiat) {
        super("Antylopa", miejsce, 4, 4, "A", Color.PINK.darker(), swiat);
    }

    @Override
    public void akcja() {
        Punkt cel = swiat.losujRuch(getLokacja(), 2);
        swiat.dodajWpis(this, cel);
        kolizja(cel);
    }

    @Override
    protected void atakowanie(Organizm atakowany) {
        if (Swiat.losujLiczbe(0, 1) == 0) {
            swiat.dodajWpis(this + " nie udało się uciec przed " + atakowany);
            super.atakowanie(atakowany);
        } else {
            swiat.dodajWpis(this + " chce uciec przed zaatakowaniem " + atakowany);
            Punkt p = swiat.sprawdzMiejsce(atakowany.getLokacja());
            if (p != null) {
                swiat.dodajWpis(this, p);
                kolizja(p);
            } else {
                swiat.dodajWpis(this + " nie może uciec przed " + atakowany);
                super.atakowanie(atakowany);
            }
        }
    }

    @Override
    protected boolean czyUniknalAtak(Organizm atakujacy) {
        if (Swiat.losujLiczbe(0, 1) == 0) {
            swiat.dodajWpis(this + " nie udało się uciec przed " + atakujacy);
            return false;
        } else {
            swiat.dodajWpis(this + " chce uciec przed atakującym " + atakujacy);
            Punkt p = swiat.sprawdzMiejsce(getLokacja());
            if (p != null) {
                swiat.dodajWpis(this, p);
                Punkt temp = new Punkt(getLokacja());
                kolizja(p);
                atakujacy.kolizja(temp);
                return true;
            } else {
                swiat.dodajWpis(this + " nie może uciec przed " + atakujacy);
                return false;
            }
        }
    }

    @Override
    public Antylopa stworzDziecko(Punkt miejsce) {
        return new Antylopa(miejsce, swiat);
    }
}
