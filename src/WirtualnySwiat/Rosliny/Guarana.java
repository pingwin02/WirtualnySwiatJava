package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Guarana extends Roslina {

    public Guarana(Punkt miejsce, Swiat swiat) {
        super("Guarana", miejsce, 0, 0, "G", Color.BLUE.brighter(), swiat);
    }

    @Override
    protected boolean czyZjadlSpecjalna(Organizm jedzacy) {
        jedzacy.setSila(jedzacy.getSila() + 3);
        return true;
    }

    @Override
    public Guarana stworzDziecko(Punkt miejsce) {
        return new Guarana(miejsce, swiat);
    }
}
