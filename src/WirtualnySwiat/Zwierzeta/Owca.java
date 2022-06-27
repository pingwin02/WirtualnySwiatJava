package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;

public class Owca extends Zwierze {

    public Owca(Punkt miejsce, Swiat swiat) {
        super("Owca", miejsce, 4, 4, "O", Color.GRAY, swiat);
    }

    @Override
    public Owca stworzDziecko(Punkt miejsce) {
        return new Owca(miejsce, swiat);
    }
}
