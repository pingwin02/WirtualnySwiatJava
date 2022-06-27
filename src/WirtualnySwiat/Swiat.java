package WirtualnySwiat;

import WirtualnySwiat.Rosliny.*;
import WirtualnySwiat.Zwierzeta.*;

import java.awt.*;
import java.util.Comparator;
import java.util.Random;
import java.util.Vector;
import java.util.stream.Collectors;

public class Swiat {
    private final Vector<String> dziennik = new Vector<>();
    private final Color specjalnyKolor = new Color(255, 136, 0);
    private Vector<Organizm> organizmy = new Vector<>();
    private int wybrany;
    private Organizm[][] plansza;
    private int rozmiarX, rozmiarY;
    private int numerTury;
    private Boolean czyZyjeCzlowiek = false;
    private int cooldown = 0;

    public Swiat(int rozmiarX, int rozmiarY) {
        this.rozmiarX = rozmiarX;
        this.rozmiarY = rozmiarY;
        this.plansza = new Organizm[rozmiarY][rozmiarX];
    }

    public static int losujLiczbe(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public int[][] getKierunki() {
        return new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    }

    public Boolean getCzyZyjeCzlowiek() {
        return czyZyjeCzlowiek;
    }

    public int getWybrany() {
        return wybrany;
    }

    public void setWybrany(int wybrany) {
        this.wybrany = wybrany;
    }

    public Organizm getPolePlanszy(Punkt p) {
        return plansza[p.getY()][p.getX()];
    }

    public void setPolePlanszy(Punkt p, Organizm o) {
        plansza[p.getY()][p.getX()] = o;
    }

    public Vector<String> getDziennik() {
        return dziennik;
    }

    public Vector<Organizm> getOrganizmy() {
        return organizmy;
    }

    public int getNumerTury() {
        return numerTury;
    }

    public void setNumerTury(int numerTury) {
        this.numerTury = numerTury;
    }

    public int getRozmiarX() {
        return rozmiarX;
    }

    public int getRozmiarY() {
        return rozmiarY;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public int getczyHex() {
        return 0;
    }

    public Color getSpecjalnyKolor() {
        return specjalnyKolor;
    }

    public void dodajWpis(String wiad) {
        dziennik.add(wiad);
    }

    public void dodajWpis(Organizm o, Punkt p) {
        dziennik.add(o + " chce iść " + p);
    }

    public boolean sprawdzGranice(Punkt p) {
        return p.getX() < rozmiarX && p.getY() < rozmiarY && p.getX() >= 0 && p.getY() >= 0;
    }

    public Punkt sprawdzMiejsce(Punkt p) {
        for (int i = 0; i < getKierunki().length; i++) {
            Punkt miejsce = new Punkt(p.getX() + getKierunki()[i][0], p.getY() + getKierunki()[i][1]);
            if (sprawdzGranice(miejsce)) {
                if (getPolePlanszy(miejsce) == null) {
                    return miejsce;
                }
            }
        }

        dodajWpis("Wokół " + p + " nie ma miejsca");
        return null;
    }

    public Punkt losujRuch(Punkt p, int zasieg) {

        int mnoznik = losujLiczbe(1, zasieg);
        int i = losujLiczbe(0, getKierunki().length - 1);
        Punkt temp = new Punkt(p.getX() + mnoznik * getKierunki()[i][0], p.getY() + mnoznik * getKierunki()[i][1]);
        if (!sprawdzGranice(temp)) {
            return p;
        }
        return temp;
    }

    public void dodajOrganizm(Organizm nowy) {
        Punkt miejsce = nowy.getLokacja();

        if (sprawdzGranice(miejsce)) {
            if (getPolePlanszy(miejsce) != null) {
                throw new Error("Błąd zapisu! Miejsce " + miejsce + " zajęte");
            } else {
                if (nowy instanceof Czlowiek) czyZyjeCzlowiek = true;
                organizmy.add(nowy);
                setPolePlanszy(miejsce, nowy);
            }
        } else {
            throw new Error("Błąd zapisu! Miejsce " + miejsce + " nie istnieje");
        }
    }

    public void dodajOrganizm(String znak, Punkt miejsce, int sila, int inicjatywa, int wiek) {

        Organizm nowy = null;

        znak = String.valueOf(znak.charAt(0));

        switch (znak) {
            case "W": {
                nowy = new Wilk(miejsce, this);
                break;
            }
            case "O": {
                nowy = new Owca(miejsce, this);
                break;
            }
            case "L": {
                nowy = new Lis(miejsce, this);
                break;
            }
            case "Z": {
                nowy = new Zolw(miejsce, this);
                break;
            }
            case "A": {
                nowy = new Antylopa(miejsce, this);
                break;
            }
            case "C": {
                nowy = new Czlowiek(miejsce, this);
                break;
            }
            case "S": {
                nowy = new Czlowiek(miejsce, this);
                cooldown = 10;
                nowy.setZnak("S");
                nowy.setKolor(specjalnyKolor);
                break;
            }
            case "T": {
                nowy = new Trawa(miejsce, this);
                break;
            }
            case "M": {
                nowy = new Mlecz(miejsce, this);
                break;
            }
            case "G": {
                nowy = new Guarana(miejsce, this);
                break;
            }
            case "B": {
                nowy = new BarszczSosnowskiego(miejsce, this);
                break;
            }
            case "X": {
                nowy = new WilczeJagody(miejsce, this);
                break;
            }
        }

        if (nowy instanceof Czlowiek && czyZyjeCzlowiek) {
            throw new Error("Błąd. Człowiek już istnieje!");
        }

        if (nowy != null) {
            dodajOrganizm(nowy);
            dodajWpis("<span style=\"color:red\">Dodano " + nowy + "</span>");
            if (sila != -1 && inicjatywa != -1 && wiek != -1) {
                nowy.setSila(sila);
                nowy.setInicjatywa(inicjatywa);
                nowy.setWiek(wiek);
            }
        }

    }

    public StringBuilder getInfoCooldown() {
        StringBuilder info = new StringBuilder();
        if (cooldown > 5) {
            info.append("<span style=\"color:red; font-size:11px\">Umiejętność Szybkość antylopy aktywna jeszcze przez " + (cooldown - 5) + " tur</span><br/>");
        }
        if (cooldown < 8 && cooldown > 5) {
            info.append("<span style=\"color:red; font-size:11px\">Prawdopodobieństwo 50%</span><br/>");
        }
        if (cooldown < 6 && cooldown > 0) {
            info.append("<span style=\"color:red; font-size:11px\">Nie możesz użyć umiejętności jeszcze przez " + cooldown + " tur</span><br/>");
        }
        return info;
    }

    public String drukujOrganizmy() {
        StringBuilder lista = new StringBuilder();
        if (czyZyjeCzlowiek) {
            lista.append("<p style=\"color:red\">Człowiek żyje! Poruszaj się strzałkami.</p>");
            lista.append(getInfoCooldown());
        }
        lista.append("<h3>Dziennik:</h3>Na świecie jest <span style=\"font-size:25px\">" + organizmy.size() + "</span> organizmów.<br/>");
        sortujOrganizmy();
        int i = 0;
        for (Organizm o : organizmy) {
            if (i > 9) {
                lista.append("...");
                break;
            }
            lista.append(o.info());
            lista.append("<br/>");
            i++;
        }
        lista.append("<br/>Tura nr <span style=\"font-size:25px\">" + numerTury + "</span><br/>");
        return lista.toString();
    }

    public String drukujDziennik() {
        StringBuilder lista = new StringBuilder();
        int i = 0;
        for (String wiad : dziennik) {
            if (i > 29) {
                lista.append("...");
                break;
            }
            lista.append(wiad);
            lista.append("<br/>");
            i++;
        }
        return lista.toString();
    }

    private void sortujOrganizmy() {
        organizmy = organizmy.stream().sorted(Comparator.comparing(Organizm::getInicjatywa).
                thenComparing(Organizm::getWiek).reversed()).collect(Collectors.toCollection(Vector::new));
    }

    private void usunMartwe() {
        for (int i = organizmy.size() - 1; i >= 0; i--) {
            Organizm martwy = organizmy.get(i);
            if (!martwy.getZyje()) {
                if (martwy instanceof Czlowiek) {
                    czyZyjeCzlowiek = false;
                    cooldown = 0;
                }
                organizmy.remove(martwy);
            }
        }
    }

    public void wykonajTure() {

        sortujOrganizmy();

        for (Organizm o : organizmy) {
            if (o.getNarodzony()) {
                o.setNarodzony(false);
            }
        }

        for (int i = 0; i < organizmy.size(); i++) {
            Organizm org = organizmy.get(i);
            if (!org.getNarodzony() && org.getZyje()) {
                org.akcja();
                dodajWpis("-----");
            }
            org.setWiek(org.getWiek() + 1);
        }

        usunMartwe();
        numerTury++;
    }

}
