package WirtualnySwiat.Zwierzeta;

import WirtualnySwiat.Punkt;
import WirtualnySwiat.Swiat;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Czlowiek extends Zwierze {
    public Czlowiek(Punkt miejsce, Swiat swiat) {
        super("Czlowiek", miejsce, 5, 4, "C", Color.RED, swiat);
    }

    @Override
    public void akcja() {

        int x = getLokacja().getX(), y = getLokacja().getY(), mnoznik = 1;

        int wybrany = swiat.getWybrany();

        int cooldown = swiat.getCooldown();

        if (wybrany == KeyEvent.VK_Q && cooldown == 0) {
            cooldown = 11;
            setKolor(swiat.getSpecjalnyKolor());
            setZnak("S");
        }
        if (cooldown > 5) {
            mnoznik = 2;
        }
        if (cooldown == 7) {
            mnoznik = Swiat.losujLiczbe(1, 2);
        } else if (cooldown == 6) {
            setZnak("C");
            setKolor(Color.RED);
        }
        if (cooldown > 0) {
            cooldown--;
        }
        swiat.setCooldown(cooldown);

        if (wybrany == KeyEvent.VK_UP) {
            if (y > -1 + mnoznik) y -= mnoznik;
        } else if (wybrany == KeyEvent.VK_DOWN) {
            if (y < swiat.getRozmiarY() - mnoznik) y += mnoznik;
        } else if (wybrany == KeyEvent.VK_RIGHT) {
            if (x < swiat.getRozmiarX() - mnoznik) x += mnoznik;
        } else if (wybrany == KeyEvent.VK_LEFT) {
            if (x > -1 + mnoznik) x -= mnoznik;
        } else {
            swiat.dodajWpis(this + " nie poruszył się");
            return;
        }
        Punkt cel = new Punkt(x, y);
        swiat.dodajWpis(this, cel);
        kolizja(cel);
    }

    @Override
    public Czlowiek stworzDziecko(Punkt miejsce) {
        return null;
    }
}
