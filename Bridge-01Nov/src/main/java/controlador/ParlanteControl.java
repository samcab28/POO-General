// Paquete "controlador"
package controlador;

public class ParlanteControl implements ControlRemoto {
    @Override
    public void subirVolumen() {
        System.out.println("Subiendo el volumen del parlante");
    }

    @Override
    public void bajarVolumen() {
        System.out.println("Bajando el volumen del parlante");
    }
}
