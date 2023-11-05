package ventana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VentanaControl extends JFrame {
    private JComboBox<String> pintorComboBox;
    private JComboBox<Integer> cantidadColoresComboBox;
    private JButton guardarButton;
    private JTextArea infoTextArea;
    private List<String> pintoresCreados;

    public VentanaControl() {
        setTitle("Ventana Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        JLabel labelPintor = new JLabel("Seleccione el tipo de pintor:");
        String[] pintores = {"Pintor de líneas", "Pintor de círculos", "Pintor de polígonos"};
        pintorComboBox = new JComboBox<>(pintores);

        JLabel labelColores = new JLabel("Seleccione la cantidad de colores (1-5):");
        Integer[] cantidadesColores = {1, 2, 3, 4, 5};
        cantidadColoresComboBox = new JComboBox<>(cantidadesColores);

        guardarButton = new JButton("Guardar");
        infoTextArea = new JTextArea(10, 30); // 10 líneas y 30 columnas
        infoTextArea.setEditable(false); // Evita que el usuario edite el texto
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        add(labelPintor);
        add(pintorComboBox);
        add(labelColores);
        add(cantidadColoresComboBox);
        add(guardarButton);
        add(scrollPane);

        pintoresCreados = new ArrayList<>();

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPintor = (String) pintorComboBox.getSelectedItem();
                int selectedColores = (int) cantidadColoresComboBox.getSelectedItem();

                // Almacena la información del pintor creado en la lista
                String pintorCreado = selectedPintor + " - Colores: " + selectedColores;
                pintoresCreados.add(pintorCreado);

                // Actualiza el texto en el JTextArea con la información de los pintores creados
                updateInfoTextArea();

                // Restablece los combos de selección
                pintorComboBox.setSelectedIndex(0);
                cantidadColoresComboBox.setSelectedIndex(0);
            }
        });

        setVisible(true);
    }

    private void updateInfoTextArea() {
        StringBuilder infoText = new StringBuilder("Información sobre pintores creados:\n");
        for (String pintorInfo : pintoresCreados) {
            infoText.append("- ").append(pintorInfo).append("\n");
        }
        infoTextArea.setText(infoText.toString());
    }
}

