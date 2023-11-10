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
    // Componentes de la interfaz de usuario
    private JComboBox<String> pintorComboBox;
    private JButton agregarPintorButton;
    private JTextArea infoTextArea;
    private JButton guardarButton;
    private JComboBox<Integer> cantidadPintoresComboBox;
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
        // Configuración básica de la ventana
        setTitle("Ventana Control");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Configuración de componentes de la interfaz de usuario
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

        // Acciones del botón "Agregar Pintor"
        agregarPintorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el tipo de pintor seleccionado
                String selectedPintor = (String) pintorComboBox.getSelectedItem();

                // Obtener las elecciones del usuario
                boolean useRandomColors = colorCheckBox.isSelected();
                boolean useRandomStrokeWidth = strokeWidthCheckBox.isSelected();

                // Obtener la cantidad seleccionada de pintores
                int selectedQuantity = (int) cantidadPintoresComboBox.getSelectedItem();

                // Crear la cantidad especificada de pintores
                for (int i = 0; i < selectedQuantity; i++) {
                    // Crear el pintor utilizando la fábrica de pintores
                    Pintor nuevoPintor = crearPintor(selectedPintor, useRandomColors, useRandomStrokeWidth);

                    // Almacenar información sobre el pintor creado en la lista
                    pintoresCreados.add(nuevoPintor);

                    // Guardar las últimas elecciones
                    lastUseRandomColors = useRandomColors;
                    lastUseRandomStrokeWidth = useRandomStrokeWidth;

                    // Actualizar el texto en JTextArea con información sobre todos los pintores creados
                    updateInfoTextArea(nuevoPintor);
                }

                // Reiniciar los checkboxes
                reiniciarCheckboxes(colorCheckBox, strokeWidthCheckBox);
            }
        });

        // Acciones del botón "Guardar"
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

        // Detener el hilo al cerrar la ventana
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                stopThread = true;
            }
        });

        setVisible(true);
    }

    // Método para crear un pintor con opciones específicas
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

    // Método para actualizar la información en el JTextArea
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

    // Método para reiniciar los checkboxes a su estado inicial
    private void reiniciarCheckboxes(JCheckBox... checkboxes) {
        for (JCheckBox checkbox : checkboxes) {
            checkbox.setSelected(false);
        }
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaControl ventanaControl = new VentanaControl();

            // Obtener las dimensiones de la pantalla
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            // Definir la ubicación de la esquina superior derecha
            int x = screenSize.width - ventanaControl.getWidth();
            int y = 0;

            // Establecer la ubicación de la ventana
            ventanaControl.setLocation(x, y);
        });
    }
}
