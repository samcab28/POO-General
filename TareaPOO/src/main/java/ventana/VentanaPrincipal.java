// VentanaPrincipal.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JPanel {
    private static VentanaPrincipal instancia = null;
    private JFrame ventana;
    private Pintor pintor;

    private VentanaPrincipal() {
        ventana = new JFrame("Ventana Principal");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.add(this); // Agregamos la instancia actual como JPanel

        // Establecemos un tamaño inicial para la ventana principal
        ventana.setSize(400, 300);
        ventana.setVisible(true);
    }

    public static VentanaPrincipal obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    public void setPintor(Pintor pintor) {
        this.pintor = pintor;
        repaint(); // Forzamos la repintura del panel cuando se cambia el pintor
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Verificar si hay un pintor asignado y pintar si es así
        if (pintor != null) {
            pintor.pintar(g, getWidth(), getHeight());
        }
    }
}
