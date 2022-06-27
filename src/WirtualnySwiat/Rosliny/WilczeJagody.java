package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Organizm;
import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class WilczeJagody extends Roslina {

    public WilczeJagody(Punkt miejsce, Swiat swiat) {
        super("Wilcze Jagody", miejsce, 99, 0, "X", new Color(81, 58, 124), swiat);
    }

    @Override
    protected boolean czyZjadlSpecjalna(Organizm jedzacy) {
        setZyje(false);
        swiat.setPolePlanszy(getLokacja(), null);
        return true;
    }

    @Override
    public WilczeJagody stworzDziecko(Punkt miejsce) {
        return new WilczeJagody(miejsce, swiat);
    }
}
