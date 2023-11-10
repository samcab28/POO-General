// VentanaControl.java
package ventana;

import pintor.Pintor;
import pintor.PintorFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaControl extends JFrame {
    private JComboBox<String> pintorComboBox;
    private JButton agregarPintorButton;
    private JTextArea infoTextArea;
    private List<String> pintoresCreados;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaControl() {
        setTitle("Ventana Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        JLabel labelPintor = new JLabel("Seleccione el tipo de pintor:");
        String[] pintores = {"Rayas", "Círculos", "Figuras"};
        pintorComboBox = new JComboBox<>(pintores);

        agregarPintorButton = new JButton("Agregar Pintor");
        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        add(labelPintor);
        add(pintorComboBox);
        add(agregarPintorButton);
        add(scrollPane);

        pintoresCreados = new ArrayList<>();
        ventanaPrincipal = VentanaPrincipal.obtenerInstancia();

        agregarPintorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPintor = (String) pintorComboBox.getSelectedItem();

                // Crear el pintor utilizando el PintorFactory
                Pintor nuevoPintor = crearPintor(selectedPintor);

                // Almacena la información del pintor creado en la lista
                pintoresCreados.add(nuevoPintor.getClass().getSimpleName());

                // Notificar a la VentanaPrincipal sobre el pintor agregado
                ventanaPrincipal.agregarPintor(nuevoPintor);

                // Actualiza el texto en el JTextArea con la información de los pintores creados
                updateInfoTextArea();
            }
        });

        setVisible(true);
    }

    private Pintor crearPintor(String tipo) {
        // Verificar el tipo de pintor y pasar el tipo a la fábrica
        if (tipo.equalsIgnoreCase("Rayas")) {
            return PintorFactory.crearPintor("rayas");

        } else if (tipo.equalsIgnoreCase("Círculos")) {
            return PintorFactory.crearPintor("circulos");

        } else if (tipo.equalsIgnoreCase("Figuras")) {
            return PintorFactory.crearPintor("figuras");
        } else {
            throw new IllegalArgumentException("Tipo de pintor no válido.");
        }
    }

    private void updateInfoTextArea() {
        StringBuilder infoText = new StringBuilder("Información sobre pintores creados:\n");
        for (String pintorInfo : pintoresCreados) {
            infoText.append("- ").append(pintorInfo).append("\n");
        }
        infoTextArea.setText(infoText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaControl();
        });
    }
}
