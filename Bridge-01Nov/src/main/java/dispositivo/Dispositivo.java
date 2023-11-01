package dispositivo;

import controlador.ControlRemoto;

public abstract class Dispositivo {
    protected ControlRemoto controlRemoto;

    public Dispositivo(ControlRemoto controlRemoto) {
        this.controlRemoto = controlRemoto;
    }

    public void subirVolumen() {
        controlRemoto.subirVolumen();
    }

    public void bajarVolumen() {
        controlRemoto.bajarVolumen();
    }

    public abstract void encender();
    public abstract void apagar();
}

