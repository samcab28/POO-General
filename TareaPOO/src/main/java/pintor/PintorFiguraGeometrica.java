// PintorFiguraGeometrica.java
package pintor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

// Clase que implementa la interfaz Pintor para dibujar figuras geométricas aleatorias
public class PintorFiguraGeometrica implements Pintor {
    private boolean useRandomColors; // Flag para indicar si se deben usar colores aleatorios
    private boolean useRandomStrokeWidth; // Flag para indicar si se debe usar un grosor aleatorio

    // Método de la interfaz Pintor para realizar la acción de pintar figuras geométricas aleatorias
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

        // Generar parámetros aleatorios para la figura geométrica
        int x = random.nextInt(ancho);
        int y = random.nextInt(alto);
        int width = random.nextInt(ancho / 2);
        int height = random.nextInt(alto / 2);

        // Dibujar un rectángulo y un rectángulo redondeado con esquinas de radio 15
        g.drawRect(x, y, width, height);
        g.drawRoundRect(x, y, width, height, 15, 15);
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
