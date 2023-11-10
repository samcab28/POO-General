package pintor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class PintorRayas implements Pintor {
    private boolean useRandomColors;
    private boolean useRandomStrokeWidth;

    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        Random random = new Random();

        if (useRandomColors) {
            g.setColor(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        }

        if (useRandomStrokeWidth) {
            ((Graphics2D) g).setStroke(new java.awt.BasicStroke(random.nextInt(5) + 1));
        }

        int x1 = random.nextInt(ancho);
        int y1 = random.nextInt(alto);
        int x2 = random.nextInt(ancho);
        int y2 = random.nextInt(alto);

        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public void setUseRandomColors(boolean useRandomColors) {
        this.useRandomColors = useRandomColors;
    }

    @Override
    public void setUseRandomStrokeWidth(boolean useRandomStrokeWidth) {
        this.useRandomStrokeWidth = useRandomStrokeWidth;
    }
}
