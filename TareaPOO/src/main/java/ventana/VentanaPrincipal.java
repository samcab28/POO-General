// VentanaPrincipal.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JPanel implements ObservadorPintor {
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

        // Notificamos a los observadores (en este caso, a la VentanaPrincipal) sobre el pintor agregado
        if (pintor != null) {
            String infoPintor = pintor.getClass().getSimpleName();
            notificarPintorAgregado(infoPintor);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Verificar si hay un pintor asignado y pintar si es así
        if (pintor != null) {
            pintor.pintar(g, getWidth(), getHeight());
        }
    }

    // Implementación del método de la interfaz ObservadorPintores
    @Override
    public void pintorAgregado(String infoPintor) {
        // Puedes realizar cualquier acción que desees cuando se agrega un pintor
        System.out.println("Pintor agregado: " + infoPintor);
    }

    // Método para agregar observadores (en este caso, la VentanaPrincipal)
    public void agregarObservadorPintores(ObservadorPintor observador) {
        // En este ejemplo, solo hay un observador (la VentanaPrincipal)
        // Pero puedes gestionar múltiples observadores si es necesario
    }

    // Método para notificar a los observadores sobre el pintor agregado
    private void notificarPintorAgregado(String infoPintor) {
        agregarObservadorPintores(this); // Agregamos a la VentanaPrincipal como observador
        pintorAgregado(infoPintor);
    }
}
