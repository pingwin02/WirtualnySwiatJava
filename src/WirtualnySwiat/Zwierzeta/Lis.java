package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Lis extends Zwierze {

    public Lis(Punkt miejsce, Swiat swiat) {
        super("Lis", miejsce, 3, 7, "L", new Color(255, 213, 0), swiat);
    }

    @Override
    public Lis stworzDziecko(Punkt miejsce) {
        return new Lis(miejsce, swiat);
    }

    @Override
    protected void atakowanie(Organizm atakowany) {
        if (atakowany.getSila() <= getSila()) {
            super.atakowanie(atakowany);
        } else {
            swiat.dodajWpis(this + " wyczuł " + atakowany);
        }
    }
}
