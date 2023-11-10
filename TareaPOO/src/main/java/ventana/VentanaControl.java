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
    private JButton guardarButton;
    private JComboBox<Integer> cantidadPintoresComboBox; // Nuevo JComboBox
    private volatile boolean stopThread;
    private int selectedIteraciones;
    private List<Pintor> pintoresCreados;
    private VentanaPrincipal ventanaPrincipal;
    private JCheckBox colorCheckBox;
    private JCheckBox strokeWidthCheckBox;

    // Variables para almacenar información acumulada
    private StringBuilder accumulatedInfo;
    private boolean lastUseRandomColors;
    private boolean lastUseRandomStrokeWidth;

    public VentanaControl() {
        setTitle("Ventana Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        JLabel labelPintor = new JLabel("Seleccione el tipo de pintor:");
        String[] pintores = {"Rayas", "Círculos", "Figuras"};
        pintorComboBox = new JComboBox<>(pintores);

        // Nuevo JComboBox para seleccionar la cantidad de pintores
        cantidadPintoresComboBox = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        cantidadPintoresComboBox.setSelectedIndex(0); // Inicializar en 1

        agregarPintorButton = new JButton("Agregar Pintor");
        infoTextArea = new JTextArea(20, 50);
        infoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        guardarButton = new JButton("Guardar");

        pintoresCreados = new ArrayList<>();
        ventanaPrincipal = VentanaPrincipal.obtenerInstancia();

        // Paneles para checkboxes
        JPanel checkboxesPanel = new JPanel();
        checkboxesPanel.setLayout(new BoxLayout(checkboxesPanel, BoxLayout.Y_AXIS));

        JPanel staticCheckboxesPanel = new JPanel();
        staticCheckboxesPanel.setLayout(new BoxLayout(staticCheckboxesPanel, BoxLayout.Y_AXIS));
        colorCheckBox = new JCheckBox("Colores Aleatorios");
        strokeWidthCheckBox = new JCheckBox("Grosor Aleatorio");
        staticCheckboxesPanel.add(colorCheckBox);
        staticCheckboxesPanel.add(strokeWidthCheckBox);

        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(labelPintor);
        topPanel.add(pintorComboBox);
        topPanel.add(agregarPintorButton);

        // Agregar el nuevo JComboBox al panel superior
        topPanel.add(new JLabel("Cantidad de Pintores:"));
        topPanel.add(cantidadPintoresComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(guardarButton, BorderLayout.SOUTH);
        add(staticCheckboxesPanel, BorderLayout.WEST);
        add(checkboxesPanel, BorderLayout.EAST);

        // Inicializar las variables de información acumulada
        accumulatedInfo = new StringBuilder();

        agregarPintorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPintor = (String) pintorComboBox.getSelectedItem();

                // Retrieve the user's choices
                boolean useRandomColors = colorCheckBox.isSelected();
                boolean useRandomStrokeWidth = strokeWidthCheckBox.isSelected();

                // Retrieve the selected quantity of painters
                int selectedQuantity = (int) cantidadPintoresComboBox.getSelectedItem();

                // Create the specified number of painters
                for (int i = 0; i < selectedQuantity; i++) {
                    // Create the painter using the PintorFactory
                    Pintor nuevoPintor = crearPintor(selectedPintor, useRandomColors, useRandomStrokeWidth);

                    // Store information about the created painter in the list
                    pintoresCreados.add(nuevoPintor);

                    // Save the last choices
                    lastUseRandomColors = useRandomColors;
                    lastUseRandomStrokeWidth = useRandomStrokeWidth;

                    // Update the text in the JTextArea with information about all created painters
                    updateInfoTextArea(nuevoPintor);
                }

                // Reiniciar los checkboxes
                reiniciarCheckboxes(colorCheckBox, strokeWidthCheckBox);
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIteraciones = 1;

                // Iniciar un hilo para mostrar el mensaje cada 3 segundos
                new Thread(() -> {
                    int iteracionActual = 0;
                    while (iteracionActual < selectedIteraciones && !stopThread) {
                        // Muestra el mensaje cada 3 segundos
                        SwingUtilities.invokeLater(() -> {
                            // Muestra la información acumulada
                            infoTextArea.setText(accumulatedInfo.toString());

                            // Itera sobre los pintores creados y notifica a la VentanaPrincipal
                            for (int i = 0; i < pintoresCreados.size(); i++) {
                                ventanaPrincipal.agregarPintor(pintoresCreados.get(i));
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

        nuevoPintor.setUseRandomColors(useRandomColors);
        nuevoPintor.setUseRandomStrokeWidth(useRandomStrokeWidth);

        return nuevoPintor;
    }

    private void updateInfoTextArea(Pintor pintor) {
        // Agregar información sobre el pintor al acumulado
        accumulatedInfo.append("- ").append(pintor.getClass().getSimpleName());

        if (lastUseRandomColors || lastUseRandomStrokeWidth) {
            accumulatedInfo.append(" (");
            if (lastUseRandomColors) {
                accumulatedInfo.append("Colores Aleatorios");
                if (lastUseRandomStrokeWidth) {
                    accumulatedInfo.append(" y ");
                }
            }
            if (lastUseRandomStrokeWidth) {
                accumulatedInfo.append("Grosor Aleatorio");
            }
            accumulatedInfo.append(")");
        }

        accumulatedInfo.append("\n");

        // Actualizar el texto en el JTextArea con la información acumulada
        infoTextArea.setText(accumulatedInfo.toString());
    }

    private void reiniciarCheckboxes(JCheckBox... checkboxes) {
        for (JCheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaControl();
        });
    }
}
