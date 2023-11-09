// PintorRayas.java
package pintor;

import java.awt.Graphics;
import java.util.Random;

public class PintorRayas implements Pintor {
    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        Random random = new Random();
        int x1 = random.nextInt(ancho);
        int y1 = random.nextInt(alto);
        int x2 = random.nextInt(ancho);
        int y2 = random.nextInt(alto);

        g.drawLine(x1, y1, x2, y2);
    }
}

