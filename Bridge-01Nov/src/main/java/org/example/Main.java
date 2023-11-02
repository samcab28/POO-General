package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dispositivo.*;
import controlador.*;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los dispositivos y controles
        ControlRemoto televisorControl = new TelevisorControl();
        ControlRemoto parlanteControl = new ParlanteControl();
        ControlRemoto computadoraControl = new ComputadoraControl();
        Televisor televisor = new Televisor(televisorControl);
        Parlante parlante = new Parlante(parlanteControl);
        Computadora computadora = new Computadora(computadoraControl);

        // Crear una ventana principal
        JFrame frame = new JFrame("Control de Dispositivos");
        frame.setSize(600, 600);
        frame.setMinimumSize(new Dimension(600, 600));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear un panel para los botones y el label
        JPanel panel = new JPanel();
        frame.add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(4, 2));

        // Crear botones para las acciones
        JButton encenderButton = new JButton("Encender");
        JButton apagarButton = new JButton("Apagar");
        JButton subirVolumenButton = new JButton("Subir Volumen");
        JButton bajarVolumenButton = new JButton("Bajar Volumen");

        // Crear un label para mostrar mensajes
        JLabel mensajeLabel = new JLabel("Mensaje: ");

        // Agregar los componentes al panel
        panel.add(encenderButton);
        panel.add(apagarButton);
        panel.add(subirVolumenButton);
        panel.add(bajarVolumenButton);
        panel.add(new JLabel("Dispositivo:"));
        JComboBox<String> dispositivoComboBox = new JComboBox<>(new String[]{"Televisor", "Parlante", "Computadora"});
        panel.add(dispositivoComboBox);
        panel.add(mensajeLabel);

        // Agregar oyentes de eventos a los botones
        encenderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener el dispositivo seleccionado
                String dispositivoSeleccionado = dispositivoComboBox.getSelectedItem().toString();
                String mensaje = "";
                switch (dispositivoSeleccionado) {
                    case "Televisor":
                        televisor.encender();
                        mensaje = "Televisor encendido";
                        break;
                    case "Parlante":
                        parlante.encender();
                        mensaje = "Parlante encendido";
                        break;
                    case "Computadora":
                        computadora.encender();
                        mensaje = "Computadora encendida";
                        break;
                }
                mensajeLabel.setText("Mensaje: " + mensaje);
            }
        });

        apagarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dispositivoSeleccionado = dispositivoComboBox.getSelectedItem().toString();
                String mensaje = "";
                switch (dispositivoSeleccionado) {
                    case "Televisor":
                        televisor.apagar();
                        mensaje = "Televisor apagado";
                        break;
                    case "Parlante":
                        parlante.apagar();
                        mensaje = "Parlante apagado";
                        break;
                    case "Computadora":
                        computadora.apagar();
                        mensaje = "computadora apagada";
                        break;
                }
                mensajeLabel.setText("Mensaje: " + mensaje);
            }
        });

        subirVolumenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dispositivoSeleccionado = dispositivoComboBox.getSelectedItem().toString();
                String mensaje = "";
                switch (dispositivoSeleccionado) {
                    case "Televisor":
                        televisor.subirVolumen();
                        mensaje = "Televisor se le sube el volumen";
                        break;
                    case "Parlante":
                        parlante.subirVolumen();
                        mensaje = "Parlante se le sube el volumen";
                        break;
                    case "Computadora":
                        computadora.subirVolumen();
                        mensaje = "Computadora se le sube el volumen";
                        break;
                }
                mensajeLabel.setText("Mensaje: " + mensaje);
            }
        });

        bajarVolumenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dispositivoSeleccionado = dispositivoComboBox.getSelectedItem().toString();
                String mensaje = "";
                switch (dispositivoSeleccionado) {
                    case "Televisor":
                        televisor.bajarVolumen();
                        mensaje = "Televisor se baja el volumen";
                        break;
                    case "Parlante":
                        parlante.bajarVolumen();
                        mensaje = "Parlante se baja el volumen";
                        break;
                    case "Computadora":
                        computadora.bajarVolumen();
                        mensaje = "Computadora se baja el volumen";
                        break;
                }
                mensajeLabel.setText("Mensaje: " + mensaje);
            }
        });

        // Configurar la ventana principal
        frame.pack();
        frame.setVisible(true);
    }
}


