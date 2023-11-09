// PintorCirculos.java
package pintor;

import java.awt.Graphics;
import java.util.Random;

public class PintorCirculo implements Pintor {
    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        Random random = new Random();
        int x = random.nextInt(ancho);
        int y = random.nextInt(alto);
        int radio = random.nextInt(Math.min(ancho, alto) / 4);

        g.drawOval(x, y, radio, radio);
    }
}
