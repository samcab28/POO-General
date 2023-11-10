// PintorRayas.java
package pintor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

// Clase que implementa la interfaz Pintor para dibujar líneas aleatorias
public class PintorRayas implements Pintor {
    private boolean useRandomColors; // Flag para indicar si se deben usar colores aleatorios
    private boolean useRandomStrokeWidth; // Flag para indicar si se debe usar un grosor aleatorio

    // Método de la interfaz Pintor para realizar la acción de pintar líneas aleatorias
    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        Random random = new Random();

        // Si se deben usar colores aleatorios, establecer un nuevo color aleatorio
        if (useRandomColors) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        }

        // Si se debe usar un grosor aleatorio, establecer un nuevo grosor aleatorio
        if (useRandomStrokeWidth) {
            ((Graphics2D) g).setStroke(new java.awt.BasicStroke(random.nextInt(5) + 1));
        }

        // Generar coordenadas aleatorias y dibujar una línea
        int x1 = random.nextInt(ancho);
        int y1 = random.nextInt(alto);
        int x2 = random.nextInt(ancho);
        int y2 = random.nextInt(alto);

        g.drawLine(x1, y1, x2, y2);
    }

    // Método de la interfaz Pintor para establecer si se deben usar colores aleatorios
    @Override
    public void setUseRandomColors(boolean useRandomColors) {
        this.useRandomColors = useRandomColors;
    }

    // Método de la interfaz Pintor para establecer si se debe usar un grosor aleatorio
    @Override
    public void setUseRandomStrokeWidth(boolean useRandomStrokeWidth) {
        this.useRandomStrokeWidth = useRandomStrokeWidth;
    }
}
