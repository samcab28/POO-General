// Pintor.java
package pintor;

import java.awt.Graphics;

// Interfaz que define el contrato para los objetos que pueden pintar en un área gráfica
public interface Pintor {
    // Método para realizar la acción de pintar en el área gráfica
    void pintar(Graphics g, int ancho, int alto);

    // Método para establecer si se deben utilizar colores aleatorios
    void setUseRandomColors(boolean useRandomColors);

    // Método para establecer si se debe utilizar un grosor aleatorio
    void setUseRandomStrokeWidth(boolean useRandomStrokeWidth);
}
