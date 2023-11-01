// Paquete "controlador"
package controlador;

public class TelevisorControl implements ControlRemoto {
    @Override
    public void subirVolumen() {
        System.out.println("Subiendo el volumen del televisor");
    }

    @Override
    public void bajarVolumen() {
        System.out.println("Bajando el volumen del televisor");
    }
}
