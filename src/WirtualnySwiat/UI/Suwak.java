package WirtualnySwiat.UI;

import javax.swing.*;

public class Suwak extends JSlider {

    public Suwak(int ticks, int max) {
        setMinimum(0);
        setMaximum(max);
        setValue(20);
        setMajorTickSpacing(ticks);
        setSnapToTicks(true);
        setPaintTicks(true);
        setPaintLabels(true);
    }

}
