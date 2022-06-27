package WirtualnySwiat.UI;

import javax.swing.*;
import java.util.Objects;

public class NowySwiat extends JOptionPane {

    private final JSlider sliderX, sliderY, sliderZag;

    private final ButtonGroup wybor;
    private final JComponent[] inputs;

    public NowySwiat() {

        sliderX = new Suwak(5, 50);
        sliderY = new Suwak(5, 50);
        sliderZag = new Suwak(10, 100);
        wybor = new ButtonGroup();
        JRadioButton krata = new JRadioButton("Krata");
        krata.setSelected(true);
        krata.setActionCommand(krata.getText());
        JRadioButton hex = new JRadioButton("Hex");
        hex.setActionCommand(hex.getText());
        wybor.add(krata);
        wybor.add(hex);

        inputs = new JComponent[]{
                new JLabel("Podaj szerokość"),
                sliderX,
                new JLabel("Podaj wysokość"),
                sliderY,
                new JLabel("Podaj zagęszczenie organizmów w %"),
                sliderZag,
                new JLabel("Wybierz abstrakcję dla świata"),
                krata, hex,

        };
    }

    public int[] getWybor() {
        if (JOptionPane.showConfirmDialog(null, inputs, "Tworzenie nowego świata", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
            return null;
        int czyHex = 0;
        if (Objects.equals(wybor.getSelection().getActionCommand(), "Hex")) {
            czyHex = 1;
        }
        return new int[]{sliderX.getValue(), sliderY.getValue(), sliderZag.getValue(), czyHex};
    }
}
