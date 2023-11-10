package pintor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class PintorFiguraGeometrica implements Pintor {
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

        int x = random.nextInt(ancho);
        int y = random.nextInt(alto);
        int width = random.nextInt(ancho / 2);
        int height = random.nextInt(alto / 2);

        g.drawRect(x, y, width, height);
        g.drawRoundRect(x, y, width, height, 15, 15);
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