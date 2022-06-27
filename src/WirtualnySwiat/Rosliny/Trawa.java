package WirtualnySwiat.Rosliny;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Trawa extends Roslina {

    public Trawa(Punkt miejsce, Swiat swiat) {
        super("Trawa", miejsce, 0, 0, "T", Color.GREEN, swiat);
    }

    @Override
    public Trawa stworzDziecko(Punkt miejsce) {
        return new Trawa(miejsce, swiat);
    }
}
