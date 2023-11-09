// VentanaInicio.java
package ventana;

import pintor.Pintor;
import pintor.PintorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaInicio {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ventana Inicio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        JLabel label = new JLabel("Ingrese las dimensiones de la nueva ventana:");
        frame.add(label);

        JTextField anchoTextField = new JTextField(5);
        JTextField altoTextField = new JTextField(5);

        JButton crearVentanaButton = new JButton("Crear Ventana");
        frame.add(anchoTextField);
        frame.add(new JLabel("x"));
        frame.add(altoTextField);
        frame.add(crearVentanaButton);

        crearVentanaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int ancho = Integer.parseInt(anchoTextField.getText());
                    int alto = Integer.parseInt(altoTextField.getText());

                    VentanaPrincipal ventanaPrincipal = VentanaPrincipal.obtenerInstancia();
                    ventanaPrincipal.setSize(ancho, alto);

                    // Asignar un pintor a la ventana principal (por ejemplo, rayas)
                    Pintor pintorRayas = PintorFactory.crearPintor("rayas");
                    ventanaPrincipal.setPintor(pintorRayas);

                    // Crear y mostrar la ventana de control después de crear la ventana principal
                    VentanaControl ventanaControl = new VentanaControl();
                    ventanaControl.setVisible(true);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese dimensiones válidas (números enteros).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
