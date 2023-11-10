// ObservadorPintor.java
package ventana;

import pintor.Pintor;

// Interfaz que define el contrato para los objetos que desean observar la adición de un Pintor
public interface ObservadorPintor {
    // Método llamado cuando se agrega un nuevo Pintor
    void pintorAgregado(Pintor nuevoPintor);
}
