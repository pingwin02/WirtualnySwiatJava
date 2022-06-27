package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Wilk extends Zwierze {

    public Wilk(Punkt miejsce, Swiat swiat) {
        super("Wilk", miejsce, 9, 5, "W", Color.BLACK, swiat);
    }

    @Override
    public Wilk stworzDziecko(Punkt miejsce) {
        return new Wilk(miejsce, swiat);
    }
}
