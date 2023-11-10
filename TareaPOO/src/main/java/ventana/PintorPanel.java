// PintorPanel.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;

// Clase que representa un panel para dibujar un Pintor en específico
public class PintorPanel extends JPanel {
    private Pintor pintor; // Variable que almacena el Pintor a ser dibujado en el panel

    // Método que se ejecuta cuando es necesario repintar el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Verificar si hay un Pintor asignado antes de intentar pintar
        if (pintor != null) {
            // Llamar al método pintar del Pintor actual con las dimensiones del panel
            pintor.pintar(g, getWidth(), getHeight());
        }
    }
}
