// PintorCompuesto.java
package pintor;

import java.awt.Graphics;

public class PintorCompuesto implements Pintor {
    private Pintor[] pintores;

    public PintorCompuesto(Pintor... pintores) {
        this.pintores = pintores;
    }

    @Override
    public void pintar(Graphics g, int ancho, int alto) {
        for (Pintor pintor : pintores) {
            // Paint using the individual painter
            pintor.pintar(g, ancho, alto);
        }
    }

    @Override
    public void setUseRandomColors(boolean useRandomColors) {
        // Leave empty
    }

    @Override
    public void setUseRandomStrokeWidth(boolean useRandomStrokeWidth) {
        // Leave empty
    }

    public void setPintores(Pintor... pintores) {
        this.pintores = pintores;
    }
}