package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Mlecz extends Roslina {

    public Mlecz(Punkt miejsce, Swiat swiat) {
        super("Mlecz", miejsce, 0, 0, "M", Color.YELLOW.darker(), swiat);
    }

    @Override
    public void akcja() {
        for (int i = 0; i < 3; i++) {
            super.akcja();
        }
    }

    @Override
    public Mlecz stworzDziecko(Punkt miejsce) {
        return new Mlecz(miejsce, swiat);
    }
}
