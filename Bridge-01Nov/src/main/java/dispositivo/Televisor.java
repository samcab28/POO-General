package dispositivo;

import controlador.ControlRemoto;

public class Televisor {
    private ControlRemoto controlRemoto;

    public Televisor(ControlRemoto controlRemoto) {
        this.controlRemoto = controlRemoto;
    }

    public void subirVolumen() {
        controlRemoto.subirVolumen();
    }

    public void bajarVolumen() {
        controlRemoto.bajarVolumen();
    }

    public void encender() {
        System.out.println("Encendiendo el televisor");
    }

    public void apagar() {
        System.out.println("Apagando el televisor");
    }
}

