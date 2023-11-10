// VentanaPrincipal.java
package ventana;

import pintor.Pintor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Clase que representa la ventana principal de la aplicación
public class VentanaPrincipal extends JPanel implements ObservadorPintor {
    // Declaración de variables de instancia
    private static VentanaPrincipal instancia = null;
    private JFrame ventana;
    private List<Pintor> pintores;

    // Constructor privado para garantizar un único objeto de instancia
    private VentanaPrincipal() {
        // Configuración básica de la ventana principal
        ventana = new JFrame("Ventana Principal");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.add(this); // Agregamos la instancia actual como JPanel

        // Establecemos un tamaño inicial para la ventana principal
        ventana.setSize(500, 500);

        pintores = new ArrayList<>(); // Inicializamos la lista de pintores

        ventana.setVisible(true); // Hacemos visible la ventana principal
    }

    // Método para obtener la instancia única de la ventana principal
    public static VentanaPrincipal obtenerInstancia() {
        if (instancia == null) {
            instancia = new VentanaPrincipal();
        }
        return instancia;
    }

    // Método para agregar un pintor a la lista y forzar la repintura del panel
    public void agregarPintor(Pintor pintor) {
        pintores.add(pintor);
        repaint(); // Forzamos la repintura del panel cuando se agrega un pintor
    }

    // Método que se ejecuta cuando es necesario repintar el panel
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
}
