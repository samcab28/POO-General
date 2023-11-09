// PintorFigurasGeometricas.java
package pintor;

import java.awt.Graphics;
import java.util.Random;

public class PintorFiguraGeometrica implements Pintor {
    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        Random random = new Random();
        int x = random.nextInt(ancho);
        int y = random.nextInt(alto);
        int width = random.nextInt(ancho / 2);
        int height = random.nextInt(alto / 2);

        g.drawRect(x, y, width, height);
        g.drawRoundRect(x, y, width, height, 15, 15);
    }
}
