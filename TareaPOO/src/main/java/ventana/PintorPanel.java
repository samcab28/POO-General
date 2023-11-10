// PintorPanel.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;

public class PintorPanel extends JPanel {
    private Pintor pintor;

    public PintorPanel(Pintor pintor) {
        this.pintor = pintor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (pintor != null) {
            pintor.pintar(g, getWidth(), getHeight());
        }
    }
}