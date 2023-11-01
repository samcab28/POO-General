// Paquete "controlador"
package controlador;

public class ComputadoraControl implements ControlRemoto {
    @Override
    public void subirVolumen() {
        System.out.println("Subiendo el volumen de la computadora");
    }

    @Override
    public void bajarVolumen() {
        System.out.println("Bajando el volumen de la computadora");
    }
}
