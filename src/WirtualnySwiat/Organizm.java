package WirtualnySwiat;

import WirtualnySwiat.Rosliny.Roslina;

import java.awt.*;
import java.util.Objects;

public abstract class Organizm implements InterfejsOrganizm {
    private final String nazwa;
    protected Swiat swiat;
    private int sila, inicjatywa, wiek;
    private Punkt lokacja;
    private String znak;
    private boolean zyje = true, narodzony = false;
    private Color kolor;

    public Organizm(String nazwa, Punkt miejsce, int sila, int inicjatywa, String znak, Color kolor, Swiat swiat) {
        this.nazwa = nazwa;
        this.lokacja = miejsce;
        this.sila = sila;
        this.inicjatywa = inicjatywa;
        this.swiat = swiat;
        this.znak = znak;
        this.kolor = kolor;
    }

    @Override
    public String toString() {
        return nazwa + lokacja;
    }

    public String info() {
        return nazwa + lokacja + " Sila:" + sila + " Inicjatywa:" + inicjatywa + " Wiek:" + wiek;
    }

    public String generujKomende() {
        return znak + " " + lokacja.getX() + " " + lokacja.getY() + " " + getSila() + " " + getInicjatywa() + " " + getWiek();
    }

    protected boolean czyOdbilAtak(Organizm atakujacy) {
        return false;
    }

    protected boolean czyUniknalAtak(Organizm atakujacy) {
        return false;
    }

    protected boolean czyZjadlSpecjalna(Organizm jedzacy) {
        return false;
    }


    public void kolizja(Punkt miejsce) {
        Organizm atakowany = swiat.getPolePlanszy(miejsce);

        if (atakowany != null && atakowany != this) {
            if (Objects.equals(atakowany.getNazwa(), nazwa)) {
                swiat.dodajWpis(this + " próbuje urodzić");
                if (Swiat.losujLiczbe(0, 3) > 0) {
                    swiat.dodajWpis(this + " poronił");
                    return;
                }
                Punkt p = swiat.sprawdzMiejsce(miejsce);
                if (p != null) {
                    Organizm dziecko = stworzDziecko(p);
                    dziecko.setNarodzony(true);
                    swiat.dodajOrganizm(dziecko);
                    swiat.dodajWpis(this + " rozmnożył się");
                }
            } else if ((zyje && atakowany.getZyje())) {
                atakowanie(atakowany);
            } else {
                swiat.dodajWpis(this + " nie ma miejsca na ruch");
            }
        } else if (atakowany == this) {
            swiat.dodajWpis(this + " nie poruszył się");
        } else {
            swiat.dodajWpis(this + " sukces!");
            swiat.setPolePlanszy(lokacja, null);
            lokacja = new Punkt(miejsce);
            swiat.setPolePlanszy(lokacja, this);
        }
    }

    protected void atakowanie(Organizm atakowany) {
        if (atakowany.czyOdbilAtak(this)) {
            swiat.dodajWpis(atakowany + " odbił atak od " + this);
        } else if (atakowany.czyUniknalAtak(this)) {
            swiat.dodajWpis(atakowany + " uniknął ataku od " + this);
        } else if (atakowany.getSila() >= sila) {
            if (atakowany.czyZjadlSpecjalna(this)) {
                swiat.dodajWpis(this + " doznał efektu od " + atakowany);
            }
            swiat.dodajWpis(atakowany + " zabił " + this);
            zyje = false;
            swiat.setPolePlanszy(lokacja, null);
        } else if (atakowany.getSila() < sila) {
            if (atakowany.czyZjadlSpecjalna(this)) {
                swiat.dodajWpis(this + " doznał efektu od " + atakowany);
            }
            if (atakowany instanceof Roslina) {
                swiat.dodajWpis(this + " zjadł " + atakowany);
            } else {
                swiat.dodajWpis(this + " zabił " + atakowany);
            }
            atakowany.setZyje(false);
            swiat.setPolePlanszy(atakowany.getLokacja(), this);
            swiat.setPolePlanszy(lokacja, null);
            lokacja = new Punkt(atakowany.getLokacja());
        }

    }

    public boolean getZyje() {
        return this.zyje;
    }

    public void setZyje(boolean zyje) {
        this.zyje = zyje;
    }

    public Color rysowanie() {
        return kolor;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public int getInicjatywa() {
        return inicjatywa;
    }

    public void setInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    public int getSila() {
        return sila;
    }

    public void setSila(int sila) {
        this.sila = sila;
    }

    public boolean getNarodzony() {
        return narodzony;
    }

    public void setNarodzony(boolean narodzony) {
        this.narodzony = narodzony;
    }

    public String getNazwa() {
        return nazwa;
    }

    public Punkt getLokacja() {
        return lokacja;
    }

    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }

    public String getZnak() {
        return znak;
    }

    public void setZnak(String znak) {
        this.znak = znak;
    }
}
