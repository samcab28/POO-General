package org.example;

import dispositivo.*;
import controlador.*;

public class Main {
    public static void main(String[] args) {
        ControlRemoto televisorControl = new TelevisorControl();
        ControlRemoto parlanteControl = new ParlanteControl();
        ControlRemoto computadoraControl = new ComputadoraControl();

        Televisor televisor = new Televisor(televisorControl);
        Parlante parlante = new Parlante(parlanteControl);
        Computadora computadora = new Computadora(computadoraControl);

        System.out.println("televisor:");
        televisor.encender();
        televisor.subirVolumen();
        televisor.bajarVolumen();
        televisor.apagar();

        System.out.println("\n\nparlante:");
        parlante.encender();
        parlante.subirVolumen();
        parlante.bajarVolumen();
        parlante.apagar();

        System.out.println("\n\ncomputadora:");
        computadora.encender();
        computadora.subirVolumen();
        computadora.bajarVolumen();
        computadora.apagar();
    }
}

