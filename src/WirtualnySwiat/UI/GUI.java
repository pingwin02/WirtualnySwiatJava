package WirtualnySwiat.UI;

import WirtualnySwiat.MenedzerSave;
import WirtualnySwiat.Swiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JFrame implements ActionListener, KeyListener {

    private final JFrame window;
    private final JMenuBar mb;
    private final JButton nastepna;
    private final JButton nowa, zapisz, wczytaj, wyjdz;
    private final JLabel glowna;
    private final int szerOkna = 500, wysOkna = 1000;
    private JPanel planszaGUI;
    private JScrollPane dziennikScroll;
    private Swiat swiat;

    public GUI() {
        window = new JFrame("Wirtualny Świat v2.0 Damian Jankowski s188597");
        window.setLayout(new GridLayout(2, 1, 20, 20));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(szerOkna, wysOkna);
        window.setLocationRelativeTo(null);
        mb = new JMenuBar();
        nowa = new JButton("Nowa Gra");
        zapisz = new JButton("Zapisz");
        wczytaj = new JButton("Wczytaj");
        wyjdz = new JButton("Wyjdź");
        nastepna = new JButton("Następna Tura");
        nastepna.setBackground(Color.cyan);
        nowa.addActionListener(this);
        nowa.setFocusable(false);
        zapisz.addActionListener(this);
        zapisz.setFocusable(false);
        wczytaj.addActionListener(this);
        wczytaj.setFocusable(false);
        wyjdz.addActionListener(this);
        wyjdz.setFocusable(false);
        nastepna.addActionListener(this);
        nastepna.setFocusable(false);
        mb.add(nowa);
        mb.add(wczytaj);
        mb.add(wyjdz);
        window.setJMenuBar(mb);
        glowna = new JLabel("<html><body style=\"text-align:center\"><h1>Gra Wirtualny Świat v2.0</h1>" +
                "<h2>Autor: Damian Jankowski s188597</h2>" +
                "<h3>Sterowanie:<br/>" +
                "Strzałki - poruszanie się człowiekiem.<br/>" +
                "Naciśnięcie przycisku Następna tura lub klawisza spowoduje " +
                "wykonanie nowej tury<br/>Q - specjalna umiejętność</h3>" +
                "</body></html>");
        window.add(glowna);
        window.addKeyListener(this);
        window.setFocusable(true);
        window.requestFocusInWindow();
        window.setResizable(false);
        window.setVisible(true);
    }

    public int getSzerOkna() {
        return szerOkna;
    }

    public int getWysOkna() {
        return wysOkna;
    }

    public JFrame getWindow() {
        return window;
    }

    private void rysujSwiat() {
        if (swiat == null) return;
        if (swiat.getczyHex() == 0) {
            planszaGUI = new PlanszaKrata(swiat, this);
        } else {
            planszaGUI = new PlanszaHex(swiat, this);
        }

        window.add(planszaGUI);
    }

    private void rysujDziennik() {
        if (swiat == null) return;
        JLabel dziennikGUI = new JLabel("<html>" + swiat.drukujOrganizmy() + swiat.drukujDziennik() + "</html>");
        dziennikGUI.setVerticalAlignment(JLabel.TOP);
        dziennikScroll = new JScrollPane(dziennikGUI, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        window.add(dziennikScroll);
        swiat.getDziennik().clear();
    }

    public void odswiezEkran() {
        if (planszaGUI != null)
            window.remove(planszaGUI);
        if (dziennikScroll != null) {
            window.remove(dziennikScroll);
        }
        if (glowna != null) {
            window.remove(glowna);
        }
        window.remove(mb);
        window.setJMenuBar(mb);
        mb.add(nowa);
        mb.add(zapisz);
        mb.add(wczytaj);
        mb.add(wyjdz);
        mb.add(nastepna);
        rysujSwiat();
        rysujDziennik();
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rozmiarX;
        int rozmiarY;
        if (e.getSource() == nowa) {
            int[] input = new NowySwiat().getWybor();
            if (input == null) return;
            rozmiarX = input[0];
            rozmiarY = input[1];
            int zageszczenie = (int) ((double) input[2] / 100 * (rozmiarX * rozmiarY));
            int czyHex = input[3];
            if (rozmiarX * rozmiarY == 0) throw new Error("Zły rozmiar planszy!");
            swiat = new MenedzerSave().generujGre(zageszczenie, rozmiarX, rozmiarY, czyHex);
        } else if (e.getSource() == zapisz) {
            String nazwa = JOptionPane.showInputDialog(window, "Podaj nazwę pliku", "mojzapis");
            if (nazwa == null) return;
            new MenedzerSave(swiat).generujSave(nazwa);
            swiat.dodajWpis("<br/><p style=\"color:red\">Zapisano grę w " + nazwa + ".sv</p>");
        } else if (e.getSource() == wczytaj) {
            String nazwa = JOptionPane.showInputDialog(window, "Podaj nazwę pliku", "mojzapis");
            if (nazwa == null) return;
            swiat = new MenedzerSave().wczytajSave(nazwa);
            swiat.dodajWpis("<br/><p style=\"color:red\">Wczytano grę z " + nazwa + ".sv</p>");
        } else if (e.getSource() == wyjdz) {
            window.dispose();
            return;
        } else if (e.getSource() == nastepna) {
            swiat.wykonajTure();
        }
        odswiezEkran();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (swiat != null) {
            if (swiat.getCzyZyjeCzlowiek()) {
                swiat.setWybrany(e.getKeyCode());
            }
            swiat.wykonajTure();
            odswiezEkran();
            swiat.setWybrany(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}