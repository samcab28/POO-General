// VentanaPrincipal.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaPrincipal extends JPanel implements ObservadorPintor {
    private static VentanaPrincipal instancia = null;
    private JFrame ventana;
    private List<Pintor> pintores;

    private VentanaPrincipal() {
        ventana = new JFrame("Ventana Principal");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.add(this); // Agregamos la instancia actual como JPanel

        // Establecemos un tamaño inicial para la ventana principal
        ventana.setSize(400, 300);

        pintores = new ArrayList<>(); // Inicializamos la lista de pintores

        ventana.setVisible(true);
    }

    public static VentanaPrincipal obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    public void agregarPintor(Pintor pintor) {
        pintores.add(pintor);
        repaint(); // Forzamos la repintura del panel cuando se agrega un pintor
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Pintar todos los pintores almacenados
        for (Pintor p : pintores) {
            p.pintar(g, getWidth(), getHeight());
        }
    }

    // Implementación del método de la interfaz ObservadorPintor
    @Override
    public void pintorAgregado(Pintor nuevoPintor) {
        // Puedes realizar cualquier acción que desees cuando se agrega un pintor
        System.out.println("Pintor agregado: " + nuevoPintor.getClass().getSimpleName());
    }

    // Método para agregar observadores (en este caso, la VentanaPrincipal)
    public void agregarObservadorPintores(ObservadorPintor observador) {
        // En este ejemplo, solo hay un observador (la VentanaPrincipal)
        // Pero puedes gestionar múltiples observadores si es necesario
    }
}
