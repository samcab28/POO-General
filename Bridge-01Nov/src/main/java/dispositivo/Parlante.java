package dispositivo;

import controlador.ControlRemoto;

public class Parlante {
    private ControlRemoto controlRemoto;

    public Parlante(ControlRemoto controlRemoto) {
        this.controlRemoto = controlRemoto;
    }

    public void subirVolumen() {
        controlRemoto.subirVolumen();
    }

    public void bajarVolumen() {
        controlRemoto.bajarVolumen();
    }

    public void encender() {
        System.out.println("Encendiendo el parlante");
    }

    public void apagar() {
        System.out.println("Apagando el parlante");
    }
}
