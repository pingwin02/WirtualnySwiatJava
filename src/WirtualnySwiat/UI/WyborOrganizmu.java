package WirtualnySwiat.UI;

import javax.swing.*;

public class WyborOrganizmu extends JOptionPane {

    private final String[] gatunki =  {"W-Wilk", "L-Lis", "O-Owca", "Z-Żółw", "A-Antylopa", "C-Człowiek", "T-Trawa",
            "M-Mlecz", "G-Guarana", "X-Wilcze Jagody", "B-Barszcz Sosnowskiego",};

    public WyborOrganizmu() {}

    public String getWybor() {
        return (String) showInputDialog(null,
                "Wybierz gatunek: ", "Dodawanie organizmu",
                JOptionPane.PLAIN_MESSAGE, null, gatunki, "Wilk");
    }
}
