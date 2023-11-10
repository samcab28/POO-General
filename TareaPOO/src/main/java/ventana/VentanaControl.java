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
    private JLabel labelIteraciones;
    private JComboBox<Integer> iteracionesComboBox;
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

        JLabel labelPintor = new JLabel("Seleccione el tipo de pintor:");
        String[] pintores = {"Rayas", "Círculos", "Figuras"};
        pintorComboBox = new JComboBox<>(pintores);

        agregarPintorButton = new JButton("Agregar Pintor");
        infoTextArea = new JTextArea(10, 30);
        infoTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(infoTextArea);

        labelIteraciones = new JLabel("Cantidad de Iteraciones:");
        Integer[] iteraciones = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        iteracionesComboBox = new JComboBox<>(iteraciones);
        guardarButton = new JButton("Guardar");

        add(labelPintor);
        add(pintorComboBox);
        add(agregarPintorButton);
        add(scrollPane);
        add(labelIteraciones);
        add(iteracionesComboBox);
        add(guardarButton);

        pintoresCreados = new ArrayList<>();
        ventanaPrincipal = VentanaPrincipal.obtenerInstancia();

        agregarPintorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPintor = (String) pintorComboBox.getSelectedItem();

                // Crear el pintor utilizando el PintorFactory
                Pintor nuevoPintor = crearPintor(selectedPintor);

                // Almacena la información del pintor creado en la lista
                pintoresCreados.add(nuevoPintor);

                // Actualiza el texto en el JTextArea con la información de los pintores creados
                updateInfoTextArea();
            }
        });

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIteraciones = (int) iteracionesComboBox.getSelectedItem();

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
