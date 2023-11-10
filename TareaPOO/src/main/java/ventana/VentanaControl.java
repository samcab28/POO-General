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
    private JCheckBox colorCheckBox;
    private JCheckBox strokeWidthCheckBox;
    private JComboBox<String> pintorComboBox;
    private JButton agregarPintorButton;
    private JTextArea infoTextArea;
    private JButton guardarButton;
    private volatile boolean stopThread;
    private int selectedIteraciones;
    private List<Pintor> pintoresCreados;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaControl() {
        setTitle("Ventana Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout());

        colorCheckBox = new JCheckBox("Colores Aleatorios");
        strokeWidthCheckBox = new JCheckBox("Grosor Aleatorio");

        JLabel labelPintor = new JLabel("Seleccione el tipo de pintor:");
        String[] pintores = {"Rayas", "Círculos", "Figuras"};
        pintorComboBox = new JComboBox<>(pintores);

        agregarPintorButton = new JButton("Agregar Pintor");
        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        guardarButton = new JButton("Guardar");

        pintoresCreados = new ArrayList<>();
        ventanaPrincipal = VentanaPrincipal.obtenerInstancia();

        // Cambia el orden en el que se agregan los componentes al contenedor
        add(colorCheckBox);
        add(strokeWidthCheckBox);
        add(labelPintor);
        add(pintorComboBox);
        add(agregarPintorButton);
        add(scrollPane);
        add(guardarButton);

        agregarPintorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPintor = (String) pintorComboBox.getSelectedItem();

                // Retrieve the user's choices
                boolean useRandomColors = colorCheckBox.isSelected();
                boolean useRandomStrokeWidth = strokeWidthCheckBox.isSelected();

                // Create the painter using the PintorFactory
                Pintor nuevoPintor = crearPintor(selectedPintor, useRandomColors, useRandomStrokeWidth);

                // Store information about the created painter in the list
                pintoresCreados.add(nuevoPintor);

                // Update the text in the JTextArea with information about the created painters
                updateInfoTextArea();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Establecer la cantidad de iteraciones directamente a 1
                selectedIteraciones = 1;

                // Iniciar un hilo para mostrar el mensaje cada 3 segundos
                new Thread(() -> {
                    int iteracionActual = 0;
                    while (iteracionActual < selectedIteraciones && !stopThread) {
                        // Muestra el mensaje cada 3 segundos
                        SwingUtilities.invokeLater(() -> {
                            // Itera sobre los pintores creados y notifica a la VentanaPrincipal
                            for (Pintor pintor : pintoresCreados) {
                                ventanaPrincipal.agregarPintor(pintor);
                                // Puedes agregar más acciones relacionadas con la interfaz de usuario aquí si es necesario
                            }
                        });

                        try {
                            Thread.sleep(3000); // Espera 3 segundos
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                        iteracionActual++;
                    }
                }).start();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                stopThread = true;
            }
        });

        setVisible(true);
    }

    private Pintor crearPintor(String tipo, boolean useRandomColors, boolean useRandomStrokeWidth) {
        // Verify the type of painter and pass the type to the factory
        Pintor nuevoPintor;
        if (tipo.equalsIgnoreCase("Rayas")) {
            nuevoPintor = PintorFactory.crearPintor("rayas");
        } else if (tipo.equalsIgnoreCase("Círculos")) {
            nuevoPintor = PintorFactory.crearPintor("circulos");
        } else if (tipo.equalsIgnoreCase("Figuras")) {
            nuevoPintor = PintorFactory.crearPintor("figuras");
        } else {
            throw new IllegalArgumentException("Tipo de pintor no válido.");
        }

        // Customize the painter based on user choices
        nuevoPintor.setUseRandomColors(useRandomColors);
        nuevoPintor.setUseRandomStrokeWidth(useRandomStrokeWidth);

        return nuevoPintor;
    }

    private void updateInfoTextArea() {
        StringBuilder infoText = new StringBuilder("Información sobre pintores creados:\n");
        for (Pintor pintor : pintoresCreados) {
            infoText.append("- ").append(pintor.getClass().getSimpleName()).append("\n");
        }
        infoTextArea.setText(infoText.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaControl();
        });
    }
}
