package ventana;

import javax.swing.*;

public class VentanaPrincipal {
    private static VentanaPrincipal instancia = null;
    private JFrame ventana;

    private VentanaPrincipal() {
        ventana = new JFrame("Ventana Principal");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static VentanaPrincipal obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    public void mostrarVentana(int ancho, int alto) {
        ventana.setSize(ancho, alto);
        ventana.setVisible(true);
    }
}
