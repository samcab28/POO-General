package dispositivo;

import controlador.ControlRemoto;

public class Computadora {
    private ControlRemoto controlRemoto;

    public Computadora(ControlRemoto controlRemoto) {
        this.controlRemoto = controlRemoto;
    }

    public void subirVolumen() {
        controlRemoto.subirVolumen();
    }

    public void bajarVolumen() {
        controlRemoto.bajarVolumen();
    }

    public void encender() {
        System.out.println("Encendiendo la computadora");
    }

    public void apagar() {
        System.out.println("Apagando la computadora");
    }
}
